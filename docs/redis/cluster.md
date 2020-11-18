# Cluster

分片，水平扩展


## 流程

i.e. Range Based 分片

key===CRC16===>16bit====16bit%16384====>x slot

**slot 与N个实例映射：**

16384 slot/N