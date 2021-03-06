# Transaction(InnoDB)

## 概述


### 原子性
实现原理：**undo log**。（undo log在**MVVC**也有作用）

### 隔离性
**SQL标准**中定义了四种隔离级别:

| 隔离级别 | 脏读 | 不可重复读 | 幻读 |
| -------- | -------- | -------- | -------- |
| Read Uncommitted     | 1     | 1     | 1     |
| Read Committed     | 0     | 1     | 1     |
| Repeatable Read     | 0     | 0     | 1     |
| Serializable     | 0     | 0     | 0     |


InnoDB默认的隔离级别是RR。需要注意的是，在SQL标准中，RR是无法避免幻读问题的，但是InnoDB实现的RR避免了幻读问题。

#### 并发
隔离性事实上是数据库对**并发**解决方案的封装（[STM](https://zhuanlan.zhihu.com/p/151425608)是锁机制的替代）。


Serializable隔离级别下使用**读写锁**，**读读并发**，**读写互斥**。
为了进一步提高并发度，Repeatable Read，Read Committed 隔离级别下使用**MVVC**技术实现**读写并发**。

#### MVVC（Multi-Version Concurrency Control）


### 持久性
实现原理：**redo log**。目的：随机io ==> 顺序io



### 一致性
一致性是指事务执行结束后，完整性约束没有被破坏，事务执行的前后都是合法的数据状态。


完整性约束包括但不限于：**实体完整性**（如行的主键存在且唯一）、**列完整性**（如字段的类型、大小、长度要符合要求）、
**外键约束**、**用户自定义完整性**（如转账前后，两个账户余额的和应该不变）。


i.e.，**一致性是事务追求的最终目标**：原子性、持久性和隔离性，都是为了保证数据库状态的一致性。
此外，除了数据库层面的保障，一致性的实现也需要应用层面进行保障。

实现一致性的措施包括：

- 保证原子性、持久性和隔离性，如果这些特性无法保证，事务的一致性也无法保证
- 数据库本身提供保障，例如不允许向整形列插入字符串值、字符串长度不能超过列的限制等
- 应用层面进行保障，例如如果转账操作只扣除转账者的余额，而没有增加接收者的余额，无论数据库实现的多么完美，也无法保证状态的一致