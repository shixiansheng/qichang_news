package cn.abr.common.net.exception;


import android.net.ParseException;
import android.util.MalformedJsonException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import cn.abr.common.net.callback.ErrorHandler;
import io.reactivex.exceptions.CompositeException;
import io.rx_cache2.RxCacheException;
import retrofit2.HttpException;


public class ExceptionHandle {

//    private static final int UNAUTHORIZED = 401;
//    private static final int FORBIDDEN = 403;
//    private static final int NOT_FOUND = 404;
//    private static final int REQUEST_TIMEOUT = 408;
//    private static final int INTERNAL_SERVER_ERROR = 500;
//    private static final int BAD_GATEWAY = 502;
//    private static final int SERVICE_UNAVAILABLE = 503;
//    private static final int GATEWAY_TIMEOUT = 504;

    public static  void handleExceptions(Throwable e, ErrorHandler errorHandler) {
        if (e instanceof CompositeException) {
            CompositeException compositeE = (CompositeException) e;
            for (Throwable throwable : compositeE.getExceptions()) {
                if (throwable instanceof RxCacheException) {
                    //缓存异常暂时不做处理
                    //httpSubscriber.onFailure(new ApiException(e, ERROR.CACHE_ERROR, "缓存出错"));
                    continue;
                }
                System.out.println(e instanceof ApiException);
                ApiException apiException = handleException(throwable);
                errorHandler.onError(apiException);
            }
        } else {
            ApiException apiException = handleException(e);
            errorHandler.onError(apiException);
        }
    }

    private static ApiException handleException(Throwable e) {
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            return new ApiException(e, ERROR.HTTP_ERROR, "网络错误:" + httpException.code());
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            return new ApiException(resultException, resultException.code, resultException.message);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            return new ApiException(e, ERROR.PARSE_ERROR, "解析错误");
        } else if (e instanceof ConnectException) {
            return new ApiException(e, ERROR.NETWORK_ERROR, "连接失败");
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            return new ApiException(e, ERROR.SSL_ERROR, "证书验证失败");
        } else if (e instanceof ConnectTimeoutException) {
            return new ApiException(e, ERROR.TIMEOUT_ERROR, "连接超时");
        } else if (e instanceof java.net.SocketTimeoutException) {
            return new ApiException(e, ERROR.TIMEOUT_ERROR, "连接超时");
        } else if (e instanceof ApiException) {
            return (ApiException) e;
        } else if (e instanceof UnknownHostException) {
            return new ApiException(e, ERROR.UNKNOWN_HOST_ERROR, "未知域名");
        } else if (e instanceof MalformedJsonException) {
            return new ApiException(e, ERROR.MALFORMEDJSON_ERROR, "json格式错误");
        } else {
            return new ApiException(e, ERROR.UNKNOWN, "未知错误");
        }
    }

    public interface ERROR {

        int ERROR = 301;

        //无data返回
        int NO_DATA = 302;

        //未知错误
        int UNKNOWN = 1000;
        /*解析错误*/
        int PARSE_ERROR = 1001;
        //网络错误
        int NETWORK_ERROR = 1002;
        //协议出错
        int HTTP_ERROR = 1003;
        //证书出错
        int SSL_ERROR = 1004;
        //连接超时
        int TIMEOUT_ERROR = 1005;
        //未知域名
        int UNKNOWN_HOST_ERROR = 1006;

        //json格式错误
        int MALFORMEDJSON_ERROR = 1007;
        //缓存错误
        int CACHE_ERROR = 1008;

    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }
}


