package com.cclucky.decorator;

/**
 * 装饰器模式
 */
interface Component {
    void doSomething();
}

class DecoratorComponent implements Component {

    @Override
    public void doSomething() {
        System.out.println("do something");
    }
}

abstract class Decorator implements Component {
    private final Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}

class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        System.out.println("do more things+++++++");
    }
}

class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        System.out.println("do more things-------");
    }
}

/**
 * @author cclucky
 */
public class Main {
    public static void main(String[] args) {
        DecoratorComponent component = new DecoratorComponent();
        component.doSomething();
        System.out.println("================");
        new ConcreteDecoratorA(component).doSomething();
        System.out.println("================");
        new ConcreteDecoratorB(component).doSomething();
    }
}
