# Singleton

## 定义

一个类只允许创建一个对象（或者叫实例），那这个类就是一个单例类，这种设计模式就叫作单例设计模式


## 优点
- 提供了对唯一实例的受控访问。
- 由于在系统内存中只存在一个对象，因此可以节约系统资源，对于一些需要频繁创建和销毁的对象，单例模式无疑可以提高系统的性能。
- 允许可变数目的实例。可基于单例模式进行扩展，使用与单例控制相似的方法来获得指定个数的对象实例（多例）。

## 缺点

- 由于单例模式中没有抽象层，因此单例类的扩展有很大的困难。
- 单例类的职责过重，在一定程度上违背了“单一职责原则”。因为单例类既充当了工厂角色，提供了工厂方法，同时又充当了产品角色，包含一些业务方法，将产品的创建和产品的本身的功能融合到一起。

## 实现

- 饿汉式（静态实例。事实上也是懒汉的，只要不加载此类）
- 懒汉式
- 双重检测
- 静态内部类

推荐饿汉式，简单实用。