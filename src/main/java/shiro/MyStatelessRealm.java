package shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import service.GridUserService;
import util.JwtUtil;
import java.util.Set;

public class MyStatelessRealm extends AuthorizingRealm {
    @Autowired
    GridUserService userService;

    private Logger logger = LoggerFactory.getLogger(MyStatelessRealm.class);

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
    /**
     * Realm授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("授权开始");

        String jwt = (String) principals.getPrimaryPrincipal();
        if (JwtUtil.verifyToken(jwt)) {
            long accountId = (long) JwtUtil.parseToken(jwt).get("accountId");
            try {
                SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
                Set<String> roles = userService.getStringRolesByUserPrimaryKey(accountId);
                Set<String> perms = userService.getStringPermissionsByUserPrimaryKey(accountId);
                simpleAuthorizationInfo.addRoles(roles);
                simpleAuthorizationInfo.addStringPermissions(perms);
                logger.info("授权成功");
                return simpleAuthorizationInfo;
            } catch (Exception e) {
                return null;
            }
        }
        return null;

    }

    /**
     * Realm登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("登录认证");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String jwt = (String) usernamePasswordToken.getPrincipal();
        return new SimpleAuthenticationInfo(jwt, jwt ,getName());
    }
}
