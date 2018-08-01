package com.zadi.zadi.presenter.nearbyPresenter;

import com.zadi.zadi.model.BaseError;
import com.zadi.zadi.model.Nearby.NearbyMarketsModel;
import com.zadi.zadi.utils.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyPresenterImpl implements NearbyPresenter {

    private NearbyView nearbyPresenter;

    public NearbyPresenterImpl(NearbyView nearbyPresenter) {
        this.nearbyPresenter = nearbyPresenter;
    }

    @Override
    public void getNearbyMarkets(String auth, double lat, double lng) {
        App.apIsServices().getNearbyMarkets(auth, lat, lng).enqueue(new Callback<NearbyMarketsModel>() {
            @Override
            public void onResponse(Call<NearbyMarketsModel> call, Response<NearbyMarketsModel> response) {
                if (response.body() != null && response.body().isStatus()) {
                    nearbyPresenter.onGetNearbySuccess(response.body());
                } else {
                    BaseError baseError = new BaseError();
                    baseError.setError(response.body().getValidation());
                    nearbyPresenter.onGetNearbyFailed(baseError);
                }
            }

            @Override
            public void onFailure(Call<NearbyMarketsModel> call, Throwable t) {
                BaseError baseError = new BaseError();
                baseError.setError("Network error");
                nearbyPresenter.onGetNearbyFailed(baseError);
            }
        });
    }
}
