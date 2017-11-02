package com.example.xjl.demo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by xjl on 2017/10/29.
 */

public class CombankDroid extends Application {
    boolean isLogin=false;
    public boolean isLogin(){
        return isLogin;
    }
    public void setLogin(Boolean Login){
        isLogin=Login;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
