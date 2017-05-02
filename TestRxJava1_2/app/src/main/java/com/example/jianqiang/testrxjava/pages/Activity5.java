package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class Activity5 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test5();    //map变换，String => Student
    }

    private void test5() {
        Consumer<Student> consumer = new Consumer<Student>() {
            @Override
            public void accept(Student student) throws Exception {
                Log.d("baobao", student.getName());
            }
        };

        Flowable.just("baobao", "jianqiang")
                .map(new Function<String, Student>() {

                    @Override
                    public Student apply(String name) {
                        return new Student(name, null);
                    }
                })
                .subscribe(consumer);
    }
}

