package model;

import util.HttpStatusCodeEnum;
import java.util.LinkedHashMap;

/**
 * 用来返回controller层请求的结果
 * 默认为三个返回项：code,message,data
 * 如果有其他更多返回项，使用result方法拼接
 * @author Created by L.C.Y on 2018-9-20
 */
public class ResponseData extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = -364546270975223015L;

    /**
     * 自由配置项
     * @param key
     * @param value
     * @return
     */
    public ResponseData result(String key, Object value) {
        this.put(key, value);
        return this;
    }

    /**
     * 请求成功
     * @return
     */
    public ResponseData success(){
        return this.success(HttpStatusCodeEnum.OK);
    }
    public ResponseData success(Object message){
        this.put("code", HttpStatusCodeEnum.OK.getCode());
        this.put("message", message);
        return this;
    }

    /**
     * 请求数据为空
     * @return
     */
    public ResponseData blank() {
        return this.blank(HttpStatusCodeEnum.BLANK);
    }
    public ResponseData blank(Object message) {
        this.put("code", HttpStatusCodeEnum.BLANK.getCode());
        this.put("message", message);
        return this;
    }

    /**
     * 请求失败
     * @return
     */
    public ResponseData fail(){
        return this.fail(HttpStatusCodeEnum.FAIL);
    }
    public ResponseData fail(Object message) {
        this.put("code",HttpStatusCodeEnum.FAIL.getCode());
        this.put("message", message);
        return this;
    }

    /**
     * 请求重定向
     * @return
     */
    public ResponseData redirect() {
        return this.redirect(HttpStatusCodeEnum.REDIRECT);
    }
    public ResponseData redirect(Object message) {
        this.put("code",HttpStatusCodeEnum.REDIRECT.getCode());
        this.put("message", message);
        return this;
    }

    /**
     * 请求无权限
     * @return
     */
    public ResponseData unauthorized() {
        return this.unauthorized(HttpStatusCodeEnum.UNAUTHORIZED);
    }
    public ResponseData unauthorized(Object message){
        this.put("code",HttpStatusCodeEnum.UNAUTHORIZED.getCode());
        this.put("message", message);
        return this;
    }

    /**
     * 请求被禁止
     * @return
     */
    public ResponseData forbidden() {
        return this.forbidden(HttpStatusCodeEnum.FORBIDDEN);
    }
    public ResponseData forbidden(Object message){
        this.put("code",HttpStatusCodeEnum.FORBIDDEN.getCode());
        this.put("message", message);
        return this;
    }

    public ResponseData code(int code) {
        return result("code",code);
    }

    public ResponseData message(String message) {
        return result("message", message);
    }

    public ResponseData data(Object data) {
        return result("data", data);
    }
}
