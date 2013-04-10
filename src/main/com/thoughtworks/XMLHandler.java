package com.thoughtworks;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;
import java.util.Stack;

import static com.google.common.collect.Maps.newHashMap;

public class XMLHandler extends DefaultHandler {

    private Bean currentBean;
    private Map<String, Bean> beanParams = newHashMap();
    private Map<String, Bean> constructParams = newHashMap();
    private Stack<Bean> beans = new Stack<Bean>();
    private Container container;

    public XMLHandler(Container container) {
        this.container = container;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String beanName = attributes.getValue("name");
        String value = attributes.getValue("value");
        String classString = attributes.getValue("class");
        String ref = attributes.getValue("ref");

        if (qName.equals("bean") || qName.equals("property") || qName.equals("construct")) {
            if (ref != null) {
                currentBean = container.getBeans().get(beanName);
            } else {
                currentBean = new Bean();
                if (value != null) {
                    currentBean.setValue(value);
                }
                if (classString != null) {
                    try {
                        currentBean.setClazz(Class.forName(classString));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                currentBean.setName(beanName);
            }
            beans.push(currentBean);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("bean")) {
            currentBean = beans.pop();
            if (currentBean.getValue() == null) {
                currentBean.setParams(beanParams);
                currentBean.setConstructParams(constructParams);
                beanParams.clear();
                constructParams.clear();
            }
            container.getBeans().put(currentBean.getName(), currentBean);
        }

        if (qName.equals("property")) {
            currentBean = beans.pop();
            beanParams.put(currentBean.getName(), currentBean);
        }

        if (qName.equals("construct")) {
            currentBean = beans.pop();
            constructParams.put(currentBean.getName(), currentBean);
        }

    }

}

