package com.husy.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author husy
 * @date 2019/8/29
 */
public abstract class CallListener<T> implements Observer<T> {

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
