package com.zadi.zadi.SelectLang;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.zadi.zadi.GPS.GPSTracker;
import com.zadi.zadi.GPS.GPSTrakerListner;
import com.zadi.zadi.MainActivity;
import com.zadi.zadi.R;
import com.zadi.zadi.base.BaseActivity;
import com.zadi.zadi.home.HomeActivity;
import com.zadi.zadi.model.BaseError;
import com.zadi.zadi.model.registerGuest.RegisterGuestBody;
import com.zadi.zadi.model.registerGuest.RegisterGuestResponse;
import com.zadi.zadi.presenter.registerGuestPresenter.RegisterGuestPresenter;
import com.zadi.zadi.presenter.registerGuestPresenter.RegisterGuestView;
import com.zadi.zadi.presenter.registerGuestPresenter.RegisterPresenterImpl;
import com.zadi.zadi.utils.App;
import com.zadi.zadi.utils.PrefsManager;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.providers.LocationManagerProvider;

public class SelectLanguageActivity extends BaseActivity implements RegisterGuestView, GPSTrakerListner {

    private RegisterGuestPresenter registerGuestPresenter;
    private Location location;
    @BindView(R.id.english_btn)
    CircularProgressButton englishBtn;
    @BindView(R.id.arabic_btn)
    CircularProgressButton arabicBtn;
    private GPSTracker gps;

    private String mLat;
    private String mLang;


    public int getActivityLayout() {
        return R.layout.activity_select_language;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            registerGuestPresenter = new RegisterPresenterImpl(this);
//            startLocation();
            gps = new GPSTracker(this, this);
            getCurrentLocation();
        }
    }

    private void startLocation() {
        LocationManagerProvider provider = new LocationManagerProvider();
        SmartLocation smartLocation = new SmartLocation.Builder(this).logging(true).build();
//        smartLocation.location(provider).start(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SelectLanguageActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @OnClick(R.id.arabic_btn)
    void onArabicClicked() {
        PrefsManager.getInstance().setLang("ar");
        PrefsManager.getInstance().setIsHasLanguage(true);

        if (!TextUtils.isEmpty(mLang)) {
            arabicBtn.startAnimation();
            RegisterGuestBody registerGuestBody = new RegisterGuestBody();
            registerGuestBody.setDeviceId(App.getClientId());
            registerGuestBody.setLanguage(PrefsManager.getInstance().getLang());
            registerGuestBody.setToken(FirebaseInstanceId.getInstance().getToken());
            registerGuestBody.setLatitude(Double.parseDouble(mLat));
            registerGuestBody.setLongitude(Double.parseDouble(mLang));
            PrefsManager.getInstance().setLongtuide(mLang);
            PrefsManager.getInstance().setLat(mLat);
            registerGuestPresenter.registerGuestUser(registerGuestBody);
        } else {
            MainActivity.start(this);
        }
        App.changeLang(this);
    }

    @OnClick(R.id.english_btn)
    void onEnglishClicked() {
        PrefsManager.getInstance().setLang("en");
        PrefsManager.getInstance().setIsHasLanguage(true);

        if (!TextUtils.isEmpty(mLang)) {
            englishBtn.startAnimation();
            RegisterGuestBody registerGuestBody = new RegisterGuestBody();
            registerGuestBody.setDeviceId(App.getClientId());
            registerGuestBody.setLanguage(PrefsManager.getInstance().getLang());
            registerGuestBody.setToken(FirebaseInstanceId.getInstance().getToken());
            registerGuestBody.setLatitude(Double.parseDouble(mLat));
            registerGuestBody.setLongitude(Double.parseDouble(mLang));

            PrefsManager.getInstance().setLongtuide(mLang);
            PrefsManager.getInstance().setLat(mLat);
            registerGuestPresenter.registerGuestUser(registerGuestBody);
        } else {
            MainActivity.start(this);
        }
        App.changeLang(this);
    }

    @Override
    public void onFail(BaseError baseError) {
        arabicBtn.revertAnimation();
        englishBtn.revertAnimation();
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(RegisterGuestResponse registerGuestResponse) {
        arabicBtn.revertAnimation();
        englishBtn.revertAnimation();
        PrefsManager.getInstance().setToken(registerGuestResponse.getData());
        PrefsManager.getInstance().setIsHasLocation(true);
        HomeActivity.start(this);
    }

    void getCurrentLocation() {
        gps.getLocation();
        if (!gps.canGetLocation()) {
            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 300);
        } else {
            if (gps.getLatitude() != 0.0 && gps.getLongitude() != 0.0) {
                mLat = String.valueOf(gps.getLatitude());
                mLang = String.valueOf(gps.getLongitude());
            }
        }
    }

//    @Override
//    public void onLocationUpdated(Location location) {
//        this.location = location;
//    }

    @Override
    public void onTrackerSuccess(Double lat, Double log) {

    }

    @Override
    public void onStartTracker() {

    }
}
