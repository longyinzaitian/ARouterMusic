package video.cn.base.base;

import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * @author husy
 * @date 2019/3/16
 */
public class BaseActivity extends AppCompatActivity {
    protected Unbinder mUnBinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }
}
