package com.example.david.gameengineandroid.Util;

import android.app.Application;
import android.content.Context;

/**
 * Created by David on 19/11/2015.
 */
public class AssetUtil extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}