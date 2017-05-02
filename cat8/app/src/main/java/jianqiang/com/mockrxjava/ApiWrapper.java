package jianqiang.com.mockrxjava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiWrapper {
    Api api;
    public ApiWrapper(Api api) {
        this.api = api;
    }

    public AsyncJob<List<Cat>> queryCats(String query) {
        return new AsyncJob<List<Cat>>() {
            @Override
            public void start(Callback<List<Cat>> catsCallback) {
                api.queryCats(query, new Api.CatsQueryCallback() {
                    @Override
                    public void onSuccess(List<Cat> cats) {
                        catsCallback.onSuccess(cats);
                    }

                    @Override
                    public void onError(Exception e) {
                        catsCallback.onError(e);
                    }
                });
            }
        };
    }

    public AsyncJob<String> store(Cat cat) {
        return new AsyncJob<String>() {
            @Override
            public void start(Callback<String> uriCallback) {
                api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onSuccess(String uri) {
                        uriCallback.onSuccess(uri);
                    }

                    @Override
                    public void onError(Exception e) {
                        uriCallback.onError(e);
                    }
                });
            }
        };
    }
}