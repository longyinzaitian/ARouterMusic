package video.cn.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.utils.RouteUtils;
import video.cn.chat.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.CHAT_INTERCEPTOR)
public class InterceptorActivity extends AppCompatActivity {

    /**
     * eventBus数据接收页面
     */
    private TextView mTextView;
    private String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interceptor);
        initData();
        initView();
    }

    private void initData() {
        extra = getIntent().getStringExtra("extra");

    }
    private void initView() {
        mTextView = findViewById(R.id.textView);
        mTextView.setText("extra==>" + extra);
    }
}
