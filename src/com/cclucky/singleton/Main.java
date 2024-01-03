package com.cclucky.singleton;

/**
 * 饿汉模式
 */
class Singleton1 {
    private final static Singleton1 singleton = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getSingleton() {
        return singleton;
    }
}

/**
 * 懒汉模式
 */
class Singleton2 {
    private static Singleton2 singleton;

    private Singleton2() {
    }

    public static Singleton2 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton2.class) {
                singleton = new Singleton2();
            }
        }

        return singleton;
    }
}


/**
 * 单例模式
 *
 * @author cclucky
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Singleton2.getSingleton());
        }
    }
}
