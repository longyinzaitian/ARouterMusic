package com.husy.network.home;

import com.husy.network.RetrofitClient;
import com.husy.network.model.AuthorDetailData;
import com.husy.network.model.Daily;
import com.husy.network.model.Discover;
import com.husy.network.model.Interesting;
import com.husy.network.model.Replies;
import com.husy.network.model.SearchResult;
import com.husy.network.model.SpecialData;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author husy
 * @date 2019/9/5
 */
public class Api {
    public static Api instance;
    private HomeRequest mRequest;

    private Api(){
        mRequest = RetrofitClient.getInstance().create(HomeRequest.class);
    }

    public static Api getInstance(){
        if (instance == null){
            instance = new Api();
        }
        return instance;
    }

    /**
     * 获取日常视频
     * @param date
     * @return
     */
    public Observable<Daily> getDaily(long date){
        return mRequest.getDaily(date);
    }

    /**
     * 获取当天的视频
     * @return
     */
    public Observable<Daily> getDaily(){
        return mRequest.getDaily();
    }

    /**
     * 获取视频回复
     * @param id
     * @return
     */
    public Observable<Replies> getReplies(int id){
        return mRequest.getReplies(id);
    }

    /**
     * 获取视频更多回复
     * @param id
     * @return
     */
    public Observable<Replies> getReplies(int id, int lastId){
        return mRequest.getReplies(id, lastId);
    }

    /**
     * 获取发现界面的数据
     * @return
     */
    public Observable<Discover> getDiscover(){
        return mRequest.getDiscover();
    }

    /**
     * 获取发现专题下的各个小专题
     * @param id
     * @return
     */
    public Observable<SpecialData> getSpecialData(int id){
        return mRequest.getSpecialData(id);
    }

    /**
     * 获取每个专题的内容
     * @param start
     * @param category
     * @param strategy
     * @return
     */
    public Observable<Interesting> getInteresting(int start, int category, String strategy){
        return mRequest.getInteresting(start, category, strategy);
    }

    /**
     * 获取热门搜索词
     * @return
     */
    public Observable<List<String>> getTrendingTag(){
        return mRequest.getTrendingTag();
    }

    /**
     * 根据关键字查找
     * @param key
     * @param start
     * @return
     */
    public Observable<SearchResult> queryByKey(String key, int start){
        return mRequest.queryByKey(key, start);
    }

    /**
     * 获取作者详情页的数据
     * @param authorId
     * @param strategy
     * @return
     */
    public Observable<AuthorDetailData> getAuthorDetailData(int start, int authorId, String strategy){
        return mRequest.getAuthorDetailData(start, authorId, strategy);
    }
}
