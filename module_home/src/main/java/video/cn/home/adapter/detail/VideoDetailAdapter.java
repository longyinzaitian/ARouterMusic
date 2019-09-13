package video.cn.home.adapter.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.husy.network.model.Replies;

import video.cn.base.base.BaseAdapter;
import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/10
 */
public class VideoDetailAdapter extends BaseAdapter<Replies.ReplyListBean> {

    private static final int NORMAL = 1;

    public VideoDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getItemCount()-1) {
            return NORMAL;
        }
        return super.getItemViewType(position);
    }

    @Override
    protected int getLayoutIdByType(int type) {
        if (type == NORMAL) {
            return R.layout.adapter_video_replay;
        }
        return super.getLayoutIdByType(type);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(int type, View view) {
        if (type == NORMAL) {
            return new VideoReplayViewHolder(context, view);
        }
        return null;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos, Replies.ReplyListBean replyListBean) {
        if (viewHolder instanceof VideoReplayViewHolder) {
            VideoReplayViewHolder replayViewHolder = (VideoReplayViewHolder)viewHolder;
            replayViewHolder.setData(replyListBean);
        }
    }



}
