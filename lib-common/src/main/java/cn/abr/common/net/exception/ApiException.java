package cn.abr.common.net.exception;

public class ApiException extends Exception {

    private int code;
    private String message;

    public ApiException(Throwable t, int code) {
        super(t);
        this.message = t.getMessage();
        this.code = code;
    }

    public ApiException(Throwable t, int code, String message) {
        super(t);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
