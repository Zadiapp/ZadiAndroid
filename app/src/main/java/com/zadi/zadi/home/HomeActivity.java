package com.zadi.zadi.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zadi.zadi.MarketDetails.MarketActivity;
import com.zadi.zadi.R;
import com.zadi.zadi.adapter.NearbyMarketsAdapter;
import com.zadi.zadi.base.BaseActivity;
import com.zadi.zadi.model.BaseError;
import com.zadi.zadi.model.Nearby.NearbyData;
import com.zadi.zadi.model.Nearby.NearbyMarketsModel;
import com.zadi.zadi.presenter.nearbyPresenter.NearbyPresenter;
import com.zadi.zadi.presenter.nearbyPresenter.NearbyPresenterImpl;
import com.zadi.zadi.presenter.nearbyPresenter.NearbyView;
import com.zadi.zadi.utils.MyItemDecoration;
import com.zadi.zadi.utils.PrefsManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements NearbyView, NearbyMarketsAdapter.onItemClicked {

    private List<NearbyData> nearbyData = new ArrayList<>();
    private NearbyPresenter nearbyPresenter;
    private NearbyMarketsAdapter nearbyMarketsAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.item_count)
    TextView itemCount;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nearbyPresenter = new NearbyPresenterImpl(this);
        nearbyPresenter.getNearbyMarkets(PrefsManager.getInstance().getToken(),
                PrefsManager.getInstance().getLat(),
                PrefsManager.getInstance().getLongtuide());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        MyItemDecoration itemDecoration = new MyItemDecoration(this, R.dimen._4sdp);
        recyclerView.removeItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setLayoutManager(layoutManager);
        nearbyMarketsAdapter = new NearbyMarketsAdapter(nearbyData, this);
        recyclerView.setAdapter(nearbyMarketsAdapter);

    }


    public static void start(Context context) {
        Intent starter = new Intent(context, HomeActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onGetNearbySuccess(NearbyMarketsModel nearbyMarketsModel) {
        nearbyData.addAll(nearbyMarketsModel.getData());
        nearbyMarketsAdapter.notifyDataSetChanged();
        itemCount.setText(String.format("%d", nearbyMarketsModel.getItemsCount()));
    }

    @Override
    public void onGetNearbyFailed(BaseError baseError) {

    }

    @Override
    public void onClicked(NearbyData nearbyData) {
        MarketActivity.start(this, nearbyData);
    }
}
