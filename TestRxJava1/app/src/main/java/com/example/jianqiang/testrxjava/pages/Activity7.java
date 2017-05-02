package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.Course;
import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;
import com.example.jianqiang.testrxjava.Utils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class Activity7 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test7();    //flatMap变换：旧写法
    }


    void test7() {
        Student student1 = Utils.prepareData(1);
        Student student2 = Utils.prepareData(2);

        Student[] students = {student1, student2};

        Subscriber<Student> subscriber = new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                List<Course> courses = student.getCourses();
                for (int i = 0; i < courses.size(); i++) {
                    Course course = courses.get(i);
                    Log.d("baobao", course.getName());
                }
            }
        };

        Observable.from(students)
                .subscribe(subscriber);
    }
}

