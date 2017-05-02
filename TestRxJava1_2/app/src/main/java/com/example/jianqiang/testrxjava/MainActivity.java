package com.example.jianqiang.testrxjava;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jianqiang.testrxjava.pages.Activity1;
import com.example.jianqiang.testrxjava.pages.Activity10;
import com.example.jianqiang.testrxjava.pages.Activity11;
import com.example.jianqiang.testrxjava.pages.Activity12;
import com.example.jianqiang.testrxjava.pages.Activity2;
import com.example.jianqiang.testrxjava.pages.Activity3;
import com.example.jianqiang.testrxjava.pages.Activity4;
import com.example.jianqiang.testrxjava.pages.Activity5;
import com.example.jianqiang.testrxjava.pages.Activity6;
import com.example.jianqiang.testrxjava.pages.Activity7;
import com.example.jianqiang.testrxjava.pages.Activity8;
import com.example.jianqiang.testrxjava.pages.Activity9;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, Activity1.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, Activity2.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, Activity3.class));
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this, Activity4.class));
                break;
            case R.id.button5:
                startActivity(new Intent(MainActivity.this, Activity5.class));
                break;
            case R.id.button6:
                startActivity(new Intent(MainActivity.this, Activity6.class));
                break;
            case R.id.button7:
                startActivity(new Intent(MainActivity.this, Activity7.class));
                break;
            case R.id.button8:
                startActivity(new Intent(MainActivity.this, Activity8.class));
                break;
            case R.id.button9:
                startActivity(new Intent(MainActivity.this, Activity9.class));
                break;
            case R.id.button10:
                startActivity(new Intent(MainActivity.this, Activity10.class));
                break;
            case R.id.button11:
                startActivity(new Intent(MainActivity.this, Activity11.class));
                break;
            case R.id.button12:
                startActivity(new Intent(MainActivity.this, Activity12.class));
                break;
            default:
                break;
        }
    }
}
