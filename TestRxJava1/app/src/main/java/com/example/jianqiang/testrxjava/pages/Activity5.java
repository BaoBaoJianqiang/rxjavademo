package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class Activity5 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test5();    //map变换，String => Student
    }

    private void test5() {

        Subscriber<Student> subscriber = new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                Log.d("baobao", student.getName());
            }
        };

        Observable.just("baobao", "jianqiang")
                .map(new Func1<String, Student>() {

                    @Override
                    public Student call(String name) {
                        return new Student(name, null);
                    }
                })
                .subscribe(subscriber);
    }
}

