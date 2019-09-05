package com.husy.network.home;

import com.husy.network.model.AuthorDetailData;
import com.husy.network.model.Daily;
import com.husy.network.model.Discover;
import com.husy.network.model.Interesting;
import com.husy.network.model.Replies;
import com.husy.network.model.SearchResult;
import com.husy.network.model.SpecialData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author husy
 * @date 2019/9/1
 */
public interface HomeRequest {

    //获取日常视频
    @GET("v2/feed?num=2")
    Observable<Daily> getDaily(@Query("date") long date);

    //获取今天日常视频
    @GET("v2/feed?num=2")
    Observable<Daily> getDaily();

    //获取视频底下回复
    @GET("v1/replies/video")
    Observable<Replies> getReplies(@Query("id") int id);

    //获取更多视频底下回复
    @GET("v1/replies/video?num=10")
    Observable<Replies> getReplies(@Query("id")int id, @Query("lastId") int lastId);

    //获取发现界面的数据
    @GET("v3/discovery")
    Observable<Discover> getDiscover();

    //获取发现专题下的各个小专题详情
    @GET("v3/categories/detail")
    Observable<SpecialData> getSpecialData(@Query("id") int id);

    //获取最近更新或分享最多的数据
    @GET("v3/videos?num=10")
    Observable<Interesting> getInteresting(
            @Query("start") int start, @Query("categoryId") int categoryId,
            @Query("strategy") String strategy);

    //获取热门搜索词
    @GET("v3/queries/hot")
    Observable<List<String>> getTrendingTag();

    @GET("v1/search?num=10")
    Observable<SearchResult> queryByKey(@Query("query") String key, @Query("start") int start);

    //http://baobab.kaiyanapp.com/api/v3/pgc/videos?pgcId=156&strategy=date （作者列表）
    //获取作者的视频列表
    @GET("v3/pgc/videos")
    Observable<AuthorDetailData> getAuthorDetailData(@Query("start") int start, @Query("pgcId") int pgcId, @Query("strategy") String strategy);
}
