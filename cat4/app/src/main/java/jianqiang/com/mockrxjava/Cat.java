package jianqiang.com.mockrxjava;

import android.graphics.Bitmap;

public class Cat implements Comparable<Cat>{
    Bitmap image;
    int cuteness;

    @Override
    public int compareTo(Cat another) {
        return Integer.compare(cuteness, another.cuteness);
    }
}
