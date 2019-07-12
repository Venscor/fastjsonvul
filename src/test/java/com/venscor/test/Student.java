package com.venscor.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

import java.util.Properties;

public class Student {
    private String name;
    private int grade;
    private int num;
    private School school;
    private Properties _outputProperties;

    public  Properties getOutputProperties() {
        System.out.println("in getoutpro");
        return _outputProperties;
    }

    public void setSchoolName(String s ){
        System.out.println("in setSchoolName");
//        this._school.setName(s);
    }


    public void setTestName(String testName){
        System.out.println("in setTestName");
    }
    public String getTestName(String testName){
       return testName;
    }

    public String getName() {
        System.out.println("in getName");
        return name;
    }

    public School getSchool() {
        System.out.println("in get_school");
        return school;
    }



    public synchronized int getNum() {
        System.out.println("getNum");
        return num;
    }

    public synchronized void setNum(int num) {
        System.out.println("in setNum");
        this.num = num;
    }

    public Student() {
        System.out.println("Student no param constructor");
    }

    public Student(String name, int grade,int num,School school) {
        System.out.println("Student 3 param constructor");
        this.name = name;
        this.grade = grade;
        this.num = num;
        this.school = school;
    }

    void print(){
        System.out.println("name:"+name+"\ngrade:"+grade+"\nnum:"+num+"\nSchool:"+school.getName());
    }

    public  static void main(String[]args){
        ParserConfig config = new ParserConfig();
//        String s = "{\"name\":\"venscor\",\"grade\":3,\"num\":100,\"_outputProperties\":{}}";
        String s = "{\"name\":\"venscor\",\"grade\":3,\"num\":100,\"_school\":{},\"testName\":\"123\",\"schoolName\":\"xidian2\",\"world\":\"ttttt\",\"_outputProperties\":{}}";
        System.out.println(s);
        System.out.println("**********************");
        Student obj = (Student) JSON.parseObject(s, Student.class);
//        , config, Feature.SupportNonPublicField);

        System.out.println("-----------------------------");
        System.out.println(JSON.toJSONString(obj));
//        obj.print();
    }

    public  void setWorld(String s){
        System.out.println("in setWorld");

    }
}

