package video.cn.base.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

import video.cn.base.utils.RouteUtils;

/**
 * 依赖注入使用，注意：必须实现SerializationService进行注册
 *
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.HOME_JSON_SERVICE,name = "序列化JavaBean使用")
public class JsonServiceImpl implements SerializationService {
    @Override
    public void init(Context context) {

    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return JSON.toJSONString(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return JSON.parseObject(input, clazz);
    }
}
