package jianqiang.com.mockrxjava;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

public class CatsHelper {

    ApiWrapper apiWrapper;

    public CatsHelper(ApiWrapper apiWrapper) {
        this.apiWrapper = apiWrapper;
    }

    //化同步为异步，增加一个callback参数
    public AsyncJob<String> saveTheCutestCat(String query) {
//        apiWrapper.queryCats(query, new Callback<List<Cat>>() {
//            @Override
//            public void onSuccess(List<Cat> cats) {
//                Cat cutest = findCutest(cats);
//                //往下传递
//                apiWrapper.store(cutest, cutestCatCallback);
//            }
//
//            @Override
//            public void onError(Exception e) {
//                cutestCatCallback.onError(e);
//            }
//        });

        return new AsyncJob<String>() {

            @Override
            public void start(Callback<String> callback) {
                apiWrapper.queryCats(query).start(new Callback<List<Cat>>() {
                    @Override
                    public void onSuccess(List<Cat> cats) {
                        Cat cutest = findCutest(cats);
                        apiWrapper.store(cutest)
                                .start(new Callback<String>() {

                                    @Override
                                    public void onSuccess(String result) {
                                        callback.onSuccess(result);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        callback.onError(e);
                                    }
                                });
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };

    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
