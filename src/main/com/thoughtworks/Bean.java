package com.thoughtworks;

import com.thoughtworks.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

import static com.google.common.collect.Maps.newHashMap;

public class Bean {

    private static final String SET_PREFIX = "set";
    private String name;
    private Class clazz;
    private String value;
    private boolean isRef;
    private Context context;

    private Map<String, Bean> params = newHashMap();
    private Map<String, Bean> constructParams = newHashMap();

    public  Object toInstance() throws Exception {
        if(!params.isEmpty()){
            return constructFromSet();
        } else if (!constructParams.isEmpty()){
            return constructFromConstructor();
        }
        return StringUtils.toPrimitive(clazz,value);
    }

    public Object toInstance(Context context) throws Exception {
        this.context = context;
        if(!params.isEmpty()){
            return constructFromSet();
        } else if (!constructParams.isEmpty()){
            return constructFromConstructor();
        }
        return StringUtils.toPrimitive(clazz,value);
    }

    private Object constructFromSet() throws Exception {
        Object object = clazz.newInstance();
        for (Map.Entry<String, Bean> entry : params.entrySet()) {
            String propName = entry.getKey();
            Bean propBean = entry.getValue();
            if(propBean.isRef()){
                propBean = context.getBean(propName);
            }

            if(propBean.isPrimitive()){
                Object propValue = StringUtils.toPrimitive(getDeclaredType(propName), propBean.getValue());

                setProperty(object, propName, propValue);
            } else {
                Object propObject = propBean.toInstance();

                setProperty(object, propName, propObject);
            }
        }
        return object;
    }

    private Object constructFromConstructor() throws Exception {
        List<Object> cParams = newArrayList();
        Class[] paramTypes = new Class[constructParams.size()];
        int i = 0;
        for (Map.Entry<String,Bean> entry : constructParams.entrySet()){
            String paramName = entry.getKey();
            Bean cParam = entry.getValue();
            if(cParam.isRef()){
                cParam = context.getBean(paramName);
            }
            Object constructParam;
            if(cParam.isPrimitive()){
                constructParam = StringUtils.toPrimitive(getDeclaredType(cParam.getName()), cParam.getValue());
            } else {
                constructParam = cParam.toInstance();
            }
            cParams.add(constructParam);
            paramTypes[i++] = constructParam.getClass();
        }

        Constructor constructor = clazz.getDeclaredConstructor(paramTypes);
        return constructor.newInstance(cParams.toArray());
    }

    private void setProperty(Object object, String propName, Object realParam) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        clazz.getMethod(SET_PREFIX + StringUtils.capitalize(propName), new Class[]{realParam.getClass()}).invoke(object, realParam);
    }

    private Class<?> getDeclaredType(String propName) throws NoSuchFieldException {
        return this.clazz.getDeclaredField(propName).getType();
    }

    private boolean isPrimitive(){
        return value != null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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
        this.params.putAll(params);
    }

    public Map<String, Bean> getConstructParams() {
        return constructParams;
    }

    public void setConstructParams(Map<String, Bean> constructParams) {
        this.constructParams.putAll(constructParams);
    }

    public boolean isRef() {
        return isRef;
    }

    public void setRef(boolean ref) {
        isRef = ref;
    }

}
