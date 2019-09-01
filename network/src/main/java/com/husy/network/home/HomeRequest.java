package com.husy.network.home;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author husy
 * @date 2019/9/1
 */
public interface HomeRequest {

    /**
     * 获取首页信息
     * @return json root bean
     */
    @GET("http://baobab.kaiyanapp.com/api/v5/index/tab/discovery")
    Observable<JsonRootBean> getHomeInfo();
}
