package com.zadi.zadi.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings.Secure;
import com.crashlytics.android.Crashlytics;
import com.zadi.zadi.network.APIsFactory;
import com.zadi.zadi.network.APIsServices;

import io.fabric.sdk.android.Fabric;

import java.util.Locale;

public class App extends Application {
    private static APIsServices services;
    private static String userId;

    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        PrefsManager.init(this);
        userId = Secure.getString(getContentResolver(), "android_id");
    }

    public static String getClientId() {
        return userId;
    }

    public static APIsServices apIsServices() {
        if (services == null) {
            services = APIsFactory.createInstance();
        }
        return services;
    }

    public static void changeLang(Context context) {
        Locale myLocale;
        if (PrefsManager.getInstance().getLang() != null) {
            myLocale = new Locale(PrefsManager.getInstance().getLang());
        } else {
            myLocale = new Locale(Locale.getDefault().getLanguage());
        }
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        changeLang(this);
    }
}
