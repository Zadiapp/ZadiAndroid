package com.zadi.zadi.mapViews;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.iid.FirebaseInstanceId;
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

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, RegisterGuestView {

    private LocationRequest mLocationRequest;
    private GoogleMap mMap;
    private RegisterGuestPresenter registerGuestPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.confirmBtn)
    CircularProgressButton confirmBtn;

    public int getActivityLayout() {
        return R.layout.activity_maps;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        this.registerGuestPresenter = new RegisterPresenterImpl(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        this.mLocationRequest = new LocationRequest();
        this.mLocationRequest.setInterval(120000);
        this.mLocationRequest.setFastestInterval(120000);
        this.mLocationRequest.setPriority(102);
        LatLng egypt;
        if (VERSION.SDK_INT < 23) {
            egypt = new LatLng(26.8206d, 30.8025d);

            this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(egypt, 6.0f));
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
//            this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.myLooper());
//            this.mMap.setMyLocationEnabled(true);
        } else {
            egypt = new LatLng(26.8206d, 30.8025d);
            this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(egypt, 6.0f));
        }
    }

    public static void startWithClearStack(Context context) {
        Intent starter = new Intent(context, MapsActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MapsActivity.class);
        context.startActivity(starter);
    }

    @OnClick(R.id.confirmBtn)
    void onConfirmBtnclicked() {
        confirmBtn.startAnimation();
        RegisterGuestBody registerGuestBody = new RegisterGuestBody();
        registerGuestBody.setDeviceId(App.getClientId());
        registerGuestBody.setLanguage(PrefsManager.getInstance().getLang());
        registerGuestBody.setToken(FirebaseInstanceId.getInstance().getToken());
        registerGuestBody.setLatitude(mMap.getCameraPosition().target.latitude);
        registerGuestBody.setLongitude(mMap.getCameraPosition().target.longitude);
        registerGuestPresenter.registerGuestUser(registerGuestBody);

    }

    @Override
    public void onFail(BaseError baseError) {
        this.confirmBtn.revertAnimation();
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(RegisterGuestResponse registerGuestResponse) {
        confirmBtn.revertAnimation();
        PrefsManager.getInstance().setIsHasLocation(true);
        HomeActivity.start(this);
    }
}
