package video.cn.recommend.act;

import com.husy.network.model.ItemList;

import java.util.List;

import video.cn.base.base.IView;

/**
 * @author husy
 * @date 2019/9/13
 */
public interface RecommendContract {
    interface RecommendView extends IView {
        /**
         * set data
         * @param itemLists list
         */
        void setData(List<ItemList> itemLists);
    }

    interface RecommendPresenter {
        /**
         * load data
         * @param id int
         * @param isMore bool
         */
        void loadData(int id, boolean isMore);
    }
}
