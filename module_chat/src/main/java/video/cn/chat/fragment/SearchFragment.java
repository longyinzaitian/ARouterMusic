package video.cn.chat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import video.cn.base.base.BaseFragment;
import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.RouteUtils;
import video.cn.base.utils.SpUtil;
import video.cn.chat.R;

/**
 * @author husy
 * @date 2019/9/13
 */
@Route(path = RouteUtils.CHAT_FRAGMENT_MAIN)
public class SearchFragment extends BaseFragment implements SearchContract.SearchView {

    private TagFlowLayout flowLayout;
    private ImageView imageView;
    private EditText searchEdit;
    private ImageView searchIcon;

    private SearchPresenter searchPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setData();
    }

    private void initView(View view) {
        flowLayout = view.findViewById(R.id.frm_search_flow_layout);
        imageView = view.findViewById(R.id.frm_search_im);
        searchEdit = view.findViewById(R.id.frm_search_edit);
        searchIcon = view.findViewById(R.id.frm_search_ic);
    }

    private void setData() {
        searchPresenter = new SearchPresenter(this);
        searchPresenter.getHotKeys();

        GlideUtil.Companion.showImage(this, imageView, SpUtil.getLaunchPic(getActivity()), null);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = searchEdit.getText().toString().trim();
                ARouter.getInstance().build(RouteUtils.QUERY_SEARCH_ACT)
                        .withString("query", key)
                        .navigation();
            }
        });

        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ARouter.getInstance().build(RouteUtils.QUERY_SEARCH_ACT)
                        .withString("query", searchPresenter.getHotKey(position))
                        .navigation();
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
