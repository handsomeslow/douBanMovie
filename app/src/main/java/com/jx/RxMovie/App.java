package com.jx.RxMovie;

import android.app.Application;

/**
 * Created by junxu on 2016/7/19.
 */
public class App extends Application {
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
