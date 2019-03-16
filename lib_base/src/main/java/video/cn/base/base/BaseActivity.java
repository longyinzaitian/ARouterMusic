package video.cn.base.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;
import video.cn.base.MyApplication;
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
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        MyApplication.getInstance().watchObj(this);
    }
}
