package com.thoughtworks.integration;

import com.thoughtworks.Bean;
import com.thoughtworks.Context;
import com.thoughtworks.puppet.Bar;
import com.thoughtworks.puppet.Foo;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContextAndBeanTest {
    @Test
    public void parentContext() throws Exception {
        Context context = new Context();
        Context parent = new Context();

        Bean barStrProp = new Bean();
        barStrProp.setName("strProp");
        barStrProp.setValue("hello");

        Bean barIntProp = new Bean();
        barIntProp.setName("intProp");
        barIntProp.setValue("5");

        Bean barBean = new Bean();
        barBean.setClazz(Bar.class);
        barBean.setName("bar");
        barBean.getParams().put(barStrProp.getName(), barStrProp);
        barBean.getParams().put(barIntProp.getName(), barIntProp);
        parent.addBean("bar", barBean);

        Bean fooStrProp = new Bean();
        fooStrProp.setClazz(Integer.class);
        fooStrProp.setName("intProp");
        fooStrProp.setValue("28");

        Bean barRefBean = new Bean();
        barRefBean.setName("bar");
        barRefBean.setRef(true);

        Bean fooBean = new Bean();
        fooBean.setClazz(Foo.class);
        fooBean.setName("foo");
        fooBean.getConstructParams().put(barBean.getName(), barRefBean);
        fooBean.getConstructParams().put(fooStrProp.getName(), fooStrProp);
        context.setParent(parent);
        Foo foo = (Foo) fooBean.toInstance(context);

        assertThat(foo.getIntProp(), is(28));
        assertThat(foo.getBar().getStrProp(), is("hello"));
        assertThat(foo.getBar().getIntProp(), is(5));
    }
}
