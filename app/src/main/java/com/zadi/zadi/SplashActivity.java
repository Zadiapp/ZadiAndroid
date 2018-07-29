package com.zadi.zadi;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zadi.zadi.SelectLang.SelectLanguageActivity;
import com.zadi.zadi.home.HomeActivity;
import com.zadi.zadi.utils.App;
import com.zadi.zadi.utils.PrefsManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.changeLang(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PrefsManager.getInstance().isHasLocation() && PrefsManager.getInstance().isHasLanguage()) {
                    HomeActivity.start(SplashActivity.this);
                } else if (!PrefsManager.getInstance().isHasLanguage()) {
                    SelectLanguageActivity.start(SplashActivity.this);
                } else if (!PrefsManager.getInstance().isHasLocation()) {
                    MainActivity.start(SplashActivity.this);
                }
            }
        }, 2000);

    }
}
