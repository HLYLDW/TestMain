package com.example.lib;

public class DataBean<T,R> {
    public DataBean(T field1, R field2) {
        this.field1 = field1;
        this.field2 = field2;
    }
    public T field1;
    public R field2;
}
