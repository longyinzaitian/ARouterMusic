package video.cn.chat.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.provider.IChatModuleService;
import video.cn.base.utils.RouteUtils;
import video.cn.chat.net.ChatService;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.SERVICE_CHAT)
public class ChatModuleServiceImpl implements IChatModuleService {

    @Override
    public String getUserName(String userId) {
        return ChatService.getUserName();
    }

    @Override
    public void init(Context context) {

    }
}
