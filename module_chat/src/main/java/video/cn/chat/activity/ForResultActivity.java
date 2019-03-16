package video.cn.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.base.BaseActivity;
import video.cn.base.utils.RouteUtils;
import video.cn.chat.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.CHAT_FOR_RESULT)
public class ForResultActivity extends BaseActivity implements View.OnClickListener {

    private Button mFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);
        initView();
    }

    private void initView() {
        mFinish = findViewById(R.id.finish);
        mFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.finish) {
            Intent intent = new Intent();
            intent.putExtra("name", "ForResult返回的数据");
            setResult(999, intent);
            finish();
        }
    }
}
