package video.cn.recommend.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.husy.network.bingimage.LaunchResponse;
import com.husy.network.model.ItemList;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import video.cn.base.base.BaseAdapter;
import video.cn.base.base.BaseFragment;
import video.cn.base.utils.GlideImageLoader;
import video.cn.base.utils.RouteUtils;
import video.cn.recommend.R;
import video.cn.recommend.act.RecommendActivity;
import video.cn.recommend.adapter.MainAdapter;

/**
 * @author  husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.RECOM_FRAGMENT_MAIN)
public class MainFragment extends BaseFragment implements MainContract.MainView {

    private Banner banner;
    private RecyclerView recyclerView;

    private MainAdapter mainAdapter;
    private MainPresenter mainPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frm_recom_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = view.findViewById(R.id.frm_recom_banner);
        recyclerView = view.findViewById(R.id.frm_recom_recycler);

        initData();
    }

    private void initData() {
        mainAdapter = new MainAdapter(getActivity());
        recyclerView.setAdapter(mainAdapter);

        mainPresenter = new MainPresenter(this);
        mainPresenter.loadData();

        mainAdapter.setListListener(new BaseAdapter.ListListener<ItemList>() {
            @Override
            public void onClickLoadMore() {

            }

            @Override
            public void onItemClick(ItemList itemList) {
                RecommendActivity.launch(getActivity(), itemList.data.id, itemList.data.title);
            }
        });
    }

    @Override
    public void setData(List<ItemList> itemLists) {
        mainAdapter.setData(itemLists);
    }

    @Override
    public void setBanner(List<String> images, final List<LaunchResponse.LaunchImage> launchImageList) {
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ARouter.getInstance().build(RouteUtils.HOME_MAIN_IMAGE)
                        .withInt("pos", position)
                        .withParcelableArrayList("launchImages", (ArrayList<? extends Parcelable>) launchImageList)
                        .navigation();
            }
        });
    }
}