package video.cn.base.service;

import com.alibaba.android.arouter.launcher.ARouter;

import video.cn.base.provider.IChatModuleService;

/**
 *
 * 服务的发现
 * @author husyin
 * @date 2019年3月10日
 *
 */

public class ModuleRouteService {

    public static String getUserName(String userId) {
        IChatModuleService chatModuleService = ARouter.getInstance().navigation(IChatModuleService.class);
        if (chatModuleService != null) {
            return chatModuleService.getUserName(userId);
        }
        return "";
    }

}
