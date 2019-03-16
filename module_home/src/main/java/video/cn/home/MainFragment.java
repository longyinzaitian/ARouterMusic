package video.cn.home;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.cn.base.base.BaseFragment;
import video.cn.base.bean.EventBusBean;
import video.cn.base.bean.JavaBean;
import video.cn.base.data.EvenBusTag;
import video.cn.base.provider.IChatModuleService;
import video.cn.base.service.ModuleRouteService;
import video.cn.base.utils.RouteUtils;
import video.cn.base.utils.UiUtils;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.HOME_FRAGMENT_MAIN)
public class MainFragment extends BaseFragment implements View.OnClickListener {

    /**
     * 登录（跨模块跳转Activity）
     */
    private Button mBtnGotoLogin;
    /**
     * 使用eventBus夸模块通信
     */
    private Button mBtnEventBus;
    /**
     * 模块间服务调用
     */
    private Button mBtnUseOtherModule;

    /**
     * 使用Uri应用内跳转
     */
    private Button mBtnUri;
    /**
     * 旧版本转场动画
     */
    private Button mOldVersionAnim;
    /**
     * 新版本转场动画
     */
    private Button mNewVersionAnim;
    /**
     * 通过URL跳转（web view）
     */
    private Button mNavByUrl;
    /**
     * 拦截器操作
     */
    private Button mInterceptor;
    /**
     * 依赖注入
     */
    private Button mAutoInject;
    /**
     * 模块间通过路径名称调用服务
     */
    private Button mBtnUseOtherModuleByName;
    /**
     * 模块间通过类名调用服务
     */
    private Button mBtnUseOtherModuleByType;
    /**
     * 跳转失败
     */
    private Button mFailNav;
    private View view;
    /**
     * 拦截器操作(利用原有分组)
     */
    private Button mInterceptor1;
    /**
     * 拦截器操作(绿色通道，跳过拦截器)
     */
    private Button mInterceptor2;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    private void initView(View view) {
        mBtnGotoLogin = view.findViewById(R.id.btn_goto_login);
        mBtnGotoLogin.setOnClickListener(this);

        mBtnEventBus = view.findViewById(R.id.btn_eventbus);
        mBtnEventBus.setOnClickListener(this);

        mBtnUseOtherModule = view.findViewById(R.id.btn_use_other_module);
        mBtnUseOtherModule.setOnClickListener(this);

        mBtnUri = view.findViewById(R.id.btn_uri);
        mBtnUri.setOnClickListener(this);

        mOldVersionAnim = view.findViewById(R.id.oldVersionAnim);
        mOldVersionAnim.setOnClickListener(this);

        mNewVersionAnim = view.findViewById(R.id.newVersionAnim);
        mNewVersionAnim.setOnClickListener(this);

        mNavByUrl = view.findViewById(R.id.navByUrl);
        mNavByUrl.setOnClickListener(this);

        mInterceptor = view.findViewById(R.id.interceptor);
        mInterceptor.setOnClickListener(this);

        mAutoInject = view.findViewById(R.id.autoInject);
        mAutoInject.setOnClickListener(this);

        mBtnUseOtherModuleByName = view.findViewById(R.id.btn_use_other_module_by_name);
        mBtnUseOtherModuleByName.setOnClickListener(this);

        mBtnUseOtherModuleByType = view.findViewById(R.id.btn_use_other_module_by_type);
        mBtnUseOtherModuleByType.setOnClickListener(this);

        mFailNav = view.findViewById(R.id.failNav);
        mFailNav.setOnClickListener(this);

        mInterceptor1 = view.findViewById(R.id.interceptor1);
        mInterceptor1.setOnClickListener(this);

        mInterceptor2 = view.findViewById(R.id.interceptor2);
        mInterceptor2.setOnClickListener(this);
    }

    /**
     * 记住这里要使用常量表达式，因为ADT14以后在library中，去掉了final修饰，
     * google建议使用if-else代替，不用纠结
     *
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_goto_login) {
            //登录（跨模块跳转Activity）
            ARouter.getInstance().build(RouteUtils.ME_LOGIN).navigation();
            
        } else if (id == R.id.btn_eventbus) {
            // 跳转并携带参数,基本涵盖所以类型传递，具体可以查看Postcard类
            //传递过去的值使用getIntent()接收
            //在fragment中无法使用ForResult进行夸模块传递数据
            //在activity中可以（详细请看LoginActivity）
            EventBusBean eventBusBean = new EventBusBean();
            eventBusBean.setProject("android");
            eventBusBean.setNum(3);

            ARouter.getInstance().build(RouteUtils.ME_EVENT_BUS)
                    .withString("name", "haungxiaoguo")
                    .withLong("age", 25)
                    .withParcelable("eventbus", eventBusBean)
                    .navigation();

        } else if (id == R.id.btn_uri) {
            EventBusBean eventBusBean = new EventBusBean();
            eventBusBean.setProject("android");
            eventBusBean.setNum(3);

            Uri testUriMix = Uri.parse("arouter://tsou.cn.huangxiaoguo/me/main/EventBus");
            ARouter.getInstance().build(testUriMix)
                    .withString("name", "haungxiaoguo")
                    .withLong("age", 25)
                    .withParcelable("eventbus", eventBusBean)
                    .navigation();

        } else if (id == R.id.oldVersionAnim) {
            testOldVersionNavAnim();

        } else if (id == R.id.newVersionAnim) {
            testNewVersionNavAnim(v);

        } else if (id == R.id.navByUrl) {
            testNavWeb();

        } else if (id == R.id.interceptor) {
            testInterceptor();

        } else if (id == R.id.interceptor1) {
            //拦截器操作(利用原有分组)
            ARouter.getInstance().build(RouteUtils.NEED_LOGIN_TEST_3)
                    .navigation();

        } else if (id == R.id.interceptor2) {
            testInterceptorForGreen();

        } else if (id == R.id.autoInject) {
            testInjectData();

        } else if (id == R.id.btn_use_other_module) {
            //模块间服务调用
            //例如home模块调用chat模块的方法
            String userName = ModuleRouteService.getUserName("userId");
            UiUtils.showToast(userName);

        } else if (id == R.id.btn_use_other_module_by_name) {
            testCallModuleServiceByName();

        } else if (id == R.id.btn_use_other_module_by_type) {
            testCallModuleServiceByClass();

        } else if (id == R.id.failNav) {
            testNavFail();
        }
    }

    private void testOldVersionNavAnim() {
        //旧版本转场动画
        ARouter.getInstance()
            .build(RouteUtils.ME_TEST)
            .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
            //context上下文不传无效果
            .navigation(getContext());
    }

    private void testNewVersionNavAnim(View v) {
        //新版本转场动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.
                    makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
            ARouter.getInstance()
                .build(RouteUtils.ME_TEST)
                .withOptionsCompat(compat)
                .navigation();
        } else {
            UiUtils.showToast("API < 16,不支持新版本动画");
        }
    }

    private void testNavWeb() {
        //通过URL跳转（webview）
        ARouter.getInstance()
                .build(RouteUtils.ME_WEB_VIEW)
                .withString("url", "file:///android_asset/schame-test.html")
                .navigation();
    }

    private void testInterceptor() {
        /*
         * 如果利用重新分组，就需要在build中进行指定的分组不然没有效果
         */
        ((Postcard)(ARouter.getInstance()
                .build(RouteUtils.ME_TEST_2)
                .setGroup("needLogin")))
                .navigation(getContext(), new NavCallback() {
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

    private void testInterceptorForGreen() {
        //拦截器操作(绿色通道，跳过拦截器)
        ARouter.getInstance().build(RouteUtils.NEED_LOGIN_TEST_3)
                .withString("extra", "我是绿色通道直接过来的，不经过拦截器")
                .greenChannel()
                .navigation();
    }

    private void testInjectData() {
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

        Map<String, List<JavaBean>> map = new HashMap<>(2);
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

    private void testCallModuleServiceByName() {
        //模块间通过路径名称调用服务
        String userName = ((IChatModuleService) ARouter.getInstance()
                .build(RouteUtils.SERVICE_CHAT)
                .navigation())
                .getUserName("userid");
        UiUtils.showToast(userName);
    }

    private void testCallModuleServiceByClass() {
        //模块间通过类名调用服务
        String userName = ARouter.getInstance()
                .navigation(IChatModuleService.class)
                .getUserName("user id");
        UiUtils.showToast(userName);
    }

    private void testNavFail() {
        //跳转失败
        ARouter.getInstance().build("/xxx/xxx").navigation(getContext(), new NavCallback() {
            @Override
            public void onFound(Postcard postcard) {
                UiUtils.showToast("找到了");
            }

            @Override
            public void onLost(Postcard postcard) {
                UiUtils.showToast("找不到了");
            }

            @Override
            public void onArrival(Postcard postcard) {
                UiUtils.showToast("跳转完了");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                UiUtils.showToast("被拦截了");
            }
        });
    }

    @Subscriber(tag = EvenBusTag.GOTO_EVENT_BUS)
    public void onEvent(String s) {
        UiUtils.showToast(s);
    }

}