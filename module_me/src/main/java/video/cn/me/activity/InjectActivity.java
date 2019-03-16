package video.cn.me.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;
import java.util.Map;

import video.cn.base.base.BaseActivity;
import video.cn.base.bean.EventBusBean;
import video.cn.base.bean.JavaBean;
import video.cn.base.utils.RouteUtils;
import video.cn.me.R;

/**
 * 依赖注入使用，注意：必须实现SerializationService进行注册
 *
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.ME_INJECT)
public class InjectActivity extends BaseActivity {
    @Autowired
    String name = "hahahha";

    @Autowired
    int age = 13;

    @Autowired(name = "boy")
    boolean sex;

    @Autowired
    long high = 160;

    @Autowired
    String url;

    @Autowired
    EventBusBean pac;

    @Autowired
    JavaBean obj;

    @Autowired
    List<JavaBean> objList;

    @Autowired
    Map<String, List<JavaBean>> map;

    @Autowired
    int height = 21;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
        ARouter.getInstance().inject(this);
        initView();
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        String params = String.format(
                "name=%s,\n age=%s, \n height=%s,\n girl=%s,\n high=%s,\n url=%s,\n pac=%s,\n obj=%s \n" +
                        "  objList=%s, \n map=%s",
                name,
                age,
                height,
                sex,
                high,
                url,
                pac.getProject(),
                obj.getName(),
                objList.get(0).getName(),
                map.get("testMap").get(0).getName()
        );
        mTextView.setText(params);
    }
}
