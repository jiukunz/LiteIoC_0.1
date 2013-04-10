package com.thoughtworks.puppet;

public class FooBar {
    private Foo foo;
    private Bar bar;

    public FooBar(Foo foo, Bar bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public Foo getFoo() {
        return foo;
    }

    public void setFoo(Foo foo) {
        this.foo = foo;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
