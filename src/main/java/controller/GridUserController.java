package controller;

import model.GridUser;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.GridUserService;
import sun.security.util.Password;
import util.Md5Util;

@RestController
@RequestMapping("super")
public class GridUserController {
    @Autowired
    GridUserService gridUserService;

    /**
     * 调用此接口进行新增用户
     * @param gridUser 用户实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account",method = RequestMethod.POST)
    public ResponseData insertGridUser(GridUser gridUser) throws Exception {
        String password = Md5Util.createSaltMD5(gridUser.getPassword());
        gridUser.setPassword(password);
        if (gridUserService.insertSelective(gridUser)) {
            return new ResponseData().success();
        }else {
            return new ResponseData().fail();
        }
    }

    /**
     * 调用此接口进行修改用户状态
     * @param accountId
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}/status/{status}", method = RequestMethod.PUT)
    public ResponseData updateStatus(@PathVariable("accountId") Long accountId, @PathVariable("status") Integer status) throws Exception {
        GridUser gridUser = new GridUser();
        gridUser.setAccountId(accountId);
        gridUser.setStatus(status);
        if (gridUserService.updateByPrimaryKeySelective(gridUser)) {
            return new ResponseData().success();
        }else {
            return new ResponseData().fail();
        }
    }

    @RequestMapping(value = "/account/{accountId}/password", method = RequestMethod.PUT)
    public ResponseData updatePassword(@PathVariable Long accountId, String oldPassword, String newPassword) throws Exception {
        GridUser user = gridUserService.selectByPrimaryKey(accountId);
        if (user == null) {
            return new ResponseData().code(400).message("用户不存在");
        }
        if (!Md5Util.createSaltMD5(oldPassword).equals(user.getPassword())) {
            return new ResponseData().code(400).message("旧密码不正确");
        }
        GridUser gridUser = new GridUser();
        gridUser.setAccountId(accountId);
        gridUser.setPassword(newPassword);


        return new ResponseData().success();
    }

    /**
     * 调用此接口进行修改用户信息
     * @param gridUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.PUT)
    public ResponseData updateAccount(@PathVariable("accountId") GridUser gridUser) throws Exception {
        if (gridUserService.updateByPrimaryKeySelective(gridUser)) {
            return new ResponseData().success();
        }else {
            return new ResponseData().fail();
        }
    }

    /**
     * 调用此接口获取用户详情
     * @param accountId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.GET)
    public ResponseData getGridUser(@PathVariable("accountId") Long accountId) throws Exception {
        GridUser gridUser = gridUserService.selectByPrimaryKey(accountId);
        if (gridUser == null) {
            return new ResponseData().code(400).message("用户不存在");
        }
        return new ResponseData().success().data(gridUser);
    }


}
