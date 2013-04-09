package com.thoughtworks;

import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class TestForXMLParser {
    @Test
    public void shouldParseXMLAndReturnBeanContainer() throws MalformedURLException, ClassNotFoundException {
        Container container = new Container();
        XMLParser parser = new XMLParser(new File("/src/test/resource/testBean.xml").toURI().toURL(),container);

        Container result = parser.parse();

        assertThat(result.getBeans().get("foo").getName(), is("foo"));
        assertThat(result.getBeans().get("bar").getName(), is("bar"));

    }


}
