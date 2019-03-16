package video.cn.app;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import video.cn.base.base.BaseActivity;
import video.cn.base.utils.L;
import video.cn.base.utils.ThreadCenter;

/**
 *
 * @author husyin
 * @date 2019年3月10日
 *
 */

public class LaunchActivity extends BaseActivity {

    private static final int SLEEP_TIME = 2000;

    @BindView(R.id.splash_root)
    ImageView mSplashRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mUnBinder = ButterKnife.bind(this);
        initView();
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void initView() {
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        mSplashRoot.startAnimation(animation);
    }

    @Override
    protected void onGrantedPermission() {
        super.onGrantedPermission();
        runAlphaAnim();
    }

    @Override
    protected void onDeniedPermission() {
        super.onDeniedPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean rationale = shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            L.Companion.i("launch", "rationale:" + rationale);
            if (!rationale) {
                // show dialog tip grand permission
            } else {
                runAlphaAnim();
            }
        }
    }

    private void runAlphaAnim() {
        ThreadCenter.Companion.getThreadCenter().postRunnable(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long costTime = System.currentTimeMillis() - start;
                //wait
                if (SLEEP_TIME - costTime > 0) {
                    try {
                        Thread.sleep(SLEEP_TIME - costTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
