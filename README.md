# 设计模式

> 设计模式是一种在软件设计中经常出现的问题的通用解决方案，它提供了一套被广泛接受的最佳实践，有助于提高代码的可维护性、灵活性和可复用性。

**了解基本的设计模式分类**

- **创建型模式：** 处理对象的创建机制，例如工厂方法、抽象工厂、单例模式等。
- **结构型模式：** 处理类和对象的组合，例如适配器、装饰器、代理模式等。
- **行为型模式：** 处理对象之间的交互，例如观察者、策略、命令模式等。

### 1.单例模式

> 单例模式是一种创建型设计模式，其目的是确保类只有一个实例，并提供一个全局访问点。通常用于管理全局状态、资源共享或控制对唯一实例的访问。

* **单一实例：** 单例模式确保一个类只有一个实例，并提供全局访问点，使得整个系统中只能存在一个该类的对象实例。
* **全局访问点：** 单例模式提供了一个全局访问点，其他对象可以通过该访问点获取单例对象的引用，从而访问该实例的方法和属性。

#### 1.1 饿汉式（**Eager Initialization**）

~~~java
public class Singleton {
    private static final Singleton instance = new Singleton();

    // 通过private修饰无参构造方法使得无法外部实例化对象
    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
~~~

#### 1.2 懒汉式（**Lazy Initialization**）

~~~java
public class Singleton {
    private static Singleton instance;

    // 通过private修饰无参构造方法使得无法外部实例化对象
    private Singleton() {}

    // 确保只有一个线程访问
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
~~~

~~~java
public class Singleton {
    private static Singleton instance;

    // 通过private修饰无参构造方法使得无法外部实例化对象
    private Singleton() {}

    // 确保只有一个线程访问
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
~~~

#### 1.3 使用场景

- 当系统中某个类只需要一个实例来协调行为时，例如配置管理、线程池、日志对象等。
- 当实例化一个对象的成本过高，频繁创建和销毁会影响性能时，可以使用单例模式来缓存对象。



### 2 简单工厂模式

> 简单工厂模式通过一个工厂类，根据传入的参数来决定创建哪一种产品类的实例。消费者无需关注实现逻辑。

#### 2.1 实现

~~~java
// 产品接口
public interface Product {
    void operation();
}

// 具体产品类A
public class ConcreteProductA implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProductA operation");
    }
}

// 具体产品类B
public class ConcreteProductB implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProductB operation");
    }
}

// 工厂类
public class SimpleFactory {
    public static Product createProduct(String type) {
        if ("A".equals(type)) {
            return new ConcreteProductA();
        } else if ("B".equals(type)) {
            return new ConcreteProductB();
        }
        return null;
    }
}
~~~

#### 2.2 使用场景

- 当一个系统需要独立于其创建、组合和表示时，可以使用简单工厂模式。
- 当客户端只知道产品的参数，而不关心具体创建的过程时，可以使用简单工厂模式。

__缺点__

工厂类集中了所有产品的创建逻辑，一旦需要添加新产品，就需要修改工厂类的代码，违反了开闭原则。



### 3 工厂方法模式

> 定义了一个创建对象的接口，但将具体的对象创建延迟到子类中。优化了简单工厂模式一旦添加产品，就要修改工厂类代码的问题。

#### 3.1 实现

~~~java
// 工厂接口
public interface Factory {
    Product createProduct();
}

// 具体工厂类A
public class ConcreteFactoryA implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

// 具体工厂类B
public class ConcreteFactoryB implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}

// 产品接口
public interface Product {
    void operation();
}

// 具体产品类A
public class ConcreteProductA implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProductA operation");
    }
}

// 具体产品类B
public class ConcreteProductB implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProductB operation");
    }
}
~~~

#### 3.2 使用场景

- 当一个类无法预知它所必须创建的对象的类时，可以使用工厂方法模式。
- 当一个类希望由它的子类来指定创建对象时，可以使用工厂方法模式。

__缺点__

每增加一个新的具体产品类，都需要创建一个对应的具体工厂类，导致类的数量增加。



### 4 抽象工厂模式

> 提供一个接口用于创建一系列相关或依赖对象的家族，而不需要指定具体类。抽象工厂模式的核心思想是将工厂抽象成接口，每个具体的工厂都实现这个接口，负责创建一组相关的产品。

#### 4.1 实现方法

~~~java
// 抽象工厂接口
public interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

// 具体工厂类A
public class ConcreteFactoryA implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

// 具体工厂类B
public class ConcreteFactoryB implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}

// 抽象产品A接口
public interface ProductA {
    void operationA();
}

// 具体产品A1类
public class ConcreteProductA1 implements ProductA {
    @Override
    public void operationA() {
        System.out.println("ConcreteProductA1 operation");
    }
}

// 具体产品A2类
public class ConcreteProductA2 implements ProductA {
    @Override
    public void operationA() {
        System.out.println("ConcreteProductA2 operation");
    }
}

// 抽象产品B接口
public interface ProductB {
    void operationB();
}

// 具体产品B1类
public class ConcreteProductB1 implements ProductB {
    @Override
    public void operationB() {
        System.out.println("ConcreteProductB1 operation");
    }
}

// 具体产品B2类
public class ConcreteProductB2 implements ProductB {
    @Override
    public void operationB() {
        System.out.println("ConcreteProductB2 operation");
    }
}
~~~

#### 4.2 使用场景

- 当系统中有多个系列的产品，且这些产品之间存在关联或依赖关系时，可以使用抽象工厂模式。

#### 4.3 工厂方法模式和抽象工厂模式的简单区别

##### 关注点不同

- **抽象工厂模式：** 关注创建一整个产品家族，包括一组相关或依赖的产品。抽象工厂接口定义了多个方法，每个方法用于创建一个特定类型的产品。
- **工厂方法模式：** 关注创建单一产品，但通过将创建过程延迟到子类来使得客户端能够选择实例化哪个具体类。工厂方法模式只有一个抽象方法，用于创建单一类型的产品。

> 简答来说就是工厂方法模式是工厂对应具体实现类，而抽象工厂模式是把实现类抽象成接口出来，多了一层依赖关系。



### 5 装饰器模式

> 通过将对象放入包含行为的特殊包装类中来为原对象添加新的行为

#### 5.1 实现方法

~~~java
// 组件接口
public interface Component {
    void operation();
}

// 具体组件
public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("ConcreteComponent operation");
    }
}

// 装饰器抽象类
public abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

// 具体装饰器A
public class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("ConcreteDecoratorA operation");
    }
}

// 具体装饰器B
public class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("ConcreteDecoratorB operation");
    }
}
~~~

#### 5.2 使用场景

- 当需要在不修改现有对象结构的情况下，动态地添加、删除或扩展对象的功能时，可以使用装饰器模式。
- 当继承关系无法满足系统的需求，或者因为类数量庞大而导致继承关系过于复杂时，装饰器模式提供了一种更灵活的替代方案。