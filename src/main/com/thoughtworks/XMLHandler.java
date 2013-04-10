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
    private Context context;
    private final String BEAN_ELEMENT = "bean";
    private final String PROPERTY_ELEMENT = "property";
    private final String CONSTRUCT_ELEMENT = "construct";
    private final String ATTR_VALUE = "value";
    private final String ATTR_NAME = "name";
    private final String ATTR_CLASS = "class";
    private final String ATTR_REF = "ref";

    public XMLHandler(Context context) {
        this.context = context;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String beanName = attributes.getValue(ATTR_NAME);
        String value = attributes.getValue(ATTR_VALUE);
        String classString = attributes.getValue(ATTR_CLASS);
        String ref = attributes.getValue(ATTR_REF);

        if (!isInNormalElement(qName)) {
            return;
        }

        if (ref != null) {
            if(context.getBeans().containsKey(beanName)) {
                currentBean = context.getBeans().get(beanName);
            } else {
                currentBean = new Bean();
                currentBean.setRef(true);
                currentBean.setName(beanName);
            }
        } else
        {
            currentBean = new Bean();
            currentBean.setValue(value);

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

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(!isInNormalElement(qName)) {
            return;
        }

        currentBean = beans.pop();

        if (isInBean(qName)) {
            if (currentBean.getValue() == null) {

                currentBean.setParams(beanParams);
                currentBean.setConstructParams(constructParams);

                beanParams.clear();
                constructParams.clear();
            }
            context.getBeans().put(currentBean.getName(), currentBean);
        }

        if (isInProperty(qName)) {
            beanParams.put(currentBean.getName(), currentBean);
        }

        if (isInConstruct(qName)) {
            constructParams.put(currentBean.getName(), currentBean);
        }

    }

    private boolean isInNormalElement(String qName) {
        return isInBean(qName) || isInProperty(qName) || isInConstruct(qName);
    }

    private boolean isInConstruct(String qName) {
        return qName.equals(CONSTRUCT_ELEMENT);
    }

    private boolean isInProperty(String qName) {
        return qName.equals(PROPERTY_ELEMENT);
    }

    private boolean isInBean(String qName) {
        return qName.equals(BEAN_ELEMENT);
    }

}

