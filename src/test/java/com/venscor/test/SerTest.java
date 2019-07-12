package com.venscor.test;

import com.alibaba.fastjson.JSON;

public class SerTest {
    public  static  void main(String[]args){
        Person  p = new Person("venscor",25);
//        ,new School("XIDIAN"));
        System.out.println(JSON.toJSONString(p));
    }
}
