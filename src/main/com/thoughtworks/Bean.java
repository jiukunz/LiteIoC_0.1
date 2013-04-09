package com.thoughtworks;

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
            if(entry.getValue().isPrimitive()){

                //将字符串参数转换成真正的参数对象
                String property = entry.getValue().getValue();
                Class realClass = this.clazz.getDeclaredField(entry.getKey()).getType();
                Object realParam = StringUtils.toPrimitive(realClass,property);

                String param = entry.getKey();
                param = param.substring(0, 1).toUpperCase() + param.substring(1);
                clazz.getMethod("set" + param, new Class[]{realParam.getClass()}).invoke(object, realParam);
            } else {
                Object realParam = entry.getValue().toInstance();

                String param = entry.getKey();
                param = param.substring(0, 1).toUpperCase() + param.substring(1);
                clazz.getMethod("set" + param, new Class[]{realParam.getClass()}).invoke(object, realParam);
            }
        }
        return object;
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
