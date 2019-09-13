package video.cn.recommend.act;

import com.husy.network.AbstractCallListener;
import com.husy.network.home.Api;
import com.husy.network.model.ItemList;
import com.husy.network.model.SectionList;
import com.husy.network.model.SpecialData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import video.cn.base.base.BasePresenter;

/**
 * @author husy
 * @date 2019/9/13
 */
public class RecommendPresenter extends BasePresenter<RecommendContract.RecommendView>
        implements RecommendContract.RecommendPresenter {

    public RecommendPresenter(RecommendContract.RecommendView iView) {
        super(iView);
    }

    @Override
    public void loadData(int id, boolean isMore) {
        Api.getInstance().getSpecialData(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<SpecialData>() {
                    @Override
                    public void onResponse(SpecialData response) {
                        if (iView == null) {
                            return;
                        }
                        resultData(response);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

    private void resultData(SpecialData response) {
        if (response == null) {
            return;
        }

        List<SectionList> sectionLists = response.sectionList;
        if (sectionLists == null || sectionLists.isEmpty()) {
            return;
        }

        List<ItemList> result = new ArrayList<>();

        for (SectionList sectionList : sectionLists) {
            List<ItemList> itemLists = sectionList.itemList;
            if (itemLists == null || itemLists.isEmpty()) {
                continue;
            }

            for (ItemList itemList : itemLists) {
                if (itemList.type.equals("video")) {
                    result.add(itemList);
                    continue;
                }

                List<ItemList> innerList = itemList.data.itemList;
                if (innerList == null || innerList.isEmpty()) {
                    continue;
                }

                for (ItemList item : itemLists) {
                    if (item.type.equals("video")) {
                        result.add(item);
                    }
                }
            }
        }

        iView.setData(result);
    }
}
