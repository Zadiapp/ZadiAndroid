package com.zadi.zadi.network;

import com.zadi.zadi.model.MarketAnnouncement;
import com.zadi.zadi.model.Nearby.NearbyMarketsModel;
import com.zadi.zadi.model.registerGuest.RegisterGuestBody;
import com.zadi.zadi.model.registerGuest.RegisterGuestResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIsServices {
    @POST("register-guest")
    Call<RegisterGuestResponse> registerGuest(@Body RegisterGuestBody registerGuestBody);

    @GET("markets/nearby")
    Call<NearbyMarketsModel> getNearbyMarkets(@Header("Authorization") String token,
                                              @Query("latitude") double latitude,
                                              @Query("longitude") double longitude);

    @GET("markets/announcement/1")
    Call<MarketAnnouncement> getMarketAnnouncement(@Header("Authorization") String toke);
}
