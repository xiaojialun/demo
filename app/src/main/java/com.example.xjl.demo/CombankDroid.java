package com.example.xjl.demo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import grpc.demo.Client.User;


/**
 * Created by xjl on 2017/10/29.
 */

public class CombankDroid extends Application {

    private User user;
    private boolean isLogin=false;
    public boolean isLogin(){
        return isLogin;
    }
    public void setLogin(Boolean Login){
        isLogin=Login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
