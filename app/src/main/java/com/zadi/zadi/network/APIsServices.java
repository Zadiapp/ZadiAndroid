package com.zadi.zadi.network;

import com.zadi.zadi.model.registerGuest.RegisterGuestBody;
import com.zadi.zadi.model.registerGuest.RegisterGuestResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIsServices {
    @POST("register-guest")
    Call<RegisterGuestResponse> registerGuest(@Body RegisterGuestBody registerGuestBody);
}
