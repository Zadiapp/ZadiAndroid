package com.zadi.zadi.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.crashlytics.android.Crashlytics;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        PrefsManager.init(this);
    }

    public static void changeLang(Context context) {
        Locale myLocale;

        if (PrefsManager.getInstance().getLang() != null) {
            myLocale = new Locale(PrefsManager.getInstance().getLang());
        } else {
            myLocale = new Locale(Locale.getDefault().getLanguage());
        }

        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        changeLang(this);
    }
}
