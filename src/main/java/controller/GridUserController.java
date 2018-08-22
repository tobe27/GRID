package controller;

import model.GridUser;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.GridUserService;
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
    public ResponseData insertGridUser(GridUser gridUser) {
        String password = Md5Util.createSaltMD5(gridUser.getPassword());
        gridUser.setPassword(password);
        try {
            gridUserService.insertSelective(gridUser);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
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
    public ResponseData updateStatus(@PathVariable("accountId") Long accountId, @PathVariable("status") Integer status){
        GridUser gridUser = new GridUser();
        gridUser.setAccountId(accountId);
        gridUser.setStatus(status);
        try {
            gridUserService.updateByPrimaryKeySelective(gridUser);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

    /**
     * 调用此接口修改密码
     * @param accountId
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}/password", method = RequestMethod.PUT)
    public ResponseData updatePassword(@PathVariable Long accountId, String oldPassword, String newPassword){
        GridUser user;
        try {
            user = gridUserService.selectByPrimaryKey(accountId);
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
        if (user == null) {
            return new ResponseData().code(400).message("用户不存在");
        }
        if (!Md5Util.createSaltMD5(oldPassword).equals(user.getPassword())) {
            return new ResponseData().code(400).message("旧密码不正确");
        }
        GridUser gridUser = new GridUser();
        gridUser.setAccountId(accountId);
        gridUser.setPassword(Md5Util.createSaltMD5(newPassword));
        try {
            gridUserService.updateByPrimaryKeySelective(gridUser);
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
        return new ResponseData().success();
    }

    /**
     * 调用此接口进行修改用户信息
     * @param gridUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.PUT)
    public ResponseData updateAccount(@PathVariable("accountId") GridUser gridUser) {
        try {
            gridUserService.updateByPrimaryKeySelective(gridUser);
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
        return new ResponseData().success();
    }

    /**
     * 调用此接口获取用户详情
     * @param accountId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.GET)
    public ResponseData getGridUser(@PathVariable("accountId") Long accountId){
        GridUser gridUser = null;
        try {
            gridUser = gridUserService.selectByPrimaryKey(accountId);
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
        if (gridUser == null) {
            return new ResponseData().code(400).message("用户不存在");
        }
        return new ResponseData().success().data(gridUser);
    }


}
