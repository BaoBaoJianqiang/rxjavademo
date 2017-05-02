package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class Activity3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        //使用Action和Action1
        test3_1();  //只有onNext
        test3_2();  //onNext, onError
        test3_3();  //onNext, onError, onCompleted
    }

    private void test3_1() {
        Observable observable = Observable.just("Hello", "Hi", "Aloha");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("baobao", s);
            }
        };

        observable.subscribe(onNextAction);
    }

    private void test3_2() {
        Observable observable = Observable.just("Hello", "Hi", "Aloha");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("baobao", s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                //error handling
            }
        };

        observable.subscribe(onNextAction, onErrorAction);
    }

    private void test3_3() {
        Observable observable = Observable.just("Hello", "Hi", "Aloha");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("baobao", s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                //error handling
            }
        };

        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                Log.d("baobao", "completed");
            }
        };

        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }
}

