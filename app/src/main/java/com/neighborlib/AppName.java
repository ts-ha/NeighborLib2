package com.neighborlib;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.analytics.Tracker;
import com.google.firebase.crash.FirebaseCrash;
import com.neighborlib.common.SettingsStore;
import com.neighborlib.util.LogUtils;

import org.apache.log4j.Logger;

/**
 * Created by ts.ha on 2017-02-07.
 */

public class AppName extends Application {

    private static final String TAG = AppName.class.getSimpleName();
    private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        handleException();
        // Configure log
        LogUtils.configLog(this);
        // init settings preferences
        SettingsStore.init(this);
    }

    /**
     * 모든 에러를 firebase 로 보냄
     */
    private void handleException() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread thread, Throwable e) {
                FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
                FirebaseCrash.report(e);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_COMPLETE) {
           
        }else if(level == TRIM_MEMORY_UI_HIDDEN){
            Logger.getLogger(TAG).debug("TRIM_MEMORY_UI_HIDDEN");
        }
    }


}
