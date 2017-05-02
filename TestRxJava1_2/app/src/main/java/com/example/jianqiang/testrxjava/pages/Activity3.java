package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class Activity3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

//        Rx1.0-----------Rx2.0
//
//        Action0--------Action
//        Action1--------Consumer
//        Action2--------BiConsumer
//        后面的Action都去掉了，只保留了ActionN

        //使用Action和Action1
        test3_1();  //只有onNext
        test3_2();  //onNext, onError
        test3_3();  //onNext, onError, onCompleted
    }

    private void test3_1() {
        Flowable flowable = Flowable.just("Hello", "Hi", "Aloha");

        Consumer<String> onNextAction = new Consumer<String>() {

            @Override
            public void accept(String s) throws Exception {
                Log.d("baobao", s);
            }
        };

        flowable.subscribe(onNextAction);
    }

    private void test3_2() {
        Flowable flowable = Flowable.just("Hello", "Hi", "Aloha");

        Consumer<String> onNextAction = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("baobao", s);
            }
        };

        Consumer<Throwable> onErrorAction = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //error handling
            }
        };

        flowable.subscribe(onNextAction, onErrorAction);
    }

    private void test3_3() {
        Flowable flowable = Flowable.just("Hello", "Hi", "Aloha");

        Consumer<String> onNextAction = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("baobao", s);
            }
        };

        Consumer<Throwable> onErrorAction = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //error handling
            }
        };

        Action onCompletedAction = new Action() {
            @Override
            public void run() throws Exception {
                Log.d("baobao", "completed");
            }
        };

        flowable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }
}

