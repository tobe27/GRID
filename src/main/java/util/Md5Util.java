package util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {
    private static final String SALT = "www.yzsbank.com";

    /**
     * 生成加盐MD5
     * @param value 初始值
     * @return 加盐后的MD5
     */
    public static String createSaltMD5(String value) {
        return new Md5Hash(value,SALT).toString();
    }

    /**
     * 校验初始值是否和加盐MD5相等
     * @param saltMD5 加盐MD5
     * @param value 初始值
     * @return boolean
     */
    public static boolean verify(String saltMD5, String value) {
        return createSaltMD5(value).equals(saltMD5);
    }

}
