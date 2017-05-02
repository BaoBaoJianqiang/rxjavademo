package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jianqiang.testrxjava.Course;
import com.example.jianqiang.testrxjava.R;
import com.example.jianqiang.testrxjava.Student;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Activity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test1();    // observable订阅observer
        test2();    // observable也可以订阅subscriber
    }

    void test1() {
        // 创建observable
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava");
                subscriber.onNext("Hello baobao");
                subscriber.onCompleted();
            }
        });

        // observer
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.d("baobao", "completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("baobao", "completed");
            }

            @Override
            public void onNext(String s) {
                Log.d("baobao", "onNext: " + s);
            }
        };

        // observable订阅observer
        observable.subscribe(observer);
    }


    void test2() {
        // 创建observable
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava");
                subscriber.onNext("Hello baobao");
                subscriber.onCompleted();
            }
        });

        // 创建subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("baobao", s);
            }
        };

        // observable也可以订阅subscriber
        observable.subscribe(subscriber);
    }
}

