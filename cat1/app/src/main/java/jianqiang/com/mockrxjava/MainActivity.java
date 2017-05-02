package jianqiang.com.mockrxjava;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);

        CatsHelper catsHelper = new CatsHelper(new Api() {
            @Override
            public List<Cat> queryCats(String query) {
                ArrayList<Cat> list = new ArrayList<Cat>();
                list.add(new Cat());
                list.add(new Cat());
                return list;
            }

            @Override
            public String store(Cat cat) {
                //跟进cat获取相应的Uri
                return "file://" + cat.cuteness;
            }
        });

        catsHelper.saveTheCutestCat("abcd");
    }
}
