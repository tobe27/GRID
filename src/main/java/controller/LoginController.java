package controller;

import model.GridUser;
import model.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.GridUserService;
import util.JwtUtil;
import util.Md5Util;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    GridUserService userService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResponseData login() {
        return new ResponseData().success();
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public ResponseData unauthorized() {
        return new ResponseData().unauthorized();
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseData login(GridUser user) {
        if (user.getAccountName() == null || user.getPassword() == null ||
                user.getAccountName().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseData().code(400).message("登录名或密码不能为空");
        }
        try {
            // 查询用户是否存在
            GridUser gridUser = userService.getUsersByUniqueIndex(user);
            if (gridUser == null) {
                return new ResponseData().code(400).message("用户不存在");
            }
            // 用户密码是否为空
            String saltPassword = gridUser.getPassword();
            if (saltPassword == null || saltPassword.isEmpty()) {
                return new ResponseData().code(400).message("该用户密码不存在");
            }

            //验证密码
            if (!Md5Util.verify(saltPassword, user.getPassword())) {
                return new ResponseData().code(400).message("用户名或密码错误");
            }
            //生成JWT
            Map<String, Object> payload = new HashMap<>();
            payload.put("accountId", gridUser.getAccountId());
            String jwt = JwtUtil.createToken(payload);
            logger.info("登录生成JWT："+jwt);

            //Shiro进行认证授权
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(jwt, jwt);
            subject.login(usernamePasswordToken);
            return new ResponseData().success().data(jwt);
        } catch (AuthenticationException authEx) {
            return new ResponseData().code(400).message("shiro认证授权失败");
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

}
