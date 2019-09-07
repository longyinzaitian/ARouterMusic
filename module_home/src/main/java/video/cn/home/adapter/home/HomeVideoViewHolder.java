package video.cn.home.adapter.home;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.husy.network.model.Data;

import cn.jzvd.JzvdStd;
import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.LogUtil;
import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/5
 */
public class HomeVideoViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private JzvdStd jzvdStd;

    public HomeVideoViewHolder(Context context,  @NonNull View itemView) {
        super(itemView);
        mContext = context;
        jzvdStd = itemView.findViewById(R.id.home_video_player);
    }

    public void setData(Data data) {
        jzvdStd.setUp(data.playUrl, data.title, JzvdStd.SCREEN_NORMAL);
        if (data.cover == null) {
            LogUtil.Companion.i("HomeVideoViewHolder", "data:" + data);
            return;
        }
        String homePage = data.cover.feed;
        if (TextUtils.isEmpty(homePage)) {
            homePage = data.cover.detail;
        }

        if (TextUtils.isEmpty(homePage)) {
            homePage = data.cover.blurred;
        }

        if (TextUtils.isEmpty(homePage)) {
            LogUtil.Companion.i("HomeVideoViewHolder", "data:" + data);
        }
        GlideUtil.Companion.showImage((Activity) mContext, jzvdStd.thumbImageView, homePage, null);
    }


}
