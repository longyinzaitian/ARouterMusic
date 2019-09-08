package video.cn.home.act.search;

import com.husy.network.AbstractCallListener;
import com.husy.network.home.Api;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import video.cn.base.base.BasePresenter;

/**
 * @author husy
 * @date 2019/9/8
 */
public class SearchPresenter extends BasePresenter<SearchContract.SearchView>

        implements SearchContract.SearchPresenter {

    public SearchPresenter(SearchContract.SearchView iView) {
        super(iView);
    }

    @Override
    public void getHotKeys() {
        Api.getInstance().getTrendingTag()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<List<String>>() {
                    @Override
                    public void onResponse(List<String> response) {
                        if (iView == null) {
                            return;
                        }

                        iView.setHotKeys(response);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }
}
