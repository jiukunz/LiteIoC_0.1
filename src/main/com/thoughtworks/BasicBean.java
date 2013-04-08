package com.thoughtworks;

public class BasicBean implements Bean {

    private Object value;

    @Override
    public Object toInstance() {
        return value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
