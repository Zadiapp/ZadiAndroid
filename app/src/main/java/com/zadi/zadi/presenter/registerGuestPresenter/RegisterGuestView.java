package com.zadi.zadi.presenter.registerGuestPresenter;

import com.zadi.zadi.model.BaseError;
import com.zadi.zadi.model.registerGuest.RegisterGuestResponse;

public interface RegisterGuestView {
    void onFail(BaseError baseError);

    void onSuccess(RegisterGuestResponse registerGuestResponse);
}
