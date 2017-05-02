package jianqiang.com.mockrxjava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class ApiWrapper {
    Api api;
    public ApiWrapper(Api api) {
        this.api = api;
    }

    public Observable<List<Cat>> queryCats(String query) {
        return Observable.create(new Observable.OnSubscribe<List<Cat>>() {

            @Override
            public void call(Subscriber<? super List<Cat>> subscriber) {
                api.queryCats(query, new Api.CatsQueryCallback() {
                    @Override
                    public void onSuccess(List<Cat> cats) {
                        subscriber.onNext(cats);
                    }

                    @Override
                    public void onError(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }
        });
    }

    public Observable<String> store(Cat cat) {
        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onSuccess(String uri) {
                        subscriber.onNext(uri);
                    }

                    @Override
                    public void onError(Exception e) {
                        subscriber.onError(e);
                    }
                });
            }
        });
    }
}