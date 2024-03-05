package com.example.lib;

public class DataBean<T,R> {
    public DataBean(T field1, R field2) {
        this.field1 = field1;
        this.field2 = field2;
        int i = 0;
        boolean accept = i > 1;
        if (accept) {
            System.out.println(accept);
        }
    }
    public T field1;
    public R field2;
}
