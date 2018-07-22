package com.zadi.zadi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zadi.zadi.changeLanguage.SelectLanguageActivity;
import com.zadi.zadi.home.HomeActivity;
import com.zadi.zadi.utils.App;
import com.zadi.zadi.utils.PrefsManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.changeLang(this);
        if (PrefsManager.getInstance().isHasLocation() && PrefsManager.getInstance().isHasLanguage())
            HomeActivity.start(this);
        else if (!PrefsManager.getInstance().isHasLocation()) {
            MainActivity.start(this);
        } else if (!PrefsManager.getInstance().isHasLanguage()) {
            SelectLanguageActivity.start(this);
        }
        finish();
    }
}
