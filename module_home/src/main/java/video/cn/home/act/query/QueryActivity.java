package video.cn.home.act.query;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.husy.network.model.ItemList;
import com.husy.network.model.SearchResult;

import video.cn.base.base.AbstractCustomRecyclerScrollListener;
import video.cn.base.base.BaseActivity;
import video.cn.base.base.BaseAdapter;
import video.cn.base.widget.CommonTitle;
import video.cn.home.R;
import video.cn.home.act.detail.VideoDetailActivity;
import video.cn.home.adapter.home.HomeFrAdapter;

/**
 * @author husy
 * @date 2019/9/9
 */
public class QueryActivity extends BaseActivity implements QueryContract.QueryView {

    private static final String KEY = "query";
    private RecyclerView queryRecyclerView;
    private CommonTitle commonTitle;

    private QueryPresenter queryPresenter;
    private HomeFrAdapter homeFrAdapter;
    private String queryKey;

    public static void launchActivity(Context context, String key) {
        Intent intent = new Intent(context, QueryActivity.class);
        intent.putExtra(KEY, key);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_query);

        queryRecyclerView = findViewById(R.id.act_query_list);
        commonTitle = findViewById(R.id.act_query_title);
        setData();
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        queryPresenter = new QueryPresenter(this);
        queryKey = intent.getStringExtra(KEY);
        queryPresenter.query(queryKey, false);

        commonTitle.setText("搜索结果：" + queryKey);

        homeFrAdapter = new HomeFrAdapter(QueryActivity.this);
        queryRecyclerView.setAdapter(homeFrAdapter);

        queryRecyclerView.setOnScrollListener(new AbstractCustomRecyclerScrollListener() {
            @Override
            public void onLoadMore() {
                onLoadMoreQuery();
            }

            @Override
            public void onScroll(RecyclerView recyclerView, int firstVisibleItem) {

            }
        });

        homeFrAdapter.setListListener(new BaseAdapter.ListListener<ItemList>() {
            @Override
            public void onClickLoadMore() {
                onLoadMoreQuery();
            }

            @Override
            public void onItemClick(ItemList itemList) {
                VideoDetailActivity.launchAct(QueryActivity.this, itemList);
            }
        });
    }

    private void onLoadMoreQuery() {
        if (TextUtils.isEmpty(queryKey)) {
            return;
        }
        queryPresenter.query(queryKey, true);
    }

    @Override
    public void setQueryResult(SearchResult response) {
        homeFrAdapter.setData(response.getItemList());
    }

    @Override
    public void addQueryResult(SearchResult response) {
        if (response == null) {
            homeFrAdapter.addData(null);
            return;
        }
        homeFrAdapter.addData(response.getItemList());
    }
}
