package com.sample.puppet;

public class Foo {

    private Integer intProp;
    private Bar bar;

    //Required
    public Foo() {
    }

    public Foo(Integer intProp, Bar bar) {
        this.intProp = intProp;
        this.bar = bar;
    }

    public Integer getIntProp() {
        return intProp;
    }

    public void setIntProp(Integer intProp) {
        this.intProp = intProp;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "intProp=" + intProp +
                ", bar=" + bar +
                '}';
    }
}
