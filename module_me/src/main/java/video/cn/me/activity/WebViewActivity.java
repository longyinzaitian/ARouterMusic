package video.cn.me.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.base.BaseActivity;
import video.cn.base.utils.RouteUtils;
import video.cn.me.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.ME_WEB_VIEW)
public class WebViewActivity extends BaseActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    private void initView() {
        mWebview = findViewById(R.id.webview);
        mWebview.loadUrl(getIntent().getStringExtra("url"));
    }
}
