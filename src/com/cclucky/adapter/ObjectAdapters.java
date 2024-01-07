package com.cclucky.adapter;

/**
 * 适配者模式：对象适配器模式
 */

// 目标接口
interface Target2 {
    void request();
}

class Adapter2 {
    public void specificRequest() {
        System.out.println("被适配者的方法");
    }
}

/**
 * 对象适配器
 * @author cclucky
 */
public class ObjectAdapters implements Target2 {

    private final Adapter2 adapter2;

    public ObjectAdapters(Adapter2 adapter2) {
        this.adapter2 = adapter2;
    }

    @Override
    public void request() {
        adapter2.specificRequest();
    }

    public static void main(String[] args) {
        new ObjectAdapters(new Adapter2()).request();
    }
}