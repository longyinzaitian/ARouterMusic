package video.cn.home.fragment.home;

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
         */
        void setData();
    }

    interface MainPresenter extends IPresenter {
        /**
         * get home info
         */
        void getHomeInfo();
    }
}
