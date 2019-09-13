package video.cn.recommend.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.husy.network.adapter.HomeFrAdapter;
import com.husy.network.model.ItemList;

import java.util.List;

import video.cn.base.base.BaseActivity;
import video.cn.base.base.BaseAdapter;
import video.cn.base.utils.RouteUtils;
import video.cn.base.widget.CommonTitle;
import video.cn.recommend.R;

/**
 * @author husy
 * @date 2019/9/13
 */
public class RecommendActivity extends BaseActivity
        implements RecommendContract.RecommendView {

    private static final String ID = "id";
    private static final String TITLE = "title";

    private CommonTitle commonTitle;
    private RecyclerView recyclerView;

    private HomeFrAdapter homeFrAdapter;
    private RecommendPresenter recommendPresenter;

    public static void launch(Context context, int id, String title) {
        Intent intent = new Intent(context, RecommendActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recommend);
        initView();
        initData();
    }

    private void initView() {
        commonTitle = findViewById(R.id.act_rec_title);
        recyclerView = findViewById(R.id.act_rec_recycler);
    }

    private void initData() {
        homeFrAdapter = new HomeFrAdapter(this);
        homeFrAdapter.setShowMore(false);
        recyclerView.setAdapter(homeFrAdapter);

        recommendPresenter = new RecommendPresenter(this);
        int id = getIntent().getIntExtra(ID, 12);
        String title = getIntent().getStringExtra(TITLE);
        commonTitle.setText(title);
        recommendPresenter.loadData(id, false);

        homeFrAdapter.setListListener(new BaseAdapter.ListListener<ItemList>() {
            @Override
            public void onClickLoadMore() {

            }

            @Override
            public void onItemClick(ItemList itemList) {
                ARouter.getInstance().build(RouteUtils.HOME_VIDEO_DETAIL)
                        .withParcelable("item", itemList)
                        .navigation();
            }
        });
    }

    @Override
    public void setData(List<ItemList> itemLists) {
        homeFrAdapter.setData(itemLists);
    }
}
