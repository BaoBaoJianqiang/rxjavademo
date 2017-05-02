package jianqiang.com.mockrxjava;

import android.net.Uri;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import rx.Observable;

public class CatsHelper {

    ApiWrapper apiWrapper;
    public CatsHelper(ApiWrapper apiWrapper) {
        this.apiWrapper = apiWrapper;
    }

    //化同步为异步，增加一个callback参数
    public Observable<String> saveTheCutestCat(String query) {

        Observable<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);


        Observable<Cat> cutestCatAsyncJob = catsListAsyncJob.map(cats -> findCutest(cats));

        Observable<String> storedUriAsyncJob = cutestCatAsyncJob.flatMap(cat -> apiWrapper.store(cat));

        return storedUriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
