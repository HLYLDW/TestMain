package com.example.lib;

public class TestClass implements ITest{
    int aaa = 0;
    int t = 1;
    public TestClass(int aaa) {
        this.aaa = aaa;
        System.out.println("new TestClass aaa:" + aaa);
    }

    public void setAaa(int aaa) {
        System.out.println("TestClass setAaa aaa:" + aaa);
        this.aaa = aaa;
        if (aaa > 10) {
            System.out.println("aaa>10");
        }
        else {
            System.out.println("aaa<=10");
        }
    }

}
