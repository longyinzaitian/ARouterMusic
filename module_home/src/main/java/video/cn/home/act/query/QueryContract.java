package video.cn.home.act.query;

import com.husy.network.model.SearchResult;

import video.cn.base.base.IView;

/**
 * @author husy
 * @date 2019/9/9
 */
public interface QueryContract {

    interface QueryView extends IView {
        /**
         * sou suo jie guo
         * @param response res
         */
        void setQueryResult(SearchResult response);

        /**
         * add sou suo jie guo
         * @param response res
         */
        void addQueryResult(SearchResult response);
    }

    interface QueryPresenter {
        /**
         * query key
         * @param key string
         * @param isMore bool
         */
        void query(String key, boolean isMore);
    }
}
