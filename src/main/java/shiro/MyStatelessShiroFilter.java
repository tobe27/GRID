package shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import service.GridUserService;
import util.JwtUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 自定义Shiro过滤器，所有请求的请求头需携带token
 * @author Created by L.C.Y on 2018-8-28
 */
public class MyStatelessShiroFilter extends AccessControlFilter {
    @Autowired
    GridUserService userService;
    private Logger logger = LoggerFactory.getLogger(MyStatelessShiroFilter.class);
    /**
     *返回false
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return 返回结果是false的时候才会执行下面的onAccessDenied方法
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /**
     * 从请求头获取token并验证，验证通过后交给realm进行登录
     * @param servletRequest
     * @param servletResponse
     * @return 返回结果为true时，表明登录认证通过，执行controller层
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        String uri = request.getRequestURI(); // 返回请求URL
        String method = request.getMethod();    // 返回请求方法
        String requestUrl = method+":"+uri;
        logger.info("请求方法拼接："+requestUrl);

        // token判空
        if (jwt == null) {
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.getWriter().print("{\"code\":300,\"message\":\"用户未登录，请重新登录！\"}");
            return false;
        }

        // 权限判断
        if (JwtUtil.verifyToken(jwt)) {

            // 获取用户ID和权限
            String id = String.valueOf(JwtUtil.parseToken(jwt).get("accountId"));
            Set<String> permissionUrl = userService.getStringPermissionsByUserPrimaryKey(Long.valueOf(id));
            for (String perm : permissionUrl) {
                String[] p = perm.split(":");

                // 判断权限是否属于该URL，不属于返回登录页
                if (p[0].equals(method) && uri.contains(p[1])) {
                	return true;
                }
            }
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.getWriter().print("{\"code\":401,\"message\":\"该用户没有操作权限！\"}");
            return false;
        }else{
        	servletResponse.setContentType("application/json;charset=UTF-8");
        	servletResponse.getWriter().print("{\"code\":300,\"message\":\"token已过期，请重新登录！\"}");
            return false;
        }
        
    }

}
