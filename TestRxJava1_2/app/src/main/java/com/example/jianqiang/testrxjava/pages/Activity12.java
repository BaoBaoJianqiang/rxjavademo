package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Activity12 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        testUnsubscribe();  //Unsubscribe
    }

    Flowable<Integer> a1;
    Subscriber<Integer> b1;

    Observable<Integer> a2;
    Consumer<Integer> b2;

    private void testUnsubscribe() {
        a1 = Flowable.just(1, 2, 3, 4);
        b1 = new Subscriber<Integer>() {
            @Override
            public void onComplete() {
                int a = 1;
            }

            @Override
            public void onError(Throwable e) {
                int a = 1;

            }

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Integer s) {
                int a = s;

            }
        };

        //subscribe一个Subscriber，返回值为void。没办法取消订阅
        a1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(b1);







        a2 = Observable.just(1, 2, 3, 4);
        b2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                int a = integer;
            }
        };

        //subscribe一个Consumer，返回值为Disposable。可以取消订阅
        Disposable subscription2 = a2.subscribe(b2);

        Log.v("a2", String.valueOf(subscription2.isDisposed()));
        subscription2.dispose();
        Log.v("a2", String.valueOf(subscription2.isDisposed()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        a1.unsubscribeOn(Schedulers.io());
    }
}

