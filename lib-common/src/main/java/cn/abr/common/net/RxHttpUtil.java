package cn.abr.common.net;


import org.reactivestreams.Publisher;

import cn.abr.common.base.BaseEntity;
import cn.abr.common.net.exception.ApiException;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Rx网络统一处理封装类
 */
public class RxHttpUtil {
    private static final String HTTP_REQUEST_SUCCESS_CODE = "200";

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseEntity<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<BaseEntity<T>, T>() {
            @Override
            public Publisher<T> apply(Flowable<BaseEntity<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap((Function<BaseEntity<T>, Flowable<T>>) new Function<BaseEntity<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseEntity<T> PHHttpResponse) throws Exception {
                        if (PHHttpResponse.getCode().equals(HTTP_REQUEST_SUCCESS_CODE)) {
                            return createData(PHHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException(new Exception(PHHttpResponse.getMsg()), PHHttpResponse.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    if (t != null) {
                        emitter.onNext(t);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                    e.printStackTrace();
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
