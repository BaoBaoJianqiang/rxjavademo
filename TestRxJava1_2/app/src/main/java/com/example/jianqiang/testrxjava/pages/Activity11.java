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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class Activity11 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        testConcat();   //concat
    }

    String data[] = {"data", "data"};

    void testConcat() {
        Flowable<String> disk = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                if (data[0] != null) {
                    e.onNext("abc");
                }

                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        Flowable<String> network = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                if (data[1] != null) {
                    e.onNext("123");
                }

                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);


        Flowable.concat(disk, network)
                .firstOrError()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.v("baobao", s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}

