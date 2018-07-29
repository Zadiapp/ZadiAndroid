package com.zadi.zadi.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zadi.zadi.R;
import com.zadi.zadi.base.BaseActivity;
import com.zadi.zadi.utils.PrefsManager;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.lang)
    TextView lang;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lang.setText("App lang is :" + PrefsManager.getInstance().getLang());
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, HomeActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }
}
