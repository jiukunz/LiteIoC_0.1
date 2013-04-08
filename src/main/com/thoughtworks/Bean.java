package com.thoughtworks;

public interface Bean {
    Object toInstance() throws InstantiationException, IllegalAccessException, NoSuchMethodException, Exception;
}
