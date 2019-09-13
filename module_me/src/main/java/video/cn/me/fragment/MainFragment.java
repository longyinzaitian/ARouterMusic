package video.cn.me.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.cn.base.base.BaseFragment;
import video.cn.base.bean.EventBusBean;
import video.cn.base.bean.JavaBean;
import video.cn.base.utils.RouteUtils;
import video.cn.me.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
//@Route(path = RouteUtils.ME_FRAGMENT_MAIN)
public class MainFragment extends BaseFragment implements View.OnClickListener {

    /**
     * 依赖注入
     */
    private Button mAutoInject;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(View view) {
        mAutoInject = view.findViewById(R.id.autoInject);
        mAutoInject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.autoInject) {
            /*
             * 序列化过得
             * 必须先初始化JsonServiceImpl实现SerializationService
             */
            EventBusBean eventBusBean = new EventBusBean();
            eventBusBean.setProject("android");
            eventBusBean.setNum(3);
            /*
             * 普通的javaBean
             */
            JavaBean javaBean = new JavaBean();
            javaBean.setName("huangxiaoguo");
            javaBean.setAge(25);

            List<JavaBean> objList = new ArrayList<>();
            objList.add(javaBean);

            Map<String, List<JavaBean>> map = new HashMap<>();
            map.put("testMap", objList);

            ARouter.getInstance().build(RouteUtils.ME_INJECT)
                    .withString("name", "老王")
                    .withInt("age", 18)
                    .withBoolean("boy", true)
                    .withLong("high", 180)
                    .withString("url", "https://www.baidu.com")
                    .withParcelable("pac", eventBusBean)
                    .withObject("obj", javaBean)
                    .withObject("objList", objList)
                    .withObject("map", map)
                    .navigation();
        }
    }
}