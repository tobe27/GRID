package util;


public class ValidUtil {

    //正则表达式.手机格式，必须1开头的11位电话号码
    private static final String PHONE_NUMBER = "^1[3|4|5|6|7|8|9][0-9]{9}$";
    //正则表达式.邮箱格式，格式必须为example@example.com
    private static final String EMAIL = "^[a-zA-Z0-9_]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    //正则表达式.登录名格式，英文、数字、下划线，且英文开头
    private static final String LOGIN_NAME = "^[a-zA-Z][a-zA-Z0-9_]*$";
    //正则表达式.纯阿拉伯数字
    private static final String  ARABIC_NUMERALS = "^[0-9]*$";

    /**
     * 校验为空
     * @param param
     * @return
     */
    public static boolean isEmpty(Object param) {
        return param == null || "".equals(param);
    }

    /**
     * 校验非空
     * @param param
     * @return
     */
    public static boolean notEmpty(Object param) {
        return !isEmpty(param);
    }

    /**
     * 校验限定长度
     * @param param
     * @param len
     * @return
     */
    public static boolean length(Object param, int len) {
        return length(param, len, len);
    }

    /**
     * 校验长度区间，在minLen和maxLen之间
     * @param param
     * @param minLen
     * @param maxLen
     * @return
     */
    public static boolean length(Object param, int minLen, int maxLen) {
        return notEmpty(param) && String.valueOf(param).length() >= minLen
                && String.valueOf(param).length() <=maxLen;
    }

    /**
     *校验登录名
     * @param loginName
     * @param len
     * @return
     */
    public static boolean loginName(String loginName, int len) {
        return length(loginName, 1, len) && loginName.matches(LOGIN_NAME);
    }

    /**
     * 校验手机号码
     * @param phone
     * @return
     */
    public static boolean phone(Object phone) {
        return notEmpty(phone) && String.valueOf(phone).matches(PHONE_NUMBER);
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    public static boolean email(String email) {
        return notEmpty(email) && email.matches(EMAIL);
    }

    /**
     * 校验纯数字
     * @param number
     * @return
     */
    public static boolean number(Object number) {
        return notEmpty(number) && String.valueOf(number).matches(ARABIC_NUMERALS);
    }



}
