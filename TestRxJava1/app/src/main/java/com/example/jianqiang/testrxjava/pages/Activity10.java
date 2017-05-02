package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import rx.Observable;
import rx.functions.Action1;

public class Activity10 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        simplifiedRxHelloWorld();               //简化写法
        simplifiedRxHelloWorldWithLambda();     //简化写法的Lambda版本
    }

    private void simplifiedRxHelloWorld() {
        Observable.just("Hello RxJava Simplified").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.v("baobao", s);
            }
        });
    }

    private void simplifiedRxHelloWorldWithLambda() {
        Observable.just("Hello RxJava Simplified with Lambda").subscribe(s -> Log.v("baobao", s));
    }
}

