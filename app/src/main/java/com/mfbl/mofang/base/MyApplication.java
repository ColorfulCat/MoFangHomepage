package com.mfbl.mofang.base;

import android.app.Application;

import com.mfbl.mofang.util.MyConfig;

/**
 * Created by devislee on 16/8/27.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){

        MyConfig.initConfig(getApplicationContext());

    }
}
