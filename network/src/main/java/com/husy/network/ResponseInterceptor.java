package com.husy.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author husy
 * @date 2019/8/29
 */
public class ResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
