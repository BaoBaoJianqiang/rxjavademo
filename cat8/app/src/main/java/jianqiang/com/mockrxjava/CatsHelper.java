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

        AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);

        AsyncJob<Cat> cutestCatAsyncJob = catsListAsyncJob.map(new Func<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> cats) {
                return findCutest(cats);
            }
        });


//        AsyncJob<String> storedUriAsyncJob = new AsyncJob<String>() {
//            @Override
//            public void start(Callback<String> cutestCatCallback) {
//                cutestCatAsyncJob.start(new Callback<Cat>() {
//                    @Override
//                    public void onSuccess(Cat cutest) {
//                        apiWrapper.store(cutest)
//                                .start(new Callback<String>() {
//                                    @Override
//                                    public void onSuccess(String result) {
//                                        cutestCatCallback.onSuccess(result);
//                                    }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        cutestCatCallback.onError(e);
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        cutestCatCallback.onError(e);
//                    }
//                });
//            }
//        };

        AsyncJob<String> storedUriAsyncJob = cutestCatAsyncJob.flatMap(new Func<Cat, AsyncJob<String>>() {
            @Override
            public AsyncJob<String> call(Cat cat) {
                return apiWrapper.store(cat);
            }
        });

        return storedUriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
