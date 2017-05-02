package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.Course;
import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;
import com.example.jianqiang.testrxjava.Utils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

        Flowable.fromArray(students)
                .map(new Function<Student, List<Course>>() {

                    @Override
                    public List<Course> apply(Student student) throws Exception {
                        return student.getCourses();
                    }
                })
                .subscribe(
                        new Consumer<List<Course>>() {
                            @Override
                            public void accept(List<Course> courses) throws Exception {
                                for (int i = 0; i < courses.size(); i++) {
                                    Course course = courses.get(i);
                                    Log.d("baobao", course.getName());
                                }
                            }
                        }
                );
    }
}

