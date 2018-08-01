package com.zadi.zadi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.zadi.zadi.GPS.GPSTracker;
import com.zadi.zadi.GPS.GPSTrakerListner;
import com.zadi.zadi.base.BaseActivity;
import com.zadi.zadi.home.HomeActivity;
import com.zadi.zadi.mapViews.MapsActivity;
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

public class MainActivity extends BaseActivity implements RegisterGuestView, GPSTrakerListner {

    private RegisterGuestPresenter registerGuestPresenter;
    @BindView(R.id.allow_btn)
    CircularProgressButton allowBtn;
    private GPSTracker gps;
    private String mLat;
    private String mLang;
    private LocationManager manager;
    private boolean isHasLocation;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.registerGuestPresenter = new RegisterPresenterImpl(this);

    }

    private void startLocation() {
        LocationManagerProvider provider = new LocationManagerProvider();
        SmartLocation smartLocation = new SmartLocation.Builder(this).logging(true).build();
//        smartLocation.location(provider).oneFix().start(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

//    @Override
//    public void onLocationUpdated(Location location) {
//        Log.v("location gimme", location.getLatitude() + "  " + location.getLongitude());
//        RegisterGuestBody registerGuestBody = new RegisterGuestBody();
//        registerGuestBody.setDeviceId(App.getClientId());
//        registerGuestBody.setLanguage(PrefsManager.getInstance().getLang());
//        registerGuestBody.setToken(FirebaseInstanceId.getInstance().getToken());
//        registerGuestBody.setLatitude(location.getLatitude());
//        registerGuestBody.setLongitude(location.getLongitude());
//        PrefsManager.getInstance().setLongtuide(String.valueOf(location.getLongitude()));
//        PrefsManager.getInstance().setLat(String.valueOf(location.getLatitude()));
//        registerGuestPresenter.registerGuestUser(registerGuestBody);
//    }

    @OnClick(R.id.allow_btn)
    void onAllowClicked() {
        allowBtn.startAnimation();
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        gps = new GPSTracker(MainActivity.this, MainActivity.this);
                        getCurrentLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        allowBtn.revertAnimation();
                        MapsActivity.start(MainActivity.this);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 300: {
                if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    gps = new GPSTracker(MainActivity.this, MainActivity.this);
                    getCurrentLocation();
                }
            }
        }
    }

    void getCurrentLocation() {
        gps.getLocation();
        if (!gps.canGetLocation()) {
            manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 300);
        } else {
            if (gps.getLatitude() != 0.0 && gps.getLongitude() != 0.0) {
                mLat = String.valueOf(gps.getLatitude());
                mLang = String.valueOf(gps.getLongitude());
            }
        }
    }

    @Override
    public void onFail(BaseError baseError) {
        this.allowBtn.revertAnimation();
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(RegisterGuestResponse registerGuestResponse) {
        allowBtn.revertAnimation();
        PrefsManager.getInstance().setToken(registerGuestResponse.getData());
        PrefsManager.getInstance().setIsHasLocation(true);

        HomeActivity.start(this);
    }

    @Override
    public void onTrackerSuccess(Double lat, Double log) {
        if (!isHasLocation) {
            isHasLocation = true;
            RegisterGuestBody registerGuestBody = new RegisterGuestBody();
            registerGuestBody.setDeviceId(App.getClientId());
            registerGuestBody.setLanguage(PrefsManager.getInstance().getLang());
            registerGuestBody.setToken(FirebaseInstanceId.getInstance().getToken());
            registerGuestBody.setLatitude(lat);
            registerGuestBody.setLongitude(log);
            PrefsManager.getInstance().setLongtuide(String.valueOf(log));
            PrefsManager.getInstance().setLat(String.valueOf(lat));
            registerGuestPresenter.registerGuestUser(registerGuestBody);
        }
    }

    @Override
    public void onStartTracker() {

    }
}
