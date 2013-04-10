package com.thoughtworks;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Context {
    private Map<String,Bean> beans = newHashMap();
    private Map<String,Bean> beansAlsoInParent = newHashMap();

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
        return beans.get(propName);
    }

    public void setParent(Context parent){
        for(Map.Entry<String,Bean> entry : parent.getBeans().entrySet()){
            if(beans.containsKey(entry.getKey())){
                beansAlsoInParent.put(entry.getKey(), entry.getValue());
            } else {
                beans.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map<String, Bean> getBeansAlsoInParent() {
        return beansAlsoInParent;
    }
}
