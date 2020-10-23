# 访问者模式

## 作用

要将数据结构与数据操作分离。

使用场景： 

- 对象结构中对象对应的类很少改变，但经常需要在此对象结构上定义新的操作。
- 需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作"污染"这些对象的类，也不希望在增加新操作时修改这些类。



## 代码

```java

//抽象元素
public interface Element {

    void accept(ElementVisitor visitor);
}

//具体元素-车轮
@Data
@AllArgsConstructor
public class Wheel implements Element {

    private String name;

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
    }
}

//具体元素-车身
public class Body implements Element {

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
    }
}

//具体元素-引擎
public class Engine implements Element {

    @Override
    public void accept(ElementVisitor visitor) {
        visitor.visit(this);
    }
}

//具体元素-整车
public class Car implements Element {

    public void accept(final ElementVisitor visitor) {
        visitor.visit(this);
    }

}

//抽象访问者
public interface ElementVisitor {

    void visit(Body body);

    void visit(Engine engine);

    void visit(Wheel wheel);

    void visit(Car car);
}

//具体的一个访问者，纯打印
@Slf4j
public class DoElementVisitor implements ElementVisitor {

    @Override
    public void visit(Body body) {
        log.info("Moving my body");
    }

    @Override
    public void visit(Engine engine) {
        log.info("Starting my engine");
    }

    @Override
    public void visit(Wheel wheel) {
        log.info("Kicking my " + wheel.getName() + " wheel");
    }

    @Override
    public void visit(Car car) {
        log.info("Starting my car");
    }
}

//单独还定义对象结构，其实完全就可以使用列表就可以
@Data
public class ElementStructure {

    private List<Element> list = Lists.newArrayList();

    public void addElement(Element element){
        list.add(element);
    }

    public void accept(ElementVisitor visitor) {
        for (Element elem : list) {
            elem.accept(visitor);
        }
    }
}


public class ClientWithVisitor {

    public static void main(String[] args) {
        ElementStructure structure = new ElementStructure();
        structure.addElement(new Wheel("front left"));
        structure.addElement(new Wheel("front right"));
        structure.addElement(new Wheel("back left"));
        structure.addElement(new Wheel("back right"));
        structure.addElement(new Body());
        structure.addElement(new Engine());
        structure.addElement(new Car());

        structure.accept(new DoElementVisitor());
    }
}

@Slf4j
public class ClientWithoutVisitor {

    public static void main(String[] args) {
        ElementStructure structure = new ElementStructure();
        structure.addElement(new Wheel("front left"));
        structure.addElement(new Wheel("front right"));
        structure.addElement(new Wheel("back left"));
        structure.addElement(new Wheel("back right"));
        structure.addElement(new Body());
        structure.addElement(new Engine());
        structure.addElement(new Car());

        structure.getList().forEach(e -> {
            if (e instanceof Body) {
                log.info("Moving my body");
            } else if (e instanceof Engine) {
                log.info("Starting my engine");
            } else if (e instanceof Car) {
                log.info("Starting my car");
            } else if (e instanceof Wheel) {
                log.info("Kicking my " + ((Wheel)e).getName() + " wheel");
            }
        });
    }

}

```

## 总结

- 封装变与不变
- ElementVisitor 要修改，违反开闭原则
- 添加新的ElementVisitor实现类，可添加新的业务逻辑，符合开闭原则

## 参考
net.sf.jsqlparser.statement.select.FromItem