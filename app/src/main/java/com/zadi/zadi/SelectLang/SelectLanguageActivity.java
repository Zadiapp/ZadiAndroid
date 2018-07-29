package com.zadi.zadi.SelectLang;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.providers.LocationManagerProvider;

import com.google.firebase.iid.FirebaseInstanceId;
import com.zadi.zadi.R;
import com.zadi.zadi.MainActivity;
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

public class SelectLanguageActivity extends BaseActivity implements RegisterGuestView, OnLocationUpdatedListener {

    private RegisterGuestPresenter registerGuestPresenter;
    private Location location;
    @BindView(R.id.english_btn)
    CircularProgressButton englishBtn;
    @BindView(R.id.arabic_btn)
    CircularProgressButton arabicBtn;

    public int getActivityLayout() {
        return R.layout.activity_select_language;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            registerGuestPresenter = new RegisterPresenterImpl(this);
            startLocation();
        }
    }

    private void startLocation() {
        LocationManagerProvider provider = new LocationManagerProvider();
        SmartLocation smartLocation = new SmartLocation.Builder(this).logging(true).build();
        smartLocation.location(provider).start(this);
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

        if (location != null) {
            arabicBtn.startAnimation();
            RegisterGuestBody registerGuestBody = new RegisterGuestBody();
            registerGuestBody.setDeviceId(App.getClientId());
            registerGuestBody.setLanguage(PrefsManager.getInstance().getLang());
            registerGuestBody.setToken(FirebaseInstanceId.getInstance().getToken());
            registerGuestBody.setLatitude(location.getLatitude());
            registerGuestBody.setLongitude(location.getLongitude());
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

        if (location != null) {
            englishBtn.startAnimation();
            RegisterGuestBody registerGuestBody = new RegisterGuestBody();
            registerGuestBody.setDeviceId(App.getClientId());
            registerGuestBody.setLanguage(PrefsManager.getInstance().getLang());
            registerGuestBody.setToken(FirebaseInstanceId.getInstance().getToken());
            registerGuestBody.setLatitude(location.getLatitude());
            registerGuestBody.setLongitude(location.getLongitude());
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
        PrefsManager.getInstance().setIsHasLocation(true);
        HomeActivity.start(this);
    }

    @Override
    public void onLocationUpdated(Location location) {
        this.location = location;
    }
}
