package com.thoughtworks;

import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class TestForXMLParser {
    @Test
    public void shouldParseXMLAndReturnBeanContainer() throws MalformedURLException, ClassNotFoundException {
        XMLParser parser = new XMLParser(new File("./src/test/resource/testBean.xml").toURI().toURL());

        Context result = parser.parse();

        Bean bar = result.getBeans().get("bar");
        assertThat(bar.getName(), is("bar"));
        assertThat(bar.getParams().get("intProp").getValue(), is("5"));
        assertThat(bar.getParams().get("strProp").getValue(), is("hello"));

        Bean foo = result.getBeans().get("foo");
        assertThat(foo.getName(), is("foo"));
        assertThat(foo.getParams().get("intProp").getValue(), is("29"));
        assertThat(foo.getParams().get("bar"), is(bar));

        Bean constructFoo = result.getBeans().get("constructFoo");
        assertThat(constructFoo.getName(), is("constructFoo"));
        assertThat(constructFoo.getConstructParams().get("intProp").getValue(), is("28"));
        assertThat(constructFoo.getConstructParams().get("bar"), is(bar));

    }

    @Test
    public void shouldParseXMLAndReturnBeanContainerWithRefBeans() throws MalformedURLException, ClassNotFoundException {
        XMLParser parser = new XMLParser(new File("./src/test/resource/testBeanWithRef.xml").toURI().toURL());

        Context result = parser.parse();

        Bean bar = result.getBeans().get("bar");
        assertThat(bar.getName(), is("bar"));
        assertThat(bar.getParams().get("intProp").getValue(), is("5"));
        assertThat(bar.getParams().get("strProp").getValue(), is("hello"));

        Bean foo = result.getBeans().get("foo");
        assertThat(foo.getName(), is("foo"));
        assertThat(foo.getParams().get("intProp").getValue(), is("29"));
        assertThat(foo.getParams().get("bar").isRef(), is(true));

        Bean constructFoo = result.getBeans().get("constructFoo");
        assertThat(constructFoo.getName(), is("constructFoo"));
        assertThat(constructFoo.getConstructParams().get("intProp").getValue(), is("28"));
        assertThat(constructFoo.getConstructParams().get("bar").isRef(), is(true));

    }


}
