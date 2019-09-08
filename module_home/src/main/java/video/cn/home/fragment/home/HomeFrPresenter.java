package video.cn.home.fragment.home;

import android.text.TextUtils;

import com.husy.network.AbstractCallListener;
import com.husy.network.RetrofitClient;
import com.husy.network.bingimage.LaunchImageRequest;
import com.husy.network.bingimage.LaunchResponse;
import com.husy.network.home.Api;
import com.husy.network.model.Daily;
import com.husy.network.model.ItemList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import video.cn.base.base.BasePresenter;
import video.cn.base.utils.LogUtil;

/**
 * @author husy
 * @date 2019/9/2
 */
public class HomeFrPresenter extends BasePresenter<HomeContract.MainView>
        implements HomeContract.MainPresenter {
    private static final String TAG = "HomeFrPresenter";
    private static final int NUM = 5;
    private String nextPageUrl = null;
    private int moreToggle = 0;
    private String lastQuery = "";

    public HomeFrPresenter(HomeContract.MainView iView) {
        super(iView);
    }

    @Override
    public void getHomeInfo() {
        Api.getInstance().getDaily()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<Daily>() {
                    @Override
                    public void onResponse(Daily response) {
                        if (iView == null || response == null) {
                            return;
                        }

                        List<Daily.IssueList> issueLists = response.issueList;
                        nextPageUrl = response.nextPageUrl;
                        if (issueLists == null || issueLists.isEmpty()) {
                            return;
                        }

                        getBannerImages(issueLists.get(0).itemList);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });

    }

    private void getBannerImages(final List<ItemList> itemLists) {
        RetrofitClient.getInstance().create(LaunchImageRequest.class)
                .getImage(NUM)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<LaunchResponse>() {
                    @Override
                    public void onResponse(LaunchResponse launchResponse) {
                        if (iView == null || launchResponse.getImages() == null
                                || launchResponse.getImages().isEmpty()) {
                            return;
                        }

                        List<LaunchResponse.LaunchImage> images = launchResponse.getImages();
                        List<String> imageList = new ArrayList<>();
                        if (images != null) {
                            for (LaunchResponse.LaunchImage image : images) {
                                imageList.add("https://cn.bing.com" + image.getUrl());
                            }
                        }
                        if (imageList.isEmpty()) {
                            imageList.add("http://img.kaiyanapp.com/eef24aa10ab6cf17b5a512943ec22053.jpeg?imageMogr2/quality/60/format/jpg");
                        }
                        iView.setBanner(imageList);
                        iView.setData(itemLists);
                        getHomeInfoMore();
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

    @Override
    public void getHomeInfoMore() {
        if (TextUtils.isEmpty(nextPageUrl)) {
            return;
        }

        long date = 0;
        try {
            URL url = new URL(nextPageUrl);
            String query = url.getQuery();

            if (lastQuery.equals(query)) {
                return;
            }
            lastQuery = query;
            LogUtil.Companion.i(TAG, "query:" + query);

            String[] params = query.split("&");
            if (params.length > 0) {
                date = Long.valueOf(params[0].split("=")[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Api.getInstance().getDaily(date)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<Daily>() {
                    @Override
                    public void onResponse(Daily response) {
                        if (iView == null || response == null) {
                            return;
                        }

                        List<Daily.IssueList> issueLists = response.issueList;
                        nextPageUrl = response.nextPageUrl;
                        if (issueLists == null || issueLists.isEmpty()) {
                            return;
                        }

                        iView.addData(issueLists.get(0).itemList);
                        if (moreToggle <= 1) {
                            moreToggle++;
                            getHomeInfoMore();
                        } else {
                            moreToggle = 0;
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }
}
