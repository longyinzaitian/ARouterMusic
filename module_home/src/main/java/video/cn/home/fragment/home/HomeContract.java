package video.cn.home.fragment.home;

import com.husy.network.model.ItemList;

import java.util.List;

import video.cn.base.base.IPresenter;
import video.cn.base.base.IView;

/**
 * @author husy
 * @date 2019/9/1
 */
public interface HomeContract {

    interface MainView extends IView {

        /**
         * set home data
         * @param itemLists list
         */
        void setData(List<ItemList> itemLists);

        /**
         * add home data
         * @param itemLists list
         */
        void addData(List<ItemList> itemLists);

        /**
         * set banners
         * @param images list
         */
        void setBanner(List<String> images);
    }

    interface MainPresenter extends IPresenter {
        /**
         * get home info
         */
        void getHomeInfo();

        /**
         * load more
         */
        void getHomeInfoMore();
    }
}
