package com.thoughtworks;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Context {
    private Map<String,Bean> beans = newHashMap();
    private Context parent;

    public Map<String, Bean> getBeans() {
        return beans;
    }

    public void setBeans(Map<String, Bean> beans) {
        this.beans = beans;
    }

    public void addBean(String name,Bean bean){
        beans.put(name,bean);
    }

    public Bean getBean(String propName) {
        if(beans.containsKey(propName)){
            return beans.get(propName);
        }
        return parent.getBeans().get(propName);
    }

    public Context getParent() {
        return parent;
    }

    public void setParent(Context parent) {
        this.parent = parent;
    }
}
