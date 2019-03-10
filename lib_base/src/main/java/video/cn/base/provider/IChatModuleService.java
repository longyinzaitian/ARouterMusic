package video.cn.base.provider;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by Administrator on 2017/12/5 0005.
 * 如果是共有的module_base里的方法，不同模块都可以调用。
 * 但如果属于两个模块的独有方法，其他模块是不能调用的，
 * 此时使用ARouter的IProvider来实现
 *
 * @author husyin
 */

public interface IChatModuleService extends IProvider {
    /**
     * 获取模块名
     * @param userId string
     *
     * @return string
     */
    String getUserName(String userId);
}
