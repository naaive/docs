# Linked List

## 特征

- 不连续空间
- 不支持随机访问
- 通过指针确认线性关系

## 案例

### 单向链表

- 根据下标读写 O(n)
- 头读写O(1)
- 尾读写O(n)

## 双向链表

- 根据下标读写 O(n)
- 头读写O(1)
- 尾读写O(1)
- 删除指定节点更高效，直接通过prev指针操作
- 相比单链表**空间换时间**

## LRU

```java
LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(10, 1, true) {
    @Override
    protected boolean removeEldestEntry(Entry<String, String> eldest) {
        return size() > 3;
    }
};

map.put("1", "1");
map.put("2", "2");
map.put("3", "3");
map.get("2");
map.put("4", "4");

map.forEach((x, y) -> {
    System.out.println(x);
});

//3
//2
//4
```