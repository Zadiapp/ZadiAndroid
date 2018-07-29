package com.zadi.zadi.presenter;

import com.zadi.zadi.model.BaseError;
import com.zadi.zadi.model.BaseModel;

public interface BasePresenter {
    void onFail(BaseError baseError);

    void onSuccess(BaseModel baseModel);
}
