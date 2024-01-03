package com.cclucky.factory;

/**
 * 工厂模式
 * @author cclucky
 */
interface AbstractFactory {
    Phone createPhone();
    Pad createPad();
}

class HuaweiFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
        return new HuaweiPhone();
    }

    @Override
    public Pad createPad() {
        return new HuaweiPad();
    }
}

class AppleFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
        return new IPhone();
    }

    @Override
    public Pad createPad() {
        return new IPad();
    }
}

interface Phone {
    void sayMyName();
}

interface Pad {
    void sayMyName();
}

class HuaweiPhone implements Phone {

    @Override
    public void sayMyName() {
        System.out.println("my name is huawei phone");
    }
}

class HuaweiPad implements Pad {

    @Override
    public void sayMyName() {
        System.out.println("my name is huawei pad");
    }
}

class IPhone implements Phone {

    @Override
    public void sayMyName() {
        System.out.println("my name is iPhone");
    }
}

class IPad implements Pad {

    @Override
    public void sayMyName() {
        System.out.println("my name is iPad");
    }
}

/**
 * @author cclucky
 */
public class Factory {
    public static void main(String[] args) {
        Pad pad = new HuaweiFactory().createPad();
        pad.sayMyName();
    }
}
