package com.zadi.zadi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
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
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.providers.LocationManagerProvider;

public class MainActivity extends BaseActivity implements RegisterGuestView, OnLocationUpdatedListener {

    private RegisterGuestPresenter registerGuestPresenter;
    @BindView(R.id.allow_btn)
    CircularProgressButton allowBtn;

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
        smartLocation.location(provider).oneFix().start(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    public void onLocationUpdated(Location location) {
        Log.v("location gimme", location.getLatitude() + "  " + location.getLongitude());
        RegisterGuestBody registerGuestBody = new RegisterGuestBody();
        registerGuestBody.setDeviceId(App.getClientId());
        registerGuestBody.setLanguage(PrefsManager.getInstance().getLang());
        registerGuestBody.setToken(FirebaseInstanceId.getInstance().getToken());
        registerGuestBody.setLatitude(location.getLatitude());
        registerGuestBody.setLongitude(location.getLongitude());
        registerGuestPresenter.registerGuestUser(registerGuestBody);
    }

    @OnClick(R.id.allow_btn)
    void onAllowClicked() {
        allowBtn.startAnimation();
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        startLocation();
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
    public void onFail(BaseError baseError) {
        this.allowBtn.revertAnimation();
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(RegisterGuestResponse registerGuestResponse) {
        allowBtn.revertAnimation();
        PrefsManager.getInstance().setIsHasLocation(true);
        HomeActivity.start(this);
    }
}
