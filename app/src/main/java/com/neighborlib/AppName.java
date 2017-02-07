package com.neighborlib;

import android.app.Application;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;
import com.neighborlib.common.SettingsStore;
import com.neighborlib.log4j.LogCatAppender;

import org.apache.log4j.Layout;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Created by ts.ha on 2017-02-07.
 */

public class AppName extends Application {

    private static final String TAG = AppName.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        configureLogCatAppender();
        handleException();

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

    /**
     * log4j 로그 패턴 설정
     * BuildConfig.DEBUG 인경우 로그 출력
     */
    private void configureLogCatAppender() {
        LogManager.getLoggerRepository().resetConfiguration();
        String logCatPattern = "%m [%F:%L]%n";
        final Logger root = Logger.getRootLogger();

        final Layout layout = new PatternLayout(logCatPattern);
        final LogCatAppender appender = new LogCatAppender(layout);

        root.addAppender(appender);
    }
}
