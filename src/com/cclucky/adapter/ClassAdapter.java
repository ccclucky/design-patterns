package com.cclucky.adapter;

/**
 * 适配者模式：类适配器模式
 */

// 目标接口
interface Target {
    void request();
}

// 被适配者
class Adapter {
    public void specificRequest() {
        System.out.println("被适配者的方法");
    }
}

class Adaptee {
    public void specificRequest() {
        System.out.println("被适配者的方法========");
    }
}


/**
 * @author cclucky
 */
public class ClassAdapter extends Adapter implements Target {
    public static void main(String[] args) {
        new ClassAdapter().request();
    }

    @Override
    public void request() {
        specificRequest();
    }
}
