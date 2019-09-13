package video.cn.recommend.fragment;

import com.husy.network.bingimage.LaunchResponse;
import com.husy.network.model.ItemList;

import java.util.List;

import video.cn.base.base.IView;

/**
 * @author husy
 * @date 2019/9/13
 */
public interface MainContract {

    interface MainView extends IView {
        /**
         * set data
         * @param itemLists list
         */
        void setData(List<ItemList> itemLists);

        /**
         * set banner
         * @param images list
         * @param images launch image
         */
        void setBanner(List<String> images, List<LaunchResponse.LaunchImage> launchImages);
    }

    interface MainPresenter {
        /**
         * load data
         */
        void loadData();
    }
}
