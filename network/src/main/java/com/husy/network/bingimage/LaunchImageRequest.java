package com.husy.network.bingimage;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author husy
 * @date 2019/8/31
 */
public interface LaunchImageRequest {

    /**
     * get a image url
     * @param n num
     * @return LaunchResponse
     */
    @GET("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0")
    Observable<LaunchResponse> getImage(@Query("n") int n);
}
