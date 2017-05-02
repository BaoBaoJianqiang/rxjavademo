package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;
import com.example.jianqiang.testrxjava.Utils;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class Activity6 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test6();    //map变换，Student => String
    }

    private void test6() {
        Student student1 = Utils.prepareData(1);
        Student student2 = Utils.prepareData(2);

        Student[] students = {student1, student2};

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("baobao", s);
            }
        };

        Observable.from(students)
                .map(new Func1<Student, String>() {

                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .subscribe(subscriber);
    }
}

