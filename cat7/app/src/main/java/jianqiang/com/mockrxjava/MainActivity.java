package jianqiang.com.mockrxjava;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);

        CatsHelper catsHelper = new CatsHelper(new ApiWrapper(new Api() {
            @Override
            public void queryCats(String query, CatsQueryCallback catsQueryCallback) {
                if (query == "abcd123") {
                    catsQueryCallback.onError(new IOException());
                    return;
                }

                ArrayList<Cat> list = new ArrayList<Cat>();
                list.add(new Cat());
                list.add(new Cat());
                catsQueryCallback.onSuccess(list);
            }

            @Override
            public void store(Cat cat, StoreCallback storeCallback) {
                if (cat.cuteness < 0) {
                    storeCallback.onError(new Exception());
                    return;
                }

                storeCallback.onSuccess("file://" + cat.cuteness);
            }
        }));

        catsHelper.saveTheCutestCat("abcd").start(new Callback<String>() {
            @Override
            public void onSuccess(String result) {
                                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Result")
                        .setMessage(String.valueOf(result))
                        .setPositiveButton("确定", null)
                        .show();
            }

            @Override
            public void onError(Exception e) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Error")
                        .setMessage(e.getMessage())
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
    }
}
