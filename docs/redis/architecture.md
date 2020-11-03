# Architecture

## 基本实现

### 性能
- 精致的key,val数据机构
- reactor单线程模型

### 可用性
- 持久化备份rdb,aof
- 哨兵

### 可扩展
- redis cluster

## Key数据结构
类似Java里面**HashMap**。
扩容（rehash）时,为防止一次性大量拷贝导致redis服务停顿，采用**渐进式rehash**，分批拷贝。

## Val数据结构

###  String
简单动态字符串

###  List

**压缩列表**
条件：

- 列表中保存的单个数据（有可能是字符串类型的）小于 64 字节
- 列表中数据个数少于 512 个

优点：
- 省内存
- 数据量小时，读写时间复杂度尚可接受
**双向循环链表**
条件：
不满足压缩列表条件时

###  Hash
**压缩列表**
条件：

- 字典中保存的键和值小于 64 字节
- 字典中键值对的个数要小于 512 个

**哈希表**
条件：
不满足压缩列表条件时

###  Set
**有序数组**
条件：

- 存储的数据都是整数
- 存储的数据元素个数不超过 512 个

**哈希表**
条件：
不满足有序数组条件时

###  ZSet

**压缩列表**
条件：

- 所有数据的大小都要小于 64 字节
- 元素个数要小于 128 个

**跳表**
条件：
不满足压缩列表条件时