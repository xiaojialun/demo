package com.example.xjl.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xjl on 2017/10/25.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CombankDroid combankDroid= (CombankDroid) getApplication();
        if(combankDroid.isLogin){
            startActivity(new Intent(this,IndexActivity.class));
        }else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
