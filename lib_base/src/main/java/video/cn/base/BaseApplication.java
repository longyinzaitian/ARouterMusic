package video.cn.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import video.cn.base.utils.ActLifeListener;
import video.cn.base.utils.UiUtils;

/**
 * @author husyin
 * @date 2019年3月10日
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityLifecycleCallbacks(ActLifeListener.getInstance());
        initRouter(this);
//        initLeakCanary();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    private void initRouter(BaseApplication mApplication) {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (UiUtils.isApkInDebug(instance)) {
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
            //线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(mApplication);
    }

    private void initLeakCanary() {
        mRefWatcher = LeakCanary.install(this);
    }

    public void watchObj(Object obj) {
        if (mRefWatcher != null) {
            mRefWatcher.watch(obj);
        }
    }
}
