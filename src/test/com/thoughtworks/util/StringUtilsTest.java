package com.thoughtworks.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringUtilsTest {
    @Test
    public void shouldParseToAnyPrimitiveTypeFromString() throws Exception {
        String boolValue = "true";
        String intValue = "255";
        String strValue = "thoughtworks";
        String longValue = "2555555555555555555";
        String floatValue = "2.5F";
        String doubleValue = "2.55";
        String shortValue = "32767";
        String byteValue = "-127";

        assertThat((Boolean) StringUtils.toPrimitive(Boolean.class, boolValue), is(true));
        assertThat((Integer)StringUtils.toPrimitive(Integer.class,intValue),is(255));
        assertThat((String)StringUtils.toPrimitive(String .class,strValue),is("thoughtworks"));
        assertThat((Long)StringUtils.toPrimitive(Long.class,longValue),is(2555555555555555555L));
        assertThat((Float) StringUtils.toPrimitive(Float.class, floatValue), is(2.5F));
        assertThat((Double) StringUtils.toPrimitive(Double.class, doubleValue), is(2.55));
        assertThat((Short) StringUtils.toPrimitive(Short.class, shortValue), is((short) 32767));
        assertThat((Byte) StringUtils.toPrimitive(Byte.class, byteValue), is((byte)-127));
    }

    @Test
    public void shouldReturnTheFirstCharactorIfParseToCharFromString() throws Exception {
        String charValue = "cd";
        assertThat((Character)StringUtils.toPrimitive(Character.class,charValue),is('c'));
    }

    @Test
    public void shouldCapitalizeString() throws Exception {
        String str = "original";

        assertThat(StringUtils.capitalize(str),is("Original"));
    }
}
