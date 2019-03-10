package video.cn.app.utils;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

import video.cn.base.utils.RouteUtils;

/**
 *
 * @author husyin
 * @date 2019年3月10日
 *
 */

public class FragmentUtils {

    public static Fragment getHomeFragment() {
        return (Fragment) ARouter.getInstance().build(RouteUtils.Home_Fragment_Main).navigation();
    }

    public static Fragment getChatFragment() {
        return (Fragment) ARouter.getInstance().build(RouteUtils.Chat_Fragment_Main).navigation();
    }

    public static Fragment getRecomFragment() {
        return (Fragment) ARouter.getInstance().build(RouteUtils.Recom_Fragment_Main).navigation();
    }

    public static Fragment getMeFragment() {
        return (Fragment) ARouter.getInstance().build(RouteUtils.Me_Fragment_Main).navigation();
    }
}
