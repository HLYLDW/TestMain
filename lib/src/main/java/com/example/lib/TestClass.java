package com.example.lib;

public class TestClass {
    int aaa = 0;
    public TestClass(int aaa) {
        this.aaa = aaa;
        System.out.println("new TestClass aaa:" + aaa);
    }

    public void setAaa(int aaa) {
        System.out.println("TestClass setAaa aaa:" + aaa);
        this.aaa = aaa;
    }
}
