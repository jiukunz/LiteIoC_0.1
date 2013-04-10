package com.thoughtworks;

import com.thoughtworks.puppet.Bar;
import com.thoughtworks.puppet.Foo;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BeanTest {

    @Test
    public void primitiveBeanAlsoMakeSense() throws Exception {
        Bean intBean = new Bean();
        intBean.setClazz(Integer.class);
        intBean.setValue("288");

        assertThat((Integer) intBean.toInstance(), is(288));

        Bean strBean = new Bean();
        strBean.setClazz(String.class);
        strBean.setValue("288");

        assertThat((String) strBean.toInstance(), is("288"));
    }

    @Test
    public void beanToInstanceOnlyHavePrimitiveProperty() throws Exception {
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

        Bar b = (Bar) barBean.toInstance();
        assertThat(b.getStrProp(), is("hello"));
        assertThat(b.getIntProp(), is(5));
    }

    @Test
    public void beanToInstanceHaveCustomObjectProperty() throws Exception {
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

        Bean fooStrProp = new Bean();
        fooStrProp.setClazz(Integer.class);
        fooStrProp.setName("intProp");
        fooStrProp.setValue("28");

        Bean fooBean = new Bean();
        fooBean.setClazz(Foo.class);
        fooBean.setName("foo");
        fooBean.getParams().put(barBean.getName(), barBean);
        fooBean.getParams().put(fooStrProp.getName(), fooStrProp);
        Foo foo = (Foo) fooBean.toInstance();

        assertThat(foo.getIntProp(), is(28));
        assertThat(foo.getBar().getStrProp(), is("hello"));
        assertThat(foo.getBar().getIntProp(), is(5));

    }

    @Test
    public void couldGetInstanceFromConstructor() throws Exception {
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

        Bean fooStrProp = new Bean();
        fooStrProp.setClazz(Integer.class);
        fooStrProp.setName("intProp");
        fooStrProp.setValue("28");

        Bean fooBean = new Bean();
        fooBean.setClazz(Foo.class);
        fooBean.setName("foo");
        fooBean.getConstructParams().put(barBean.getName(), barBean);
        fooBean.getConstructParams().put(fooStrProp.getName(), fooStrProp);
        Foo foo = (Foo) fooBean.toInstance();

        assertThat(foo.getIntProp(), is(28));
        assertThat(foo.getBar().getStrProp(), is("hello"));
        assertThat(foo.getBar().getIntProp(), is(5));

    }

    @Test
    public void couldUsingRefBean() throws Exception {
        Container container = new Container();

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
        container.addBean("bar", barBean);

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
        Foo foo = (Foo) fooBean.toInstance(container);

        assertThat(foo.getIntProp(), is(28));
        assertThat(foo.getBar().getStrProp(), is("hello"));
        assertThat(foo.getBar().getIntProp(), is(5));


    }
}
