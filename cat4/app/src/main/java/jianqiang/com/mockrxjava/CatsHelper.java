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
    public void saveTheCutestCat(String query, Callback cutestCatCallback){
        apiWrapper.queryCats(query, new Callback<List<Cat>>() {
            @Override
            public void onSuccess(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                //往下传递
                apiWrapper.store(cutest, cutestCatCallback);
            }

            @Override
            public void onError(Exception e) {
                cutestCatCallback.onError(e);
            }
        });
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
