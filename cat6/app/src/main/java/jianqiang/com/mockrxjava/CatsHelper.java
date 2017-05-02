package jianqiang.com.mockrxjava;

import android.net.Uri;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class CatsHelper {

    ApiWrapper apiWrapper;
    public CatsHelper(ApiWrapper apiWrapper) {
        this.apiWrapper = apiWrapper;
    }

    //化同步为异步，增加一个callback参数
    public AsyncJob<String> saveTheCutestCat(String query) {

//        return new AsyncJob<String>() {
//
//            @Override
//            public void start(Callback<String> callback) {
//                apiWrapper.queryCats(query).start(new Callback<List<Cat>>() {
//                    @Override
//                    public void onSuccess(List<Cat> cats) {
//                        Cat cutest = findCutest(cats);
//                        apiWrapper.store(cutest)
//                                .start(new Callback<String>() {
//
//                                    @Override
//                                    public void onSuccess(String result) {
//                                        callback.onSuccess(result);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        callback.onError(e);
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        callback.onError(e);
//                    }
//                });
//            }
//        };


        AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        AsyncJob<Cat> cutestCatAsyncJob = new AsyncJob<Cat>() {
            @Override
            public void start(Callback<Cat> callback) {
                catsListAsyncJob.start(new Callback<List<Cat>>() {

                    @Override
                    public void onSuccess(List<Cat> result) {
                        callback.onSuccess(findCutest(result));
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };

        AsyncJob<String> storedUriAsyncJob = new AsyncJob<String>() {
            @Override
            public void start(Callback<String> cutestCatCallback) {
                cutestCatAsyncJob.start(new Callback<Cat>() {
                    @Override
                    public void onSuccess(Cat cutest) {
                        apiWrapper.store(cutest)
                                .start(new Callback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        cutestCatCallback.onSuccess(result);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        cutestCatCallback.onError(e);
                                    }
                                });
                    }

                    @Override
                    public void onError(Exception e) {
                        cutestCatCallback.onError(e);
                    }
                });
            }
        };
        return storedUriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
