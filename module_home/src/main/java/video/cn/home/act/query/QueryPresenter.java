package video.cn.home.act.query;

import com.husy.network.AbstractCallListener;
import com.husy.network.home.Api;
import com.husy.network.model.SearchResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import video.cn.base.base.BasePresenter;
import video.cn.base.utils.LogUtil;

/**
 * @author husy
 * @date 2019/9/9
 */
public class QueryPresenter extends BasePresenter<QueryContract.QueryView>
                        implements QueryContract.QueryPresenter {
    private String nextQueryPage;
    private int start = 0;

    public QueryPresenter(QueryContract.QueryView iView) {
        super(iView);
    }

    @Override
    public void query(String key, final boolean isMore) {
        LogUtil.Companion.i("query present.", "start:" + start + ", next querpage:" + nextQueryPage);
        if (isMore && start < 10) {
            iView.addQueryResult(null);
            return;
        }
        Api.getInstance().queryByKey(key, start)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<SearchResult>() {
                    @Override
                    public void onResponse(SearchResult response) {
                        if (iView == null) {
                            return;
                        }
                        LogUtil.Companion.i("query present.", "response:" + response);
                        if (response.getItemList() == null) {
                            if (isMore) {
                                iView.addQueryResult(null);
                            }
                            return;
                        }
                        start += response.getItemList().size();
                        if (isMore) {
                            iView.addQueryResult(response);
                        } else {
                            iView.setQueryResult(response);
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

}
