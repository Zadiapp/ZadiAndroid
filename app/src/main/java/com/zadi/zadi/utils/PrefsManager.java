package com.zadi.zadi.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class PrefsManager {
    private static final String PREFS_NAME = "training_app_prefs";
    private static PrefsManager instance;
    private SharedPreferences preferences;

    private PrefsManager(Context context) {
        this.preferences = context.getSharedPreferences(PREFS_NAME, 0);
    }

    public static void init(Context context) {
        if (instance == null) {
            synchronized (PrefsManager.class) {
                instance = new PrefsManager(context.getApplicationContext());
            }
        }
    }

    public void setIsHasLocation(boolean isHasLocation) {
        addBoolean("IS_HAS_LOCATION", isHasLocation);
    }

    public boolean isHasLocation() {
        return getBoolean("IS_HAS_LOCATION", false);
    }

    public void setIsHasLanguage(boolean isHasLocation) {
        addBoolean("IS_HAS_LANGUAGE", isHasLocation);
    }

    public boolean isHasLanguage() {
        return getBoolean("IS_HAS_LANGUAGE", false);
    }

    public static PrefsManager getInstance() {
        return instance;
    }

    public void setLang(String lang) {
        addString("APP_LANG", lang);
    }

    public String getLang() {
        return getString("APP_LANG", "en");
    }

    private void addString(String key, String value) {
        if (this.preferences != null && !TextUtils.isEmpty(key)) {
            this.preferences.edit().putString(key, value).apply();
        }
    }

    public void setLongtuide(String lng) {
        addString("LONG", lng);
    }

    public double getLongtuide() {
        return Double.parseDouble(getString("LONG","0"));
    }


    public void setLat(String lng) {
        addString("lat", lng);
    }

    public double getLat() {
        return Double.parseDouble(getString("lat","0"));
    }

    public void setToken(String token) {
        addString("Token", "Bearer" + token);
    }

    public String getToken() {
        return getString("Token", null);
    }

    private long getLong(String key) {
        return this.preferences.getLong(key, 0);
    }


    void putDouble(final String key, final double value) {
        addLong(key, Double.doubleToRawLongBits(value));
    }

    double getDouble(final String key) {
        return Double.longBitsToDouble(getLong(key));
    }

    private void addLong(String key, long val) {
        if (this.preferences != null && !TextUtils.isEmpty(key)) {
            this.preferences.edit().putFloat(key, val).apply();
        }
    }

    private String getString(String key, String defaultVal) {
        if (this.preferences == null || TextUtils.isEmpty(key)) {
            return defaultVal;
        }
        return this.preferences.getString(key, defaultVal);
    }

    private void addBoolean(String key, boolean val) {
        if (this.preferences != null && !TextUtils.isEmpty(key)) {
            this.preferences.edit().putBoolean(key, val).apply();
        }
    }

    private boolean getBoolean(String key, boolean defaultVal) {
        if (this.preferences == null || TextUtils.isEmpty(key)) {
            return defaultVal;
        }
        return this.preferences.getBoolean(key, defaultVal);
    }

    public boolean isUserSkip() {
        return getBoolean("isSkip", false);
    }

    public void setIsUserSkip(boolean b) {
        addBoolean("isSkip", b);
    }

    public String getRunningOrderId() {
        return getString("isRunning", null);
    }

    public void setIsRunningOrder(String id) {
        addString("isRunning", id);
    }
}
