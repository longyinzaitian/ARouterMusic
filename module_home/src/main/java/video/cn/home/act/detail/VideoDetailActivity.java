package video.cn.home.act.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.husy.network.model.Data;
import com.husy.network.model.ItemList;
import com.husy.network.model.Replies;

import java.util.List;

import cn.jzvd.JzvdStd;
import video.cn.base.base.AbstractCustomRecyclerScrollListener;
import video.cn.base.base.BaseActivity;
import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.LogUtil;
import video.cn.base.utils.RouteUtils;
import video.cn.base.widget.CommonTitle;
import video.cn.home.R;
import video.cn.home.adapter.detail.VideoDetailAdapter;
import com.husy.network.widget.HomeVideoAuthorView;
import com.husy.network.widget.HomeVideoMenuView;

/**
 * @author husy
 * @date 2019/9/10
 */
@Route(path = RouteUtils.HOME_VIDEO_DETAIL)
public class VideoDetailActivity extends BaseActivity implements VideoDetailContract.VideoDetailView {

    private static final String ITEM = "item";

    private CommonTitle commonTitle;
    private HomeVideoAuthorView authorView;
    private HomeVideoMenuView menuView;
    private JzvdStd jzvdStd;
    private RecyclerView recyclerView;
    private VideoDetailAdapter videoDetailAdapter;

    private VideoPresenter videoPresenter;
    private int id;

    public static void launchAct(Context context, ItemList itemList) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra(ITEM, itemList);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_video_detail);

        initView();
        setData();
    }

    private void initView() {
        commonTitle = findViewById(R.id.act_video_detail_title);
        authorView = findViewById(R.id.act_video_detail_author);
        menuView = findViewById(R.id.act_video_detail_menu);
        jzvdStd = findViewById(R.id.act_video_detail_jz);
        recyclerView = findViewById(R.id.act_video_detail_rv);
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        ItemList itemList = intent.getParcelableExtra(ITEM);
        if (itemList == null) {
            return;
        }

        Data data = itemList.data;
        id = data.id;
        commonTitle.setText(data.title);
        authorView.setInfo(data);
        menuView.setData(data);
        jzvdStd.setUp(data.playUrl, data.title, JzvdStd.SCREEN_NORMAL);
        setThumbImage(data);
        loadData();
    }

    private void loadData() {
        videoDetailAdapter = new VideoDetailAdapter(this);
        recyclerView.setAdapter(videoDetailAdapter);
        videoPresenter = new VideoPresenter(this);
        videoPresenter.loadReplay(id);

        recyclerView.setOnScrollListener(new AbstractCustomRecyclerScrollListener() {
            @Override
            public void onLoadMore() {
                videoPresenter.loadMoreReplay(id);
            }

            @Override
            public void onScroll(RecyclerView recyclerView, int firstVisibleItem) {

            }
        });
    }

    @Override
    public void setReplayData(List<Replies.ReplyListBean> replayData) {
        videoDetailAdapter.setData(replayData);
    }

    @Override
    public void addReplayData(List<Replies.ReplyListBean> replayData) {
        videoDetailAdapter.addData(replayData);
    }

    private void setThumbImage(Data data) {
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
        GlideUtil.Companion.showImage(VideoDetailActivity.this, jzvdStd.thumbImageView, homePage, null);
    }

}
