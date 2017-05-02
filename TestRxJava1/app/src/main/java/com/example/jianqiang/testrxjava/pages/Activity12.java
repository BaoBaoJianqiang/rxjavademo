package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Activity12 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        testUnsubscribe();  //Unsubscribe
    }

    Observable<Integer> a1;
    Subscriber<Integer> b1;

    Observable<Integer> a2;
    Action1<Integer> b2;

    private void testUnsubscribe() {
        a1 = Observable.just(1, 2, 3, 4);
        b1 = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                int a = 1;
            }

            @Override
            public void onError(Throwable e) {
                int a = 1;

            }

            @Override
            public void onNext(Integer s) {
                int a = s;

            }
        };

        Subscription subscription = a1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(b1);

        Log.v("a1", String.valueOf(b1.isUnsubscribed()));
        b1.unsubscribe();
        Log.v("a1", String.valueOf(b1.isUnsubscribed()));


        a2 = Observable.just(1, 2, 3, 4);
        b2 = new Action1<Integer>() {
            @Override
            public void call(Integer s) {
                int a = s;
            }
        };

        Subscription subscription2 = a2.subscribe(b2);

        Log.v("a2", String.valueOf(subscription2.isUnsubscribed()));
        subscription2.unsubscribe();
        Log.v("a2", String.valueOf(subscription2.isUnsubscribed()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        a1.unsubscribeOn(Schedulers.io());
        b1.unsubscribe();
    }
}

