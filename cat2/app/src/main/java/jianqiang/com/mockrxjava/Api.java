package jianqiang.com.mockrxjava;

import android.net.Uri;

import java.util.List;

public interface Api {
    //List<Cat> queryCats(String query);

    //化同步为异步
    interface CatsQueryCallback {
        void onSuccess(List<Cat> cats);
        void onError(Exception e);
    }

    //为原先的同步方法queryCats，加一个callback参数，变为异步
    void queryCats(String query, CatsQueryCallback catsQueryCallback);




    String store(Cat cat);
}
