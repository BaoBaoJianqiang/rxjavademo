package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;

import com.example.jianqiang.testrxjava.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class Activity11 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        testConcat();   //concat
    }

    String data[] = {"data", "data"};

    void testConcat() {
        Observable<String> disk = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                if (data[0] != null) {
                    subscriber.onNext("abc");
                }

                subscriber.onCompleted();
            }
        });

        Observable<String> network = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (data[1] != null) {
                    subscriber.onNext("123");
                }

                subscriber.onCompleted();
            }
        });
        ;

        Observable.concat(disk, network)
                .first()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                int a = 1;
                            }

                            @Override
                            public void onError(Throwable e) {
                                int a = 1;

                            }

                            @Override
                            public void onNext(String s) {
                                int a = 1;

                            }
                        }
                );
    }
}

