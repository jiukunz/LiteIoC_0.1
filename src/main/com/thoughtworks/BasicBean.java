package com.thoughtworks;

public class BasicBean implements Bean {
    private String name;
    private Object value;

    @Override
    public Object toInstance() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
