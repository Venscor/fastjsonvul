package com.venscor.test;

/**
 * Created by web on 2017/4/18.
 */
import com.alibaba.fastjson.JSON;


@SuppressWarnings("serial")
public class Person extends PersonParent implements ParentInterface{
    private String name;
    private int age;
    public Person(){
        System.out.println("in no param constructor");
    }
    public Person(String str, int n){
        System.out.println("Inside Person's Constructor");
        name = str;
        age = n;
    }
   public String getName(){
        System.out.println("in getName");
        return name;
    }

    public int getAge(){
        System.out.println("in getAge");
        return age;
    }

//    public School getSchool() {
////        System.out.println("in getSchool");
//
//        return school;
//    }

//    public  void setSchool(School school) {
//        System.out.println("in setSchool");
//        this.school = school;
//    }

    public  void setName(String str){
        System.out.println("in setname");
        this.name = str;
    }
//
//    public  void setAge(int age){
//        System.out.println("in setAge");
//        this.age = age;
//    }

    public String test() {
        return null;
    }


    public static void main(String[] args){
        Person p = new Person("venscor",25);
        String s = JSON.toJSONString(p);
        System.out.println(s);
        System.out.println("******test fastjson str 2 obj******");
        Person pp = (Person) JSON.parseObject(s, Person.class);
        System.out.println(JSON.toJSONString(pp));

    }


}
