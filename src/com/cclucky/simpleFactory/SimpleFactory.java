package com.cclucky.simpleFactory;

abstract class Person {
    abstract void eat();

    abstract void sleep();
}

class Student extends Person {

    @Override
    void eat() {
        System.out.println("student is eating");
    }

    @Override
    void sleep() {
        System.out.println("student is sleeping");
    }
}

class Teacher extends Person {

    @Override
    void eat() {
        System.out.println("teacher is eating");
    }

    @Override
    void sleep() {
        System.out.println("teacher is sleeping");
    }
}

/**
 * 简单工厂模式
 * @author cclucky
 */
public class SimpleFactory {
    public static Person createPerson(String type) {
        Person person = null;
        switch (type) {
            case "1":
                person = new Student();
            case "2":
                person = new Teacher();
            default:
                return person;
        }
    }

    public static void main(String[] args) {
        System.out.println(createPerson("1"));
    }
}
