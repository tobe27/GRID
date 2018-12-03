package util;

/**
 * 校验参数工具
 * @author Created by L.C.Y on 2018-9-28
 */
public class ValidUtil {
    /*
     * 正则表达式
     * 1. ^ 代表开始
     * 2. $ 代表结束
     * 3. ? 代表一位，等价于{0，1}
     * 4. * 代表多位，等价于{0，}
     * 5. + 代表匹配前面的至少一次
     * 6. []{m,n} 代表长度是m-n位
     * 7. []{m} 代表长度是m位
     */
    //正则表达式.手机格式，必须1开头的11位电话号码
    private static final String PHONE_NUMBER = "^1[3|4|5|6|7|8|9][0-9]{9}$";
    //正则表达式.邮箱格式，格式必须为example@example.com
    private static final String EMAIL = "^[a-zA-Z0-9_]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    //正则表达式.登录名格式，英文、数字、下划线，且英文开头
    private static final String LOGIN_NAME = "^[a-zA-Z][a-zA-Z0-9_]*$";
    // 英文、数字、下划线
    private static final String USER_NAME = "^[a-zA-Z0-9_]*$";
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
    public static boolean isNotEmpty(Object param) {
        return !isEmpty(param);
    }

    /**
     * 校验限定长度
     * @param param
     * @param len
     * @return
     */
    public static boolean isLength(Object param, int len) {
        return isLength(param, len, len);
    }

    /**
     * 校验限定长度
     * @param param
     * @param len
     * @return
     */
    public static boolean isNotLength(Object param, int len) {
        return !isLength(param, len, len);
    }


    /**
     * 校验长度区间，在minLen和maxLen之间
     * @param param
     * @param minLen
     * @param maxLen
     * @return
     */
    public static boolean isLength(Object param, int minLen, int maxLen) {
        return isNotEmpty(param) && String.valueOf(param).length() >= minLen
                && String.valueOf(param).length() <=maxLen;
    }

    /**
     * 校验长度区间，在minLen和maxLen之间
     * @param param
     * @param minLen
     * @param maxLen
     * @return
     */
    public static boolean isNotLength(Object param, int minLen, int maxLen) {
        return !isLength(param, minLen ,maxLen);
    }

    /**
     * 用户名校验
     * @param userName
     * @param len
     * @return
     */
    public static boolean isUserName(String userName, int len) {
        return isLength(userName, 1, len) && userName.matches(USER_NAME);
    }

    /**
     * 用户名校验
     * @param userName
     * @param len
     * @return
     */
    public static boolean isNotUserName(String userName, int len) {
        return !isUserName(userName, len);
    }

    /**
     *校验登录名
     * @param loginName
     * @param len
     * @return
     */
    public static boolean isLoginName(String loginName, int len) {
        return isLength(loginName, 1, len) && loginName.matches(LOGIN_NAME);
    }

    /**
     *校验登录名
     * @param loginName
     * @param len
     * @return
     */
    public static boolean isNotLoginName(String loginName, int len) {
        return !isLoginName(loginName, len);
    }

    /**
     * 校验手机号码
     * @param phone
     * @return
     */
    public static boolean isPhone(Object phone) {
        return isNotEmpty(phone) && String.valueOf(phone).matches(PHONE_NUMBER);
    }

    /**
     * 校验手机号码
     * @param phone
     * @return
     */
    public static boolean isNotPhone(Object phone) {
        return !isPhone(phone);
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return isNotEmpty(email) && email.matches(EMAIL);
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    public static boolean isNotEmail(String email) {
        return !isEmail(email);
    }

    /**
     * 校验纯数字
     * @param number
     * @return
     */
    public static boolean isNumber(Object number) {
        return isNotEmpty(number) && String.valueOf(number).matches(ARABIC_NUMERALS);
    }

    /**
     *  校验数字长度
     * @param number
     * @param minLen
     * @param maxLen
     * @return
     */
    public static boolean isNumber(Object number, int minLen, int maxLen) {
        return isLength(number, minLen, maxLen) && String.valueOf(number).matches(ARABIC_NUMERALS);
    }

    /**
     * 校验纯数字
     * @param number
     * @return
     */
    public static boolean isNotNumber(Object number) {
        return !isNumber(number);
    }

    /**
     *  校验数字长度
     * @param number
     * @param minLen
     * @param maxLen
     * @return
     */
    public static boolean isNotNumber(Object number, int minLen, int maxLen) {
        return !isNumber(number, minLen, maxLen);
    }



}
