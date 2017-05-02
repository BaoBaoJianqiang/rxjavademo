package com.example.jianqiang.testrxjava;

/**
 * Created by jianqiang on 16/9/16.
 */
public class Course {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Course(String name) {
        this.name = name;
    }
}
