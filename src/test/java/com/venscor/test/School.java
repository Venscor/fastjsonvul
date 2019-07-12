package com.venscor.test;

import com.alibaba.fastjson.JSON;

public class School {
    private String name;

    public String getName() {
        System.out.println("in  Scheool : getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("in School.setName");
        this.name = name;
    }

    public School(String name) {
        this.name = name;
    }

    public School() {
        System.out.println("School no param constructor");
    }

    public static void main(String[]args){
        String json = "{\"@type\":\"com.venscor.test.School\",\"name\":\"venscor\"}";
        JSON.parseObject(json);
//        System.out.println(s.getName());
    }
}
