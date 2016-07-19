package com.yifan.cms.demo.entity;

import java.io.Serializable;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Data 2016/5/18 20:59
 */
public class Student implements Serializable{
    private Integer id;
    private String name;
    private String password;
    private String number;
    private String clazz;
    private int age;
    private String sex;
    private String phone;


    public Student(String name, String password, String number, String clazz, int age, String sex, String phone) {
        this.name = name;
        this.password = password;
        this.number = number;
        this.clazz = clazz;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }

    public Student() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
