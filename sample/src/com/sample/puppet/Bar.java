package com.sample.puppet;

public class Bar {

    private String strProp;
    private Integer intProp;

    public String getStrProp() {
        return strProp;
    }

    public void setStrProp(String strProp) {
        this.strProp = strProp;
    }

    public Integer getIntProp() {
        return intProp;
    }

    public void setIntProp(Integer intProp) {
        this.intProp = intProp;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "strProp='" + strProp + '\'' +
                ", intProp=" + intProp +
                '}';
    }
}
