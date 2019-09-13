package video.cn.home.act.detail;

import com.husy.network.model.Replies;

import java.util.List;

import video.cn.base.base.IView;

/**
 * @author husy
 * @date 2019/9/10
 */
public interface VideoDetailContract {

    interface VideoDetailView extends IView {

        /**
         * set
         * @param replayData list
         */
        void setReplayData(List<Replies.ReplyListBean> replayData);

        /**
         * add
         * @param replayData list
         */
        void addReplayData(List<Replies.ReplyListBean> replayData);
    }

    interface VideoPresenter {
        /**
         * load
         * @param id int
         */
        void loadReplay(int id);

        /**
         * load more
         * @param id int
         */
        void loadMoreReplay(int id);
    }
}
