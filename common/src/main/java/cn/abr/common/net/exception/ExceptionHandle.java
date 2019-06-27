package cn.abr.common.net.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

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

    public static ApiException handleException(Throwable e) {
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
        } else {
            return new ApiException(e, ERROR.UNKNOWN, "请检查你的网络");
        }
    }

    interface ERROR {

        String ERROR = "301";

        //未知错误
        String UNKNOWN = "1000";
        /*解析错误*/
        String PARSE_ERROR = "1001";
        //网络错误
        String NETWORK_ERROR = "1002";
        //协议出错
        String HTTP_ERROR = "1003";
        //证书出错
        String SSL_ERROR = "1004";
        //连接超时
        String TIMEOUT_ERROR = "1005";

    }

    public class ServerException extends RuntimeException {
        public String code;
        public String message;
    }
}

