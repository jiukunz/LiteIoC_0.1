package com.thoughtworks;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Container  {
    private Map<String,Bean> beans = newHashMap();

    public Map<String, Bean> getBeans() {
        return beans;
    }

    public void setBeans(Map<String, Bean> beans) {
        this.beans = beans;
    }
}
