package com.thoughtworks;

public class BasicBean implements Bean {

    private String name;
    private Class clazz;
    private Object value;

    @Override
    public Object toObject() {
        return value;
    }
}
