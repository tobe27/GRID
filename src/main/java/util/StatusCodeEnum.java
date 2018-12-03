package util;

/**
 * HTTP状态码
 * @author Created by L.C.Y on 2018-9-28
 */
public enum StatusCodeEnum {
    OK(200), // 请求成功
    BLANK(204), // 请求成功，但返回没内容
    REDIRECT(300), // 重定向
    FAIL(400), // 请求失败
    UNAUTHORIZED(401), // 请求未授权
    FORBIDDEN(403), // 请求被禁止
    ERROR(500) // 服务器异常
    ;
    private int code;
    StatusCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
