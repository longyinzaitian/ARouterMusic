package video.cn.home.act.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alibaba.android.arouter.launcher.ARouter;

import video.cn.base.base.BaseActivity;
import video.cn.base.utils.RouteUtils;
import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/8
 */
public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);

        initView();
    }

    private void initView() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.CHAT_FRAGMENT_MAIN).navigation();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_search, fragment)
                .commitAllowingStateLoss();
    }
}
