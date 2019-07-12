package com.venscor.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

import java.io.IOException;
import java.util.HashMap;

import static java.lang.Runtime.getRuntime;

public class TestFastjson {
    public static void main(String args[]) {
        ParserConfig config = new ParserConfig();
        HashMap map = new HashMap();
        map.put("1", new Integer(1));
        map.put("2", new Integer(2));
        try {
            map.put("3", getRuntime().exec("ping -n 3 192.168.3.103"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(map));

    }

}
