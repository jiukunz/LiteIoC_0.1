package com.thoughtworks;

public class StringUtils {

    public static<T> Object toPrimitive(Class<T> clazz, String param) throws Exception {
        if(clazz.equals(String.class)){
            return param;
        }
        return clazz.getMethod("valueOf", String.class).invoke(null,param);
    }

}
