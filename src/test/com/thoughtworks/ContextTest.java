package com.thoughtworks;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContextTest {
    @Test
    public void contextCanInheritParent() throws Exception {
        Context context = new Context();
        context.addBean("foo",new Bean());
        context.addBean("bar",new Bean());

        Context parent = new Context();
        parent.addBean("bar",new Bean());
        parent.addBean("foobar",new Bean());

        context.setParent(parent);
        assertThat(context.getBeans().containsKey("foobar"), is(true));
        assertThat(context.getBeansAlsoInParent().containsKey("bar"),is(true));
    }
}
