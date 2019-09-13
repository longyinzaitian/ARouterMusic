package video.cn.home.act.search;

import java.util.List;

import video.cn.base.base.IView;

/**
 * @author husy
 * @date 2019/9/8
 */
public interface SearchContract {

    interface SearchView extends IView {
        /**
         * set hot keys
         * @param response list
         */
        void setHotKeys(List<String> response);
    }

    interface SearchPresenter {
        /**
         * get hot keys
         */
        void getHotKeys();

        /**
         * get hot key
         * @param pos int
         * @return string
         */
        String getHotKey(int pos);
    }
}
