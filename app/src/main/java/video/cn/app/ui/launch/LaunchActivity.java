package video.cn.app.ui.launch;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import video.cn.app.MainActivity;
import video.cn.app.R;
import video.cn.base.base.BaseActivity;
import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.ThreadCenter;
import video.cn.base.utils.UiUtils;

/**
 *
 * @author husyin
 * @date 2019年3月10日
 *
 */

public class LaunchActivity extends BaseActivity implements LaunchContract.LaunchView {

    private static final int SLEEP_TIME = 6000;
    private static final int REQUEST_PERMISSION_SETTING = 99;
    @BindView(R.id.splash_root)
    ImageView mSplashRoot;

    private LaunchContract.LaunchPresenter mLaunchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mUnBinder = ButterKnife.bind(this);
        mLaunchPresenter = new LaunchPresenter(this);
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);
    }

    @Override
    protected void onGrantedPermission() {
        super.onGrantedPermission();
        if (mLaunchPresenter != null) {
            mLaunchPresenter.getImage();
        }
    }

    @Override
    protected void onDeniedPermission() {
        super.onDeniedPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean rationale = shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            boolean rationalePhone = shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE);
            if (!rationale || !rationalePhone) {
                UiUtils.showToast("请打开读写权限和电话权限");
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
            } else {
                finish();
            }
        }
    }

    @Override
    public void showFlash(String url) {
        GlideUtil.Companion.showImage(LaunchActivity.this, mSplashRoot, url, new GlideUtil.Companion.ImageListener() {
            @Override
            public void onFailed() {

            }

            @Override
            public void onReady(@NotNull Drawable resource) {
                runAlphaAnim(resource);
            }
        });
    }

    private void runAlphaAnim(Drawable resource) {
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                jump();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }
        });
        mSplashRoot.startAnimation(animation);
        mSplashRoot.setImageDrawable(resource);
    }

    private void jump() {
        ThreadCenter.Companion.getThreadCenter().postRunnable( () -> {
            long start = System.currentTimeMillis();
            long costTime = System.currentTimeMillis() - start;
            if (SLEEP_TIME - costTime > 0) {
                try {
                    Thread.sleep(SLEEP_TIME - costTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);
        }
    }

    @Override
    public void loadLaunchFail() {
        jump();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLaunchPresenter != null) {
            mLaunchPresenter.destroy();
        }
    }
}
