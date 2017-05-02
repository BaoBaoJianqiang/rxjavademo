package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;

import com.example.jianqiang.testrxjava.R;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test2();        //使用just和from，构造Flowable
    }

    private void test2() {
        Flowable flowable = Flowable.just("Hello", "Hi", "Aloha");
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();

        String[] words = {"Hello", "Hi", "Aloha"};
        Flowable flowable1 = Flowable.fromArray(words);   //从1到2：from改为fromArray
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();
    }
}

