package com.thoughtworks;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class CustomerBean implements Bean {

    private String name;
    private Class clazz;
    private Object value;

    private Map<String, Bean> params = newHashMap();
    private Map<String, Bean> constructParams = newHashMap();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Map<String, Bean> getParams() {
        return params;
    }

    public void setParams(Map<String, Bean> params) {
        this.params = params;
    }

    public Map<String, Bean> getConstructParams() {
        return constructParams;
    }

    public void setConstructParams(Map<String, Bean> constructParams) {
        this.constructParams = constructParams;
    }

    @Override
    public Object toInstance() throws Exception {
        if (this.clazz == String.class || this.clazz == Integer.class) {
            return value;
        }
        Object object = clazz.newInstance();
        for (Map.Entry<String, Bean> entry : params.entrySet()) {
            Object property = entry.getValue().toInstance();
            String param = entry.getKey();
            param = param.substring(0, 1).toUpperCase() + param.substring(1);
            clazz.getMethod("set" + param, new Class[]{property.getClass()}).invoke(object, property);
        }
        return object;
    }

    public Object construct() {
        return null;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
