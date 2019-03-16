package video.cn.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.Locale;

import video.cn.base.base.BaseActivity;
import video.cn.base.bean.EventBusBean;
import video.cn.base.utils.RouteUtils;
import video.cn.me.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.ME_TEXT_ONE)
public class TextOneActivity extends BaseActivity {

    /**
     * eventBus数据接收页面
     */
    private TextView mTextView;
    /**
     * eventBus返回数据
     */
    private Button mBtnBackData;

    private String name;

    private long age;
    private EventBusBean eventBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        initData();
        initView();
    }

    private void initData() {
        name = getIntent().getStringExtra("name");
        age = getIntent().getLongExtra("age", 0);
        eventBus = getIntent().getParcelableExtra("eventbus");
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        mBtnBackData = findViewById(R.id.btn_back_data);
        mBtnBackData.setVisibility(View.GONE);

        String str = String.format(Locale.getDefault(), "name=%s,\tage=%d,\tproject=%s,\tnum=%d",
                                                name, age, eventBus.getProject(), eventBus.getNum());
        mTextView.setText(str);
    }


}
