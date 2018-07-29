package com.zadi.zadi.presenter.registerGuestPresenter;

import com.zadi.zadi.model.BaseError;
import com.zadi.zadi.model.registerGuest.RegisterGuestBody;
import com.zadi.zadi.model.registerGuest.RegisterGuestResponse;
import com.zadi.zadi.utils.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenterImpl implements RegisterGuestPresenter {
    private RegisterGuestView registerGuestView;

    class C07041 implements Callback<RegisterGuestResponse> {
        C07041() {
        }

        public void onResponse(Call<RegisterGuestResponse> call, Response<RegisterGuestResponse> response) {
            if (response.body() != null && ((RegisterGuestResponse) response.body()).isStatus()) {
                RegisterPresenterImpl.this.registerGuestView.onSuccess((RegisterGuestResponse) response.body());
            } else if (response.body() != null && ((RegisterGuestResponse) response.body()).getValidation() != null) {
                BaseError baseError = new BaseError();
                if (((RegisterGuestResponse) response.body()).getValidation().getDeviceId() != null) {
                    baseError.setError((String) ((RegisterGuestResponse) response.body()).getValidation().getDeviceId().get(0));
                    RegisterPresenterImpl.this.registerGuestView.onFail(baseError);
                } else if (((RegisterGuestResponse) response.body()).getValidation().getLanguage() != null) {
                    baseError.setError((String) ((RegisterGuestResponse) response.body()).getValidation().getLanguage().get(0));
                    RegisterPresenterImpl.this.registerGuestView.onFail(baseError);
                } else if (((RegisterGuestResponse) response.body()).getValidation().getToken() != null) {
                    baseError.setError((String) ((RegisterGuestResponse) response.body()).getValidation().getToken().get(0));
                    RegisterPresenterImpl.this.registerGuestView.onFail(baseError);
                } else if (((RegisterGuestResponse) response.body()).getValidation().getLatitude() != null) {
                    baseError.setError((String) ((RegisterGuestResponse) response.body()).getValidation().getLatitude().get(0));
                    RegisterPresenterImpl.this.registerGuestView.onFail(baseError);
                } else if (((RegisterGuestResponse) response.body()).getValidation().getLongitude() != null) {
                    baseError.setError((String) ((RegisterGuestResponse) response.body()).getValidation().getLatitude().get(0));
                    RegisterPresenterImpl.this.registerGuestView.onFail(baseError);
                }
            }
        }

        public void onFailure(Call<RegisterGuestResponse> call, Throwable t) {
            BaseError baseError = new BaseError();
            baseError.setError("Network error");
            RegisterPresenterImpl.this.registerGuestView.onFail(baseError);
        }
    }

    public RegisterPresenterImpl(RegisterGuestView registerGuestView) {
        this.registerGuestView = registerGuestView;
    }

    public void registerGuestUser(RegisterGuestBody registerGuestBody) {
        App.apIsServices().registerGuest(registerGuestBody).enqueue(new C07041());
    }
}
