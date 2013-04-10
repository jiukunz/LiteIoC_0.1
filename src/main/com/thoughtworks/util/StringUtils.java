package com.thoughtworks.util;

public class StringUtils {

    public static<T> Object toPrimitive(Class<T> clazz, String param) throws Exception {
        if(clazz.equals(String.class)){
            return param;
        } else if (clazz.equals(Character.class)){
            return param.charAt(0);
        }
        return clazz.getMethod("valueOf", String.class).invoke(null,param);
    }

    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
