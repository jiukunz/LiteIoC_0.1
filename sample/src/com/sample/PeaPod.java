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
        Context parentResult = parentParser.parse();

        XMLParser parser = new XMLParser(new File("./sample/resource/testBean.xml").toURI().toURL());

        Context result = parser.parse();

        result.setParent(parentResult);
        parentResult.setParent(result);

        Foo foo = (Foo) result.getBean("foo").toInstance(result);
        System.out.println("Foo:");
        System.out.println(foo.getBar().getIntProp());
        System.out.println(foo.getBar().getStrProp());
        System.out.println(foo.getIntProp());

        Bar bar = (Bar) result.getBean("bar").toInstance(result);
        System.out.println("Bar:");
        System.out.println(bar.getStrProp());
        System.out.println(bar.getIntProp());

        foo = (Foo) result.getBean("constructFoo").toInstance(result);
        System.out.println("ConstructFoo:");
        System.out.println(foo.getBar().getIntProp());
        System.out.println(foo.getBar().getStrProp());
        System.out.println(foo.getIntProp());

        int i = (Integer) result.getBeans().get("primitiveBean").toInstance(result);
        System.out.println("Integer Bean:");
        System.out.println(i);

        FooBar fooBar = (FooBar) result.getBean("foobar").toInstance(result);
        System.out.println("FooBar Bean");
        System.out.println(fooBar.getFoo().getBar().getIntProp());
        System.out.println(fooBar.getFoo().getBar().getStrProp());
        System.out.println(fooBar.getFoo().getIntProp());

        FooBar superFooBar = (FooBar) result.getBean("superFooBar").toInstance(result);
        System.out.println("super FooBar Bean");
        System.out.println(superFooBar.getFoo().getBar().getIntProp());
        System.out.println(superFooBar.getFoo().getBar().getStrProp());
        System.out.println(superFooBar.getFoo().getIntProp());

    }
}
