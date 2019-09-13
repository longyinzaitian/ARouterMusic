package video.cn.me.fragment.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.base.BaseFragment;
import video.cn.base.utils.CacheUtil;
import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.RouteUtils;
import video.cn.base.utils.SpUtil;
import video.cn.base.utils.ThreadCenter;
import video.cn.me.R;
import video.cn.me.act.AboutActivity;
import video.cn.me.act.AuthorActivity;

/**
 * @author husy
 * @date 2019/9/13
 */
@Route(path = RouteUtils.ME_FRAGMENT_MAIN)
public class MeFragment extends BaseFragment {

    private ImageView imageView;
    private RelativeLayout clearRv;
    private TextView cacheTx;
    private RelativeLayout aboutRv;
    private RelativeLayout authorRv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frm_me_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }

    private void initView(View view) {
        imageView = view.findViewById(R.id.frm_me_image);
        cacheTx = view.findViewById(R.id.frm_me_cache_text);
        clearRv = view.findViewById(R.id.frm_me_clear);
        aboutRv = view.findViewById(R.id.frm_me_about_app);
        authorRv = view.findViewById(R.id.frm_me_author);
    }

    private void initData() {
        String url = SpUtil.getLaunchPic(getActivity());
        if (!TextUtils.isEmpty(url)) {
            GlideUtil.Companion.showImage(this, imageView, url, null);
        }

        clearRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadCenter.Companion.getThreadCenter().postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (getActivity() == null || getActivity().isFinishing()) {
                            return;
                        }
                        CacheUtil.clearAllCache(getActivity());
                        calculateCache();
                    }
                });
            }
        });

        aboutRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });

        authorRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AuthorActivity.class));
            }
        });

        calculateCache();
    }

    private void calculateCache() {
        ThreadCenter.Companion.getThreadCenter().postRunnable(new Runnable() {
            @Override
            public void run() {
                final String total = CacheUtil.getTotalCacheSize(getActivity());
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }
                ThreadCenter.Companion.getThreadCenter().postUiRunnable(getActivity(), new Runnable() {
                    @Override
                    public void run() {
                        cacheTx.setText(total);
                    }
                });
            }
        });
    }
}
