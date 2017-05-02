package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.Course;
import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;
import com.example.jianqiang.testrxjava.Utils;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

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

        Observable.fromArray(students)
                .flatMap(new Function<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> apply(Student student) throws Exception {
                        return Observable.fromIterable(student.getCourses());
                    }

                })
                .subscribe(course -> Log.d("baobao", course.getName()));
    }
}

