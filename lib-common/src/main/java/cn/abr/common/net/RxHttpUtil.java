package cn.abr.common.net;


import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragment;

import org.reactivestreams.Publisher;

import cn.abr.common.base.BaseEntity;
import cn.abr.common.net.exception.ApiException;
import cn.abr.inabr.base.BaseView;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Rx网络统一处理封装类
 */
public class RxHttpUtil {
    private static final int HTTP_REQUEST_SUCCESS_CODE = 0;


    public static <T> LifecycleTransformer<T> bindToLifecycle(BaseView view) {
        if (view instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) view).bindToLifecycle();
        } else if (view instanceof RxFragment) {
            return ((RxFragment) view).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }
    }

    public static <T> LifecycleTransformer<T> bindActivityUntilEvent(BaseView view, ActivityEvent event) {
        if (view instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) view).bindUntilEvent(event);
        } else {
            throw new IllegalArgumentException("view isn't activity");
        }
    }

    public static <T> LifecycleTransformer<T> bindFragmentUntilEvent(BaseView view, FragmentEvent event) {
        if (view instanceof RxFragment) {
            return ((RxFragment) view).bindUntilEvent(event);
        } else {
            throw new IllegalArgumentException("view isn't fragment");
        }
    }


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
                        .unsubscribeOn(Schedulers.io())
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
                        if (PHHttpResponse.getCode()==HTTP_REQUEST_SUCCESS_CODE) {
                            return createData(PHHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException(new Exception(PHHttpResponse.getInfo()), PHHttpResponse.getCode()));
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
    private static <T> Flowable<T> createData(final T t) {
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
