package jianqiang.com.mockrxjava;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

public class CatsHelper {

    Api api;
    public CatsHelper(Api api) {
        this.api = api;
    }

    public String saveTheCutestCat(String query){
//        List<Cat> cats = api.queryCats(query);
//        Cat cutest = findCutest(cats);
//        String savedUri = api.store(cutest);
//        return savedUri;

        //通常要将上述语句，改写为try..catch...
        try{
            List<Cat> cats = api.queryCats(query);
            Cat cutest = findCutest(cats);
            String savedUri = api.store(cutest);
            return savedUri;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
