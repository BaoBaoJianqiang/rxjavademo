package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.Course;
import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;
import com.example.jianqiang.testrxjava.Utils;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class Activity8 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test8();    //flatMap变换：新写法
    }

    void test8() {
        Student student1 = Utils.prepareData(1);
        Student student2 = Utils.prepareData(2);

        Student[] students = {student1, student2};

        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d("baobao", course.getName());
            }
        };

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(subscriber);
    }
}

