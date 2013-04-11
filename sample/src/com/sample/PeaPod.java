package com.sample;

import com.sample.puppet.Bar;
import com.sample.puppet.Foo;
import com.sample.puppet.FooBar;
import com.thoughtworks.Context;
import com.thoughtworks.XMLParser;

import java.io.File;

public class PeaPod {
    public static void main(String[] args) throws Exception {
        XMLParser parentParser = new XMLParser(new File("./sample/resource/parentBean.xml").toURI().toURL());
        XMLParser parser = new XMLParser(new File("./sample/resource/testBean.xml").toURI().toURL());

        Context parentResult = parentParser.parse();
        Context result = parser.parse();
        result.setParent(parentResult);

        Foo foo = (Foo) result.getBean("foo").toInstance(result);
        System.out.println("Construct foo with set methods:");
        System.out.println(foo);

        System.out.println("\n*******************************************************************\n");

        Bar bar = (Bar) result.getBean("bar").toInstance(result);
        System.out.println("Construct bar with set methods:");
        System.out.println(bar);

        System.out.println("\n*******************************************************************\n");

        foo = (Foo) result.getBean("constructFoo").toInstance(result);
        System.out.println("Construct constructFoo with constructor:");
        System.out.println(foo);

        System.out.println("\n*******************************************************************\n");

        int i = (Integer) result.getBeans().get("primitiveBean").toInstance(result);
        System.out.println("Construct primitive Bean:");
        System.out.println(i);

        System.out.println("\n*******************************************************************\n");

        FooBar fooBar = (FooBar) result.getBean("foobar").toInstance(result);
        System.out.println("Construct foobar only in parent context");
        System.out.println(fooBar);

        System.out.println("\n*******************************************************************\n");

        FooBar superFooBar = (FooBar) result.getBean("superFooBar").toInstance(result);
        System.out.println("Construct foobar using alias superFooBar");
        System.out.println(superFooBar);

    }
}
