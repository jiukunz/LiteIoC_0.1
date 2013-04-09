package com.thoughtworks;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;
import java.util.Stack;

import static com.google.common.collect.Maps.newHashMap;

public class XMLHandler extends DefaultHandler {

    private String currentValue;
    private Bean currentBean;
    private Map<String,Bean> beanParams = newHashMap();
    private Stack<Bean> beans = new Stack<Bean>();
    private Container container;

    public XMLHandler(Container container) {
        this.container = container;
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String beanName = attributes.getValue("name");
        String value = attributes.getValue("value");
        String classString = attributes.getValue("class");
        String ref = attributes.getValue("ref");

        if(qName.equals("bean") || qName.equals("property")) {

            if(ref != null) {
              currentBean = container.getBeans().get(beanName);
            } else
            {

                currentBean = new Bean();

                if(value != null) {
                    currentBean.setValue(value);
                }
                if(classString != null)
                {
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


        if(qName.equals("bean")) {
            currentBean = beans.pop();

            if(currentBean.getValue() == null) {

                currentBean.setParams(beanParams);
                beanParams.clear();

            }

            container.getBeans().put(currentBean.getName(), currentBean);
        }

        if(qName.equals("property")) {
            currentBean = beans.pop();
            beanParams.put(currentBean.getName(), currentBean);
        }

    }

    @Override
    public void characters(char ch[], int start, int length)throws SAXException {
    }
}

