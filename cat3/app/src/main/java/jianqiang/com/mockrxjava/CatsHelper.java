package jianqiang.com.mockrxjava;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

public class CatsHelper {
    //新增回调
    public interface CutestCatCallback {
        void onSuccess(String uri);
        void onError(Exception e);
    }

    Api api;
    public CatsHelper(Api api) {
        this.api = api;
    }

//    public Uri saveTheCutestCat(String query){
//        try{
//            List<Cat> cats = api.queryCats(query);
//            Cat cutest = findCutest(cats);
//            Uri savedUri = api.store(cutest);
//            return savedUri;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    //化同步为异步，增加一个callback参数
    public void saveTheCutestCat(String query, CutestCatCallback cutestCatCallback){
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onSuccess(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                api.store(cutest, new Api.StoreCallback() {

                    @Override
                    public void onSuccess(String uri) {
                        cutestCatCallback.onSuccess(uri);
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

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
