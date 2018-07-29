package com.zadi.zadi.network;

import com.zadi.zadi.model.BaseError;
import com.zadi.zadi.model.BaseModel;
import com.zadi.zadi.model.registerGuest.RegisterGuestResponse;
import com.zadi.zadi.presenter.BasePresenter;

public class ValidateNetworkResponse {
    public void validateNetworkResponse(BaseModel baseModel, BasePresenter basePresenter) {
        if (baseModel.isStatus()) {
            basePresenter.onSuccess(baseModel);
            return;
        }
        BaseError baseError = new BaseError();
        if (!(baseModel instanceof RegisterGuestResponse)) {
            baseError.setError("Unknown error");
            basePresenter.onFail(baseError);
        } else if (((RegisterGuestResponse) baseModel).getValidation().getDeviceId() != null) {
            baseError.setError((String) ((RegisterGuestResponse) baseModel).getValidation().getDeviceId().get(0));
            basePresenter.onFail(baseError);
        } else if (((RegisterGuestResponse) baseModel).getValidation().getLanguage() != null) {
            baseError.setError((String) ((RegisterGuestResponse) baseModel).getValidation().getLanguage().get(0));
            basePresenter.onFail(baseError);
        } else if (((RegisterGuestResponse) baseModel).getValidation().getToken() != null) {
            baseError.setError((String) ((RegisterGuestResponse) baseModel).getValidation().getToken().get(0));
            basePresenter.onFail(baseError);
        } else if (((RegisterGuestResponse) baseModel).getValidation().getLatitude() != null) {
            baseError.setError((String) ((RegisterGuestResponse) baseModel).getValidation().getLatitude().get(0));
            basePresenter.onFail(baseError);
        } else if (((RegisterGuestResponse) baseModel).getValidation().getLongitude() != null) {
            baseError.setError((String) ((RegisterGuestResponse) baseModel).getValidation().getLatitude().get(0));
            basePresenter.onFail(baseError);
        }
    }
}
