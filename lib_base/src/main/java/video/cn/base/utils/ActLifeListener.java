package video.cn.base.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @author husy
 * @date 2019/8/31
 */
public class ActLifeListener implements Application.ActivityLifecycleCallbacks {

    private ActLifeListener() {}

    private static class Hold {
        private static ActLifeListener instance = new ActLifeListener();
    }

    public static ActLifeListener getInstance() {
        return Hold.instance;
    }

    private Activity mTopActivity;
    private int mCount = 0;

    public Activity getTopActivity() {
        return mTopActivity;
    }

    public boolean isForground() {
        return mCount > 0;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mCount++;
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity == mTopActivity) {
            mTopActivity = null;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mTopActivity = activity;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        mCount--;
    }
}
