# Semantics

## Producer

### At most once
```
request.required.acks=0
```

### At least once
Kafka默认
```
request.required.acks=1
```


### At exactly once
```
enable.idempotence=true
```

**原理：**

Producer会为每一个`topic,partition`维护一个单调递增的seq。
Broker也会为每个`pid,topic,partition`记录下最新的seq。

当req_seq == message_seq+1时，Message Queue才会接受该消息。因为：
- 消息的seq比Message Queue的seq大一以上，说明中间有数据还没写入，即乱序了。
- 消息的seq比Message Queue的seq小，那么说明该消息已被保存。


## Consumer


### At most once
consumer直接确认，不管消息是否处理
```
consumer.poll();
consumer.commit();
processMsg(message);
```
### At least once
```
consumer.poll();
processMsg(message);
consumer.commit();
```

会出现重复消息
### At exactly once
在 **At least once**基础上，`processMsg(message);`幂等处理即可
