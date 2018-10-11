package controller;

import model.GridUser;
import model.ResponseData;
import org.apache.shiro.SecurityUtils;
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
import util.ValidUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    GridUserService userService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResponseData login() {
        return new ResponseData().redirect();
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public ResponseData unauthorized() {
        return new ResponseData().unauthorized();
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseData login(GridUser user) throws Exception {
        if (ValidUtil.isEmpty(user.getAccountName()) || ValidUtil.isEmpty(user.getPassword())) {
            return new ResponseData().fail("登录名或密码不能为空");
        }

        // 查询用户是否存在
        GridUser gridUser = userService.getUsersByUniqueIndex(user);
        if (gridUser == null) {
            return new ResponseData().fail("用户不存在");
        }
        //验证密码
        if (!Md5Util.verify(gridUser.getPassword(), user.getPassword())) {
            return new ResponseData().fail("用户名或密码错误");
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

        GridUser data = userService.getUserByPrimaryKey(gridUser.getAccountId());
        return new ResponseData().success().result("token",jwt).data(data);
    }

}
