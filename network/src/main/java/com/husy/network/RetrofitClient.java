package com.husy.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author husy
 * @date 2019/8/28
 */
public class RetrofitClient {

    private Retrofit mRetrofit;

    private static class Holder {
        private static RetrofitClient retrofit = new RetrofitClient();
    }

    private RetrofitClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://baobab.kaiyanapp.com/api/")
                .client(getOKHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOKHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .callTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new PublicParamsInterceptor())
                .addInterceptor(new ResponseInterceptor())
                .build();
    }

    public static RetrofitClient getInstance() {
        return Holder.retrofit;
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}
