package com.sohuvideo.ui_plugin;

import com.sohuvideo.ui_plugin.api.UiPluginInit;

import video.cn.base.BaseApplication;

/**
 * @author husy
 * @date 2019/9/4
 */
public class PluginApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        UiPluginInit.init(this);
    }

    @Override
    public void onTerminate() {
        UiPluginInit.close();
        super.onTerminate();
    }
}
