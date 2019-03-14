package video.cn.me.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.simple.eventbus.EventBus;

import java.util.Locale;

import video.cn.base.bean.EventBusBean;
import video.cn.base.data.EvenBusTag;
import video.cn.base.utils.RouteUtils;
import video.cn.me.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.ME_EVENT_BUS)
public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {

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
        ARouter.getInstance().inject(this);
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
        mBtnBackData.setOnClickListener(this);

        mTextView.setText(String.format(Locale.getDefault(),"name=%s,\tage=%d,\tproject=%s,\tnum=%d",
                name, age, eventBus.getProject(), eventBus.getNum()));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_back_data) {
            EventBus.getDefault().post(name, EvenBusTag.GOTO_EVENT_BUS);
            finish();
        }
    }
}
