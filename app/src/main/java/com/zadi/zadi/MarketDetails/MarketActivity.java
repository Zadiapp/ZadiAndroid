package com.zadi.zadi.MarketDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zadi.zadi.R;
import com.zadi.zadi.base.BaseActivity;
import com.zadi.zadi.model.Nearby.NearbyData;

import butterknife.BindView;
import butterknife.OnClick;

public class MarketActivity extends BaseActivity {

    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;
    @BindView(R.id.shadow)
    View shadow;
    @BindView(R.id.market_name)
    TextView marketName;
    @BindView(R.id.opening_hours)
    TextView openingHours;
    @BindView(R.id.accuracy_value)
    TextView accuracyValue;
    @BindView(R.id.payment_value)
    TextView paymentValue;
    @BindView(R.id.speed_value)
    TextView speedValue;
    BottomSheetBehavior sheetBehavior;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_market;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NearbyData nearbyData = getIntent().getParcelableExtra("market");
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        shadow.setAlpha(0f);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:

                        shadow.setAlpha(0.7f);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        marketName.setText(nearbyData.getName());
        openingHours.setText(nearbyData.getOpeningHour());
        accuracyValue.setText(nearbyData.getAccuracy());
        speedValue.setText(nearbyData.getDeliverySpeed());

    }

    @OnClick(R.id.info)
    void onInfoClicked() {
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public static void start(Context context, NearbyData nearbyData) {
        Intent starter = new Intent(context, MarketActivity.class);
        starter.putExtra("market", nearbyData);
        context.startActivity(starter);
    }
}
