package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class Activity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test();    // observable订阅subscriber
    }

    void test() {
        //发射了一个字符串"hello RxJava 2"
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("hello RxJava 2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        // 创建subscriber
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.v("baobao", "onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                Log.v("baobao", s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.v("baobao", "onComplete");
            }
        };

        //组装发射器和接收器
        flowable.subscribe(subscriber);
    }
}

