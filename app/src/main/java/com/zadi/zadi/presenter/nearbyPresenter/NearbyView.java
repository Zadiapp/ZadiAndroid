package com.zadi.zadi.presenter.nearbyPresenter;

import com.zadi.zadi.model.BaseError;
import com.zadi.zadi.model.Nearby.NearbyMarketsModel;

public interface NearbyView {
    void onGetNearbySuccess(NearbyMarketsModel nearbyMarketsModel);

    void onGetNearbyFailed(BaseError baseError);
}
