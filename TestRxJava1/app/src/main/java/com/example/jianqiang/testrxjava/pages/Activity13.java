package com.example.jianqiang.testrxjava.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.testrxjava.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Activity13 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        //背压定义
        //背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略
        //简而言之，背压是流速控制的一种策略
        //背压策略的一个前提是异步环境，也就是说，被观察者和观察者处在不同的线程环境中

        //在2.0 中，Observable 不再支持背压，而Flowable 支持非阻塞式的背压

        test1();  //backpressure
        test2();  //响应式拉取（区别于默认的响应式推送）
        test3();  //流速控制1: 过滤/抛弃，例如Sample，ThrottleFirst
        test4();  //流速控制2: 缓存，例如buffer、window
        test5();  //onBackpressureDrop, 将observable发送的事件抛弃掉，直到subscriber再次调用request（n）方法的时候，就发送给它这之后的n个事件
        test6();  //onBackpressureBuffer, 把observable发送出来的事件做缓存，当request方法被调用的时候，给下层流发送一个item(如果给这个缓存区设置了大小，那么超过了这个大小就会抛出异常)
    }

    void test1() {
        //被观察者在主线程中，每1ms发送一个事件
        //被观察者发送事件的速度是观察者处理速度的1000倍
        //导致崩溃
        Observable.interval(1, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.newThread())
                //将观察者的工作放在新线程环境中
                .observeOn(Schedulers.newThread())
                //观察者处理每1000ms才处理一个事件
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.w("TAG", "---->" + aLong);
                    }
                });
    }

    void test2() {
        //被观察者将产生100000个事件
        //这里不使用interval，因为它不支持backpressure，所以不响应request(n)
        //range这类操作符是支持背压的, 它发送事件的速度可以被控制
        //RxJava 1.x，有的支持backpressure，有的不支持 ＝》RxJava 2.0统一解决之
        Observable.range(1, 100000)
                .observeOn(Schedulers.newThread())
                .subscribe(new MySubscriber());
    }

    class MySubscriber extends Subscriber<Integer> {
        @Override
        public void onStart() {
            //一定要在onStart中通知被观察者先发送一个事件
            request(1);
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Integer n) {
            //处理完毕之后，在通知被观察者发送下一个事件
            //如果你想取消这种backpressure 策略，调用quest(Long.MAX_VALUE)即可
            request(1);
        }
    }

    void test3() {
        Observable.interval(1, TimeUnit.MILLISECONDS)

                .observeOn(Schedulers.newThread())
                //这个操作符简单理解就是每隔200ms发送里时间点最近那个事件，
                //其他的事件浪费掉
                .sample(200,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.w("TAG","---->"+aLong);
                    }
                });
    }

    void test4() {
        Observable.interval(1, TimeUnit.MILLISECONDS)

                .observeOn(Schedulers.newThread())
                //这个操作符简单理解就是把100毫秒内的事件打包成list发送
                .buffer(100,TimeUnit.MILLISECONDS)
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> aLong) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.w("TAG","---->"+aLong.size());
                    }
                });
    }

    void test5() {
        Observable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Long>() {

                    @Override
                    public void onStart() {
                        Log.w("TAG","start");
//                        request(1);
                    }

                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR",e.toString());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.w("TAG","---->"+aLong);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    void test6() {
        Observable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer()
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Long>() {

                    @Override
                    public void onStart() {
                        Log.w("TAG","start");
//                        request(1);
                    }

                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR",e.toString());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.w("TAG","---->"+aLong);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}