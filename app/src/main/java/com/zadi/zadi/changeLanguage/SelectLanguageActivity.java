package com.zadi.zadi.changeLanguage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zadi.zadi.R;
import com.zadi.zadi.base.BaseActivity;
import com.zadi.zadi.home.HomeActivity;
import com.zadi.zadi.utils.PrefsManager;

import butterknife.OnClick;

public class SelectLanguageActivity extends BaseActivity {

    @Override
    public int getActivityLayout() {
        return R.layout.activity_select_language;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public static void start(Context context) {
        Intent starter = new Intent(context, SelectLanguageActivity.class);
        context.startActivity(starter);
    }

    @OnClick(R.id.arabic_btn)
    void onArabicClicked() {
        PrefsManager.getInstance().setLang("ar");
        PrefsManager.getInstance().setIsHasLanguage(true);
        HomeActivity.start(this);
        finish();
    }

    @OnClick(R.id.english_btn)
    void onEnglishClicked() {
        PrefsManager.getInstance().setLang("en");
        PrefsManager.getInstance().setIsHasLanguage(true);
        HomeActivity.start(this);
        finish();
    }
}
