package cn.abr.inabr.net


import cn.abr.common.net.callback.ErrorHandler

import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by WZL
 * on 2018/1/24.
 * 16:47
 * By Android Studio 3.0.1
 */
//private ErrorListener mErrorListener;

/* public HttpSubscriber(ErrorListener mErrorListener) {
        this.mErrorListener = mErrorListener;
    }*/

abstract class HttpSubscriber<T> : ResourceSubscriber<T>() {

    override fun onError(t: Throwable) {
        ErrorHandler.Instance.handleError(t)
    }


    /**
     * 如果返回结果T为null的话，不会调用这个方法，会调用onComplete方法，后续操作需要放在onComplete方法中
     */
    abstract override fun onNext(data: T)

}
