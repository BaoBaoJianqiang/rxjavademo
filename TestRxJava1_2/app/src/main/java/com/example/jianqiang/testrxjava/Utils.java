package com.example.jianqiang.testrxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianqiang on 16/9/16.
 */
public class Utils {
    public static Student prepareData(int type) {
        if(type == 1) {
            Course course1 = new Course("语文");
            Course course2 = new Course("数学");
            Course course3 = new Course("外语");
            List<Course> courses = new ArrayList<Course>();
            courses.add(course1);
            courses.add(course2);
            courses.add(course3);

            return new Student("baobao", courses);
        } else {
            Course course1 = new Course("体育");
            Course course2 = new Course("地理");
            Course course3 = new Course("历史");
            List<Course> courses = new ArrayList<Course>();
            courses.add(course1);
            courses.add(course2);
            courses.add(course3);

            return new Student("jianqiang", courses);
        }
    }
}
