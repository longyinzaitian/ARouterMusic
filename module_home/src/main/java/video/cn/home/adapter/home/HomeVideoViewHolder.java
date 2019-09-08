package video.cn.home.adapter.home;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.husy.network.model.Data;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;
import cn.jzvd.JzvdStd;
import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.LogUtil;
import video.cn.home.R;
import video.cn.home.widget.HomeVideoAuthorView;
import video.cn.home.widget.HomeVideoMenuView;

/**
 * @author husy
 * @date 2019/9/5
 */
public class HomeVideoViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private JzvdStd jzvdStd;
    private HomeVideoAuthorView authorView;
    private ExpandableTextView expandableTextView;
    private HomeVideoMenuView videoMenuView;

    public HomeVideoViewHolder(Context context,  @NonNull View itemView) {
        super(itemView);
        mContext = context;
        jzvdStd = itemView.findViewById(R.id.home_video_player);
        authorView = itemView.findViewById(R.id.home_video_author);
        expandableTextView = itemView.findViewById(R.id.home_video_des);
        videoMenuView = itemView.findViewById(R.id.home_video_menu);
    }

    public void setData(Data data) {
        jzvdStd.setUp(data.playUrl, data.title, JzvdStd.SCREEN_NORMAL);

        authorView.setInfo(data);
        expandableTextView.updateForRecyclerView(data.description, 0 , ExpandableTextView.STATE_SHRINK);
        videoMenuView.setData(data);

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
