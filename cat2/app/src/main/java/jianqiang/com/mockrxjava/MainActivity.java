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

        CatsHelper catsHelper = new CatsHelper(new Api() {
//            @Override
//            public List<Cat> queryCats(String query) {
//                ArrayList<Cat> list = new ArrayList<Cat>();
//                list.add(new Cat());
//                list.add(new Cat());
//                return list;
//            }

            @Override
            public void queryCats(String query, CatsQueryCallback catsQueryCallback) {
                if(query == "abcd") {
                    catsQueryCallback.onError(new IOException());
                    return;
                }

                ArrayList<Cat> list = new ArrayList<Cat>();
                list.add(new Cat());
                list.add(new Cat());
                catsQueryCallback.onSuccess(list);
            }

            @Override
            public String store(Cat cat) {
                //跟进cat获取相应的Uri
                return "file://" + cat.cuteness;
            }
        });

        catsHelper.saveTheCutestCat("abcd", new CatsHelper.CutestCatCallback(){

            @Override
            public void onSuccess(String uri) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(uri)
                        .setMessage(uri)
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
