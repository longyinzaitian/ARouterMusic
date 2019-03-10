package video.cn.me.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.utils.RouteUtils;
import video.cn.me.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.Me_WebView)
public class WebViewActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        mWebview.loadUrl(getIntent().getStringExtra("url"));
    }
}
