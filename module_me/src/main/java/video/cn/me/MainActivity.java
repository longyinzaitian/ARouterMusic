package video.cn.me;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import video.cn.base.base.BaseActivity;
import video.cn.me.fragment.me.MeFragment;

/**
 * @author husyin
 * @date 2019年3月10日
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main, new MeFragment())
                .commitAllowingStateLoss();
    }
}
