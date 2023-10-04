package controller;

public class ApiException extends Exception {

    private int statusCode;

    public ApiException(int statusCode, String msg) {
        super(msg);
        this.statusCode = statusCode;
    }

    public int getCode() {
        return statusCode;
    }
}
