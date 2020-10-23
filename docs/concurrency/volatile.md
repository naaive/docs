# Volatile

## 可见性
cpu 访问缓存，缓存与内存存在一致性问题。

解决方案：

- 通过在总线加 LOCK# 锁的方式
- 通过缓存一致性协议（MESI 协议）


Java提供了 volatile 来保证可见性。
当一个变量被 volatile 修饰后，表示着线程本地内存无效。
当一个线程修改共享变量后他会立即被更新到主内存中；
当其他线程读取共享变量时，它会直接从主内存中读取。
**synchronize** 和**锁**都可以保证可见性。



## 有序性

为了提高性能，编译器和处理器通常会对指令做重排序：

- 编译器在不改变单线程程序语义的前提下，可以重新安排语句的执行顺序。
- 如果不存在数据依赖性，处理器可以改变语句对应机器指令的执行顺序（指令级别并行，提高IPC）。

x86架构下汇编：
```
Java代码: instance = new Singleton();//instance是volatile变量
汇编代码:  0x01a3de1d: movb $0x0,0x1104800(%esi);0x01a3de24: lock addl $0x0,(%esp);
```
Lock前缀指令实际上相当于一个**内存屏障**（内存栅栏）:

- 确保指令重排序时不会把其后面的指令排到内存屏障之前
- 也不会把前面的指令排到内存屏障的后面



由此引出happen-before原则。



## happens-before原则


- 程序次序规则：单线程按照代码顺序，写在前面的操作，happens-before 于书写在后面的操作
- volatile 变量规则：对一个volatile变量的写操作，happens-before 于**后面**对这个变量的读操作。
- 传递规则

- 锁定规则：一个 unLock 操作，happens-before 于后面对同一个锁的 lock 操作。
- 线程启动规则：Thread 对象的 start 方法，happens-before 此线程的每个一个动作。
- 线程中断规则：对线程 interrupt 方法的调用，happens-before被中断线程的代码检测到中断事件的发生。
- 线程终结规则：线程中所有的操作，都 happens-before 线程的终止检测，我们可以通过Thread.join() 方法结束、Thread.isAlive() 的返回值手段，检测到线程已经终止执行。
- 对象终结规则：一个对象的初始化完成，happens-before 它的 finalize() 方法的开始



前三条对应volatile可见性，有序性
