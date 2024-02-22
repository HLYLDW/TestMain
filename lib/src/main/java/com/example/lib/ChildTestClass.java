package com.example.lib;

public class ChildTestClass extends TestClass {
    public ChildTestClass(int aaa) {
        super(aaa);
        System.out.println("ChildTestClass aaa:" + aaa);
    }


    @Override
    public void setAaa(int aaa) {
        super.setAaa(aaa);
    }
}
