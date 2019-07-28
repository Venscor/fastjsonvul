package com.venscor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * @ClassName VulDemo58
 * @Description TODO
 * @Author wangyu89
 * @Create Time 2019-07-26 17:07
 * @Version 1.0
 */
public class VulDemo58 {
    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true"); // 高版本jdk对jdni注入有限制，jdk8u122及以上

        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

        String payload = PoC.payload58();
        JSON.parseObject(payload);
    }
}
