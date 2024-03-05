package com.example.lib;

public class ChildTestClass extends TestClass {

    public ChildTestClass(int aaa) {
        super(aaa);
//        t = 1;
        System.out.println("ChildTestClass aaa:" + aaa);
    }


    @Override
    public void setAaa(int aaa) {
//        if (aaa > 10) {
//            System.out.println("ChildTestClass aaa>10");
//        } else {
//            System.out.println("ChildTestClass aaa<=10");
//        }
//
//        int i = aaa + 11222;
//        if (i>10) {
//            System.out.println("ChildTestClass i>10");
//        } else {
            System.out.println("ChildTestClass i<=10");
//        }
    }

    public void setAaa1(int aaa, int B, int C, int D) {

        switch (aaa) {
            case 1:
                System.out.println("ChildTestClass aaa==1");
                break;
            case 2:
                System.out.println("ChildTestClass aaa==2");
                break;
            case 3:
                System.out.println("ChildTestClass aaa==3");
                break;
            case 4:
                System.out.println("ChildTestClass aaa==4");
                break;
            case 5:
                System.out.println("ChildTestClass aaa==5");
                break;
            case 6:
                System.out.println("ChildTestClass aaa==6");
                break;
            case 7:
                System.out.println("ChildTestClass aaa==7");
                break;
            case 8:
                System.out.println("ChildTestClass aaa==8");
                break;
            case 9:
                System.out.println("ChildTestClass aaa==9");
                break;
            case 10:
                System.out.println("ChildTestClass aaa==10");
                break;
            default:
                System.out.println("ChildTestClass aaa>10");
                break;
        }
    }
}
