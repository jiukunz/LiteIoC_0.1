package com.thoughtworks;

import com.thoughtworks.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Bean {

    private String name;
    private Class clazz;
    private String value;

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

    public Object toInstance() throws Exception {
        Object object = clazz.newInstance();
        for (Map.Entry<String, Bean> entry : params.entrySet()) {

            String propName = entry.getKey();

            if(entry.getValue().isPrimitive()){
                Bean primitiveParam = entry.getValue();

                Object propValue = StringUtils.toPrimitive(getDeclaredType(propName), primitiveParam.getValue());

                setProperty(object, propName, propValue);
            } else {
                Object propValue = entry.getValue().toInstance();

                setProperty(object, propName, propValue);
            }
        }
        return object;
    }

    private Class<?> getDeclaredType(String propName) throws NoSuchFieldException {
        return this.clazz.getDeclaredField(propName).getType();
    }

    private void setProperty(Object object, String param, Object realParam) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        param = param.substring(0, 1).toUpperCase() + param.substring(1);
        clazz.getMethod("set" + param, new Class[]{realParam.getClass()}).invoke(object, realParam);
    }

    public Object construct() {
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private boolean isPrimitive(){
        return value != null;
    }
}
