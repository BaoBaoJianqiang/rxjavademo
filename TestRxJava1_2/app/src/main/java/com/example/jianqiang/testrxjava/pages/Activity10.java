package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class Activity10 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

//        simplifiedRxHelloWorld();               //简化写法
        simplifiedRxHelloWorldWithLambda();     //简化写法的Lambda版本
    }

    private void simplifiedRxHelloWorld() {
        Flowable<String> flowable = Flowable.just("hello RxJava 2");

        Consumer consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.v("baobao", s);
            }
        };

        flowable.subscribe(consumer);
    }

    private void simplifiedRxHelloWorldWithLambda() {
        Flowable.just("hello RxJava 2").subscribe(s -> Log.v("baobao", s));
    }
}

