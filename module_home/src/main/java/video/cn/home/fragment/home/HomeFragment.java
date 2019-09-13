package video.cn.home.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.husy.network.bingimage.LaunchResponse;
import com.husy.network.model.ItemList;

import java.util.List;

import video.cn.base.base.AbstractCustomRecyclerScrollListener;
import video.cn.base.base.BaseAdapter;
import video.cn.base.base.BaseFragment;
import video.cn.base.utils.LogUtil;
import video.cn.base.utils.RouteUtils;
import video.cn.home.R;
import video.cn.home.act.detail.VideoDetailActivity;
import video.cn.home.act.search.SearchActivity;
import com.husy.network.adapter.HomeFrAdapter;

/**
 * @author husy
 * @date 2019/9/1
 */
@Route(path = RouteUtils.HOME_FRAGMENT_MAIN)
public class HomeFragment extends BaseFragment implements HomeContract.MainView {
    private static final int SEARCH_HEIGHT = 400;
    private static final float SEARCH_ALPHA = 0.8f;

    private RelativeLayout mSearchRv;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeContract.MainPresenter mHomePresenter;

    private HomeFrAdapter mHomeFrAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_new, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(@NonNull View view) {
        mRecyclerView = view.findViewById(R.id.home_recycler);
        mSwipeRefreshLayout = view.findViewById(R.id.home_swipe_refresh);
        mSearchRv = view.findViewById(R.id.home_search_rv);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mHomePresenter != null) {
                    mHomePresenter.getHomeInfo();
                }
            }
        });

        mHomeFrAdapter = new HomeFrAdapter(getActivity());
        mRecyclerView.setAdapter(mHomeFrAdapter);

        mSearchRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        mHomeFrAdapter.setListListener(new BaseAdapter.ListListener<ItemList>() {
            @Override
            public void onClickLoadMore() {
                loadMore();
            }

            @Override
            public void onItemClick(ItemList itemList) {
                VideoDetailActivity.launchAct(getActivity(), itemList);
            }
        });

        mRecyclerView.setOnScrollListener(new AbstractCustomRecyclerScrollListener() {
            @Override
            public void onLoadMore() {
                HomeFragment.this.loadMore();
            }

            @Override
            public void onScroll(RecyclerView recyclerView, int firstVisibleItem) {
                if (firstVisibleItem == 0) {
                    int top = -recyclerView.getChildAt(0).getTop();
                    if (top < 0) {
                        return;
                    }

                    LogUtil.Companion.i("tag", "top:" + top + ", first visible item:" + firstVisibleItem);
                    float alpha = (SEARCH_HEIGHT + 0.0f - top)/SEARCH_HEIGHT;
                    if (alpha < 0) {
                        alpha = 0;
                    }

                    if (alpha >= SEARCH_ALPHA) {
                        alpha = SEARCH_ALPHA;
                    }
                    mSearchRv.setAlpha(alpha);
                    if (alpha == 0) {
                        mSearchRv.setVisibility(View.GONE);
                    } else {
                        mSearchRv.setVisibility(View.VISIBLE);
                    }
                } else {
                    mSearchRv.setAlpha(0);
                    mSearchRv.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadMore() {
        if (mHomePresenter != null) {
            mHomePresenter.getHomeInfoMore();
        }
    }

    private void initData() {
        mHomePresenter = new HomeFrPresenter(this);
        mHomePresenter.getHomeInfo();
    }

    @Override
    public void setData(List<ItemList> itemLists) {
        mSwipeRefreshLayout.setRefreshing(false);
        mHomeFrAdapter.setData(itemLists);
    }

    @Override
    public void addData(List<ItemList> itemLists) {
        mHomeFrAdapter.addData(itemLists);
    }

    @Override
    public void setBanner(List<LaunchResponse.LaunchImage> images) {
        mHomeFrAdapter.setBanner(images);
    }
}
