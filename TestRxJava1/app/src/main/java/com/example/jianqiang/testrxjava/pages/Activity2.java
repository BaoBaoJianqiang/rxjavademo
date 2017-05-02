package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;

import com.example.jianqiang.testrxjava.R;

import rx.Observable;

public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test2();        //使用just和from，构造Observable
    }

    private void test2() {
        Observable observable = Observable.just("Hello", "Hi", "Aloha");
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();

        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable2 = Observable.from(words);
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();
    }
}

