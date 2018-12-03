package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * 用来生成token令牌和解码验证token令牌
 * JWT由三部分组成，头部header、载荷payload与签名signature
 * @author Created by L.C.Y on 2018-9-28
 */
public class JwtUtil {
    private static final String SIGNING_KEY ="www.yzsbank.com" ; //自定义加密密钥SIGNING_KEY
    private static byte[] signingSecretBytes = DatatypeConverter.parseBase64Binary(SIGNING_KEY); //转换成Base64编码
    private static final long EXP = 60*60*1000; //有效期为60分钟
    private static final long EXP_WEEK = 7*24*60*60*1000; // 有效期为一周
    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private JwtUtil(){}

    /**
     * 生成token，默认60分钟
     * @param payload 载荷
     * @return token
     */
    public static String createToken(Map<String, Object> payload) { //默认有效期为60分钟
        return createToken(payload,EXP_WEEK);
    }

    /**
     * 生成token
     * @param payload 载荷
     * @param exp 有效时长
     * @return token
     */
    public static String createToken(Map<String, Object> payload, long exp){
        //签名算法使用SHA256算法加密
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //加密JWT
        Key signingKey = new SecretKeySpec(signingSecretBytes,signatureAlgorithm.getJcaName());
        //设置JWT声明格式，生成JWT
        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeaderParam("typ","jwt")
                .setHeaderParam("alg","HS256")
                .setClaims(payload)
                .setExpiration(new Date(System.currentTimeMillis()+exp))  //token有效期
                .signWith(signatureAlgorithm,signingKey); //签名算法及签名密钥，将header与payload加密拼接后形成JWT
        return jwtBuilder.compact(); //返回JWT
    }

    /**
     * 解析token信息
     * @param token JWT信息
     * @return payload
     */
    public static Claims parseToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(signingSecretBytes)
                    .parseClaimsJws(token).getBody();
        }catch (Exception e) {
            logger.info("JWT解析token异常："+e.getMessage());
            return null;
        }
    }

    /**
     * 鉴定token信息
     * @param token JWT信息
     * @return payload
     */
    public static boolean verifyToken(String token){
        try {
             Jwts.parser()
                    .setSigningKey(signingSecretBytes)
                    .parseClaimsJws(token).getBody();
             return true;
        }catch (Exception e) {
            logger.info("JWT鉴定token异常："+e.getMessage());
            return false;
        }
    }

}
