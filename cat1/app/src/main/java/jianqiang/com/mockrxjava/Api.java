package jianqiang.com.mockrxjava;

import android.net.Uri;

import java.util.List;

public interface Api {
    //查询
    List<Cat> queryCats(String query);

    //存储
    String store(Cat cat);
}
