package com.zadi.zadi.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zadi.zadi.BuildConfig;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIsFactory {
    private static final String baseUrl = "http://35.199.159.218/api/";

    private static final class HeadersInterceptor implements Interceptor {
        HeadersInterceptor() {
        }

        public Response intercept(Chain chain) throws IOException {
            Builder builder = chain.request().newBuilder();
            builder.addHeader("Accept", "application/json");
            return chain.proceed(builder.build());
        }
    }

    public static APIsServices createInstance() {
        return (APIsServices) new Retrofit.Builder().baseUrl(baseUrl).client(newClient()).addConverterFactory(GsonConverterFactory.create(newGson())).build().create(APIsServices.class);
    }

    private static Gson newGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    private static OkHttpClient newClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).addNetworkInterceptor(new HeadersInterceptor());
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY));
        }
        return builder.build();
    }
}
