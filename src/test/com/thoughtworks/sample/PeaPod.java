package com.thoughtworks.sample;

import com.thoughtworks.Container;
import com.thoughtworks.XMLParser;
import com.thoughtworks.puppet.Bar;
import com.thoughtworks.puppet.Foo;

import java.io.File;

public class PeaPod {
    public static void main(String[] args) throws Exception {
        XMLParser parser = new XMLParser(new File("./src/test/resource/testBean.xml").toURI().toURL());

        Container result = parser.parse();

        Foo foo = (Foo) result.getBeans().get("foo").toInstance();
        System.out.println("Foo:");
        System.out.println(foo.getBar().getIntProp());
        System.out.println(foo.getBar().getStrProp());
        System.out.println(foo.getIntProp());

        Bar bar = (Bar) result.getBeans().get("bar").toInstance();
        System.out.println("Bar:");
        System.out.println(bar.getStrProp());
        System.out.println(bar.getIntProp());

        foo = (Foo) result.getBeans().get("constructFoo").toInstance();
        System.out.println("ConstructFoo:");
        System.out.println(foo.getBar().getIntProp());
        System.out.println(foo.getBar().getStrProp());
        System.out.println(foo.getIntProp());

        int i = (Integer) result.getBeans().get("primitiveBean").toInstance();
        System.out.println("Integer Bean:");
        System.out.println(i);

    }
}
