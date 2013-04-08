package com.thoughtworks;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BeanTest {
    @Test
    public void integerInstantiation() throws ClassNotFoundException {
        BasicBean bean = new BasicBean();
        bean.setValue(5);
        assertThat((Integer) bean.toInstance(), is(5));
    }

    @Test
    public void testName() throws Exception {
        CustomerBean mmBean = new CustomerBean();
        mmBean.setClazz(String.class);
        mmBean.setName("mm");
        mmBean.setValue(new String("hello"));

        CustomerBean nnBean = new CustomerBean();
        nnBean.setClazz(Integer.class);
        nnBean.setName("nn");
        nnBean.setValue(new Integer(5));


        CustomerBean barBean = new CustomerBean();
        barBean.setClazz(Bar.class);
        barBean.setName("bar");
        barBean.getParams().put(mmBean.getName(),mmBean);
        barBean.getParams().put(nnBean.getName(),nnBean);

        barBean.toInstance();

        CustomerBean ageBean = new CustomerBean();
        ageBean.setClazz(Integer.class);
        ageBean.setName("age");
        ageBean.setValue(28);

        CustomerBean fooBean = new CustomerBean();
        fooBean.setClazz(Foo.class);
        fooBean.setName("foo");
        fooBean.getParams().put(barBean.getName(), barBean);
        fooBean.getParams().put(ageBean.getName(), ageBean);
        fooBean.toInstance();

    }
}
