package cn.abr.common.net.exception;

public class ApiException extends Exception {

    private String code;
    private String message;

    public ApiException(Throwable t, String code) {
        super(t);
        this.message = t.getMessage();
        this.code = code;
    }

    public ApiException(Throwable t, String code, String message) {
        super(t);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
