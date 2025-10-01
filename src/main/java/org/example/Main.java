package org.example;

public class Main {
    public static void main(String[] args) {

        Injector<SomeBean> injector = new Injector<>();
        SomeBean sb_old = injector.inject(new SomeBean());
        Class<?> objClass_old = sb_old.getClass();
        System.out.println(objClass_old);
        sb_old.foo();

        SomeBean sb = (new Injector<SomeBean>()).inject(new SomeBean());
        Class<?> objClass = sb_old.getClass();
        System.out.println(objClass);
        sb.foo();

    }
}