# Leader Follower

## 流程图
![leader_follower](leader_follower.png)

基本流程：

1. 全量复制
2. 基于长连接命令传播


## 主从级联模式

好处：
减少主库生成RDB(cpu)、传输RDB(网络io)压力。

## 增量复制

触发条件：

当从库与主库断开连接

实现:
环形缓冲

注：
如果要复制内容在缓冲区中已经被覆盖了，那么就不再做增量复制了，而是进行全量复制。
