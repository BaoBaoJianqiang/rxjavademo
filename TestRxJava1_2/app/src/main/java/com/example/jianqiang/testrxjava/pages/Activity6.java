package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;
import com.example.jianqiang.testrxjava.Utils;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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


        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("baobao", s);
            }
        };

        Flowable.fromArray(students)
                .map(new Function<Student, String>() {

                    @Override
                    public String apply(Student student) throws Exception {
                        return student.getName();
                    }
                })
                .subscribe(consumer);
    }
}
