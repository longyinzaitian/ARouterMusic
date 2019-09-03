package video.cn.app.ui.launch;

import video.cn.base.base.IPresenter;
import video.cn.base.base.IView;

/**
 * @author husy
 * @date 2019/8/31
 */
public interface LaunchContract {

    interface LaunchView extends IView {
        /**
         * 设置首页图片
         * @param url string
         */
        void showFlash(String url);
    }

    interface LaunchPresenter extends IPresenter {
        /**
         * 获取图片
         */
        void getImage();
    }

}
