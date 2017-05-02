package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Activity4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        test4();    //线程切换：subscribeOn和observeOn
    }

    private void test4() {
        Flowable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())               // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())  // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer number) throws Exception {
                        Log.d("baobao", "number:" + number);
                    }
                });
    }
}

