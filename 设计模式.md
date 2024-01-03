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