package video.cn.home.act.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import video.cn.base.base.BaseActivity;
import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.SpUtil;
import video.cn.home.R;
import video.cn.home.act.query.QueryActivity;

/**
 * @author husy
 * @date 2019/9/8
 */
public class SearchActivity extends BaseActivity implements SearchContract.SearchView {

    private TagFlowLayout flowLayout;
    private ImageView imageView;
    private EditText searchEdit;
    private ImageView searchIcon;

    private SearchPresenter searchPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);

        initView();
        setData();
    }

    private void initView() {
        flowLayout = findViewById(R.id.act_search_flow_layout);
        imageView = findViewById(R.id.act_search_im);
        searchEdit = findViewById(R.id.act_search_edit);
        searchIcon = findViewById(R.id.act_search_ic);
    }

    private void setData() {
        searchPresenter = new SearchPresenter(this);
        searchPresenter.getHotKeys();

        GlideUtil.Companion.showImage(this, imageView, SpUtil.getLaunchPic(this), null);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = searchEdit.getText().toString().trim();
                QueryActivity.launchActivity(SearchActivity.this, key);
            }
        });

        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                QueryActivity.launchActivity(SearchActivity.this, searchPresenter.getHotKey(position));
                return false;
            }
        });
    }

    @Override
    public void setHotKeys(List<String> response) {
        flowLayout.setAdapter(new TagAdapter<String>(response) {
            @Override
            public View getView(FlowLayout parent, int position, String tag) {
                TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.tag_flow_item_layout, parent, false);
                textView.setText(tag);
                return textView;
            }
        });
    }
}
