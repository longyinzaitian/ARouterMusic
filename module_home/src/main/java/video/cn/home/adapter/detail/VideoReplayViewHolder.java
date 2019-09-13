package video.cn.home.adapter.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.husy.network.model.Replies;

import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.TimeUtils;
import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/10
 */
public class VideoReplayViewHolder extends RecyclerView.ViewHolder {

    private ImageView avatar;
    private TextView name;
    private TextView time;
    private TextView msg;

    private Context context;

    public VideoReplayViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        avatar = itemView.findViewById(R.id.replay_avatar);
        name = itemView.findViewById(R.id.replay_name);
        time = itemView.findViewById(R.id.replay_time);
        msg = itemView.findViewById(R.id.replay_msg);
    }

    public void setData(Replies.ReplyListBean replyListBean) {
        Replies.ReplyListBean.UserBean userBean = replyListBean.getUser();
        if (userBean == null) {
            return;
        }
        if (!TextUtils.isEmpty(userBean.getAvatar())) {
            GlideUtil.Companion.showCircleImage(context, avatar, userBean.getAvatar(), null);
        }

        if (!TextUtils.isEmpty(userBean.getNickname())) {
            name.setText(userBean.getNickname());
        }

        if (!TextUtils.isEmpty(replyListBean.getCreateTime()+"")) {
            time.setText(TimeUtils.getTimeFormat(replyListBean.getCreateTime()));
        }

        if (!TextUtils.isEmpty(replyListBean.getMessage())) {
            msg.setText(replyListBean.getMessage());
        }
    }

}
