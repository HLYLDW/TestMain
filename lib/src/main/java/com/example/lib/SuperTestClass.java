package com.example.lib;

public class SuperTestClass extends TestClass {
    public SuperTestClass(int aaa) {
        super(aaa);
        System.out.println("new SuperTestClass aaa:" + aaa);
    }

    @Override
    public void setAaa(int aaa) {
        super.setAaa(aaa);
        System.out.println("SuperTestClass setAaa aaa:" + aaa);
    }
}
