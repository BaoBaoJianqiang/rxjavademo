package jianqiang.com.mockrxjava;

import java.util.Collections;
import java.util.List;

public interface Callback<T> {
    void onSuccess(T result);
    void onError(Exception e);
}