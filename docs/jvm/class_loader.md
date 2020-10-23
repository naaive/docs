# class Loader

## 双亲委派

递归调用父类类加载器，若未加载，则自己加载。
![classloader](classloader.png)

作用：

- 保证了JVM提供的核心类不被篡改，保证class执行安全
- 防止重复加载同一个class
## 加载流程

![classloader_1](classloader_1.png)