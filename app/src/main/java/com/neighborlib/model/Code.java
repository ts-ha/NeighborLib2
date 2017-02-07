package com.neighborlib.model;


public class Code {

    private String code;
    private String name;

    public Code() {
    }

    public Code(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Code [code=" + code + ", name=" + name + "]";
    }


}
