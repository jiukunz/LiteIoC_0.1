package com.thoughtworks;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class CustomerBean implements Bean {

    private String name;
    private Class clazz;

    private Map<String,Bean> params = newHashMap();
    private Map<String,Bean> constructParams = newHashMap();

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
    public Object toObject() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object construct() {
        return null;
    }

}
