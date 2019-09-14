package video.cn.base.base;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.Unbinder;
import cn.jzvd.Jzvd;
import video.cn.base.BaseApplication;
import video.cn.base.utils.MemoryLeakUtil;
import video.cn.base.utils.PermissionReq;

/**
 * @author husy
 * @date 2019/3/16
 */
public class BaseActivity extends AppCompatActivity {
    protected Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void requestPermission(@NonNull String... permissions) {
        PermissionReq.with(this).permissions(permissions).result(new PermissionReq.Result() {
            @Override
            public void onGranted() {
                onGrantedPermission();
            }

            @Override
            public void onDenied() {
                onDeniedPermission();
            }
        }).request();
    }

    protected void onGrantedPermission() {

    }

    protected void onDeniedPermission() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionReq.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public synchronized ComponentName startForegroundServiceAsUser(Intent service, UserHandle user) {
        return null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onLowMemory() {
        Glide.get(this).onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Glide.get(this).onTrimMemory(level);
        super.onTrimMemory(level);
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        MemoryLeakUtil.fixInputMethodMemoryLeak(this);
        super.onDestroy();
        BaseApplication.getInstance().watchObj(this);
    }
}
