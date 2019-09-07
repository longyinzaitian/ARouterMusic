package video.cn.home.fragment.home;

import android.text.TextUtils;

import com.husy.network.AbstractCallListener;
import com.husy.network.home.Api;
import com.husy.network.model.Daily;

import java.net.URL;
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
    private String nextPageUrl = null;

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

                        iView.setData(issueLists.get(0).itemList);
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
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }
}
