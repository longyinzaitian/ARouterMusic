package com.husy.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import video.cn.base.utils.UiUtils;

/**
 * @author husy
 * @date 2019/8/29
 */
public abstract class AbstractCallListener<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T o) {
        onResponse(o);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        UiUtils.showToast("信息获取失败，请稍后重试");
        onFail(e);
    }

    @Override
    public void onComplete() {

    }

    /**
     * response
     * @param response T
     */
    public abstract void onResponse(T response);

    /**
     * fail
     * @param e throwable
     */
    public abstract void onFail(Throwable e);
}
