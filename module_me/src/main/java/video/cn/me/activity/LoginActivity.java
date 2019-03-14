package video.cn.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.Locale;

import video.cn.base.utils.RouteUtils;
import video.cn.base.utils.UiUtils;
import video.cn.me.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.ME_LOGIN)
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 跳转ForResult
     */
    private Button mBtnForResult;
    /**
     * 拦截器操作
     */
    private Button mInterceptor;
    /**
     * 登录完成后要跳转的路径
     */
    private TextView mPath;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
        initView();
    }

    private void initData() {
        path = getIntent().getStringExtra("path");
    }

    private void initView() {
        mBtnForResult = findViewById(R.id.btn_forResult);
        mBtnForResult.setOnClickListener(this);
        mInterceptor = findViewById(R.id.interceptor);
        mInterceptor.setOnClickListener(this);
        mPath = findViewById(R.id.path);

        if (!TextUtils.isEmpty(path)) {
            String pathStr = String.format(Locale.getDefault(), "登录完成后要跳转的路径===>%s", path);
            mPath.setText(pathStr);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_forResult) {
            //跳转ForResult,在fragment中使用不起作用
            ARouter.getInstance()
                    .build(RouteUtils.CHAT_FOR_RESULT)
                    .navigation(this, 666);

        } else if (id == R.id.interceptor) {
            ARouter.getInstance()
                    .build(RouteUtils.CHAT_INTERCEPTOR)
                    .navigation(this, new NavCallback() {
                        @Override
                        public void onFound(Postcard postcard) {
                            super.onFound(postcard);
                            //路由目标被发现时调用
                            Log.e("huangxiaoguo", "发现了");
                        }

                        @Override
                        public void onLost(Postcard postcard) {
                            super.onLost(postcard);
                            //路由被丢失时调用
                            Log.e("huangxiaoguo", "丢失了");
                        }

                        @Override
                        public void onArrival(Postcard postcard) {
                            //路由到达之后调用
                            Log.e("huangxiaoguo", "到达了");
                        }

                        @Override
                        public void onInterrupt(Postcard postcard) {
                            super.onInterrupt(postcard);
                            //路由被拦截时调用
                            Log.e("huangxiaoguo", "拦截了");
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 666:
                String name = data.getStringExtra("name");
                UiUtils.showToast(name + ",resultCode===>" + resultCode);
                break;
            default:
                break;
        }
    }
}
