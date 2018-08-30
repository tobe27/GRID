package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.GridRole;
import model.GridUser;
import model.GridUserRole;
import model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.GridUserRoleService;
import service.GridUserService;
import util.Md5Util;
import java.util.List;

@RestController
@RequestMapping("super")
public class GridUserController {
    @Autowired
    GridUserService userService;
    @Autowired
    GridUserRoleService userRoleService;

    private Logger logger = LoggerFactory.getLogger(GridUserController.class);

    /**
     * 调用此接口进行新增用户
     * @param gridUser 用户实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account",method = RequestMethod.POST)
    public ResponseData insertGridUser(GridUser gridUser,@RequestParam("roleId[]") List<Long> roleIds) {

        if (gridUser.getAccountName() == null || gridUser.getAccountName().isEmpty()) {
            return new ResponseData().code(400).message("登录名不能为空");
        }
        if (gridUser.getPassword() == null || gridUser.getPassword().isEmpty()){
            return new ResponseData().code(400).message("密码不能为空");
        }
        if (roleIds == null || roleIds.isEmpty()) {
            return new ResponseData().code(400).message("角色不能为空");
        }
        //密码加密
        String password = Md5Util.createSaltMD5(gridUser.getPassword());
        logger.info("saltPassword:"+password);
        gridUser.setPassword(password);

        try {
            //查询数据库校验用户名是否存在
            if (userService.getUsersByUniqueIndex(gridUser) != null){
                return new ResponseData().code(400).message("用户名或者号码或者身份证号码已被注册");
            }

            //新增用户
            userService.insertSelective(gridUser);

            //新增用户角色关联表
            GridUserRole userRole = new GridUserRole();
            userRole.setAccountId(gridUser.getAccountId());
            for (Long roleId : roleIds) {
                userRole.setRoleId(roleId);
                userRoleService.insert(userRole);
            }
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
        if (accountId == null) {
            return new ResponseData().code(400).message("用户ID不能为空");
        }
        if (oldPassword == null || newPassword==null || oldPassword.isEmpty() || newPassword.isEmpty()) {
            return new ResponseData().code(400).message("用户密码不能为空");
        }
        //数据库查询用户密码
        GridUser user;
        try {
            user = userService.getUserByPrimaryKey(accountId);
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
        if (user == null) {
            return new ResponseData().code(400).message("用户不存在");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseData().code(400).message("该用户密码为空");
        }

        //校验密码
        if (!Md5Util.verify(user.getPassword(),oldPassword)) {
            return new ResponseData().code(400).message("旧密码不正确");
        }

        //更新用户
        GridUser gridUser = new GridUser();
        gridUser.setAccountId(accountId);
        gridUser.setPassword(Md5Util.createSaltMD5(newPassword));
        try {
            userService.updateByPrimaryKeySelective(gridUser);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

    /**
     * 调用此接口进行修改用户信息
     * @param gridUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.PUT)
    public ResponseData updateAccount(@PathVariable Long accountId,GridUser gridUser, @RequestParam("roleId[]") List<Long> roleIds) {
        if (accountId == null) {
            return new ResponseData().code(400).message("用户ID不能为空");
        }
        if (gridUser.getAccountName() == null || gridUser.getAccountName().isEmpty()) {
            return new ResponseData().code(400).message("登录名不能为空");
        }
        if (roleIds == null || roleIds.isEmpty()) {
            return new ResponseData().code(400).message("角色不能为空");
        }

        gridUser.setAccountId(accountId);
        try {
            //编辑用户
            userService.updateByPrimaryKeySelective(gridUser);
            //删除用户角色关联表的数据
            userRoleService.deleteByUser(accountId);
            //新增用户角色关联表
            GridUserRole userRole = new GridUserRole();
            userRole.setAccountId(accountId);
            for (Long roleId : roleIds) {
                userRole.setRoleId(roleId);
                userRoleService.insert(userRole);
            }
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
        return new ResponseData().success();
    }

    /**
     * 调用此接口可条件查询全部用户with role
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/account/list", method = RequestMethod.GET)
    public ResponseData getGridUser(GridUser user, Integer pageNum, Integer pageSize){
        if (pageNum == null || pageSize == null){
            return new ResponseData().code(400).message("页码和页大小不能为空");
        }
        List<GridUser> users;
        try {
            PageHelper.startPage(pageNum, pageSize);
            users = userService.getUsersByAccountNameOrRealNameOrOrgName(user);
            PageInfo<GridUser> pageInfo = new PageInfo<>(users);
            return new ResponseData().success().result("count",pageInfo.getTotal()).data(pageInfo.getList());
        }catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

    /**
     * 调用此接口通过角色Name查询全部用户with role
     * @param roleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/account/list/{roleId}", method = RequestMethod.GET)
    public ResponseData getGridUser(@PathVariable Long roleId, Integer pageNum, Integer pageSize){
        if (roleId == null ) {
            return new ResponseData().code(400).message("角色ID不能为空");
        }
        if (pageNum == null || pageSize == null){
            return new ResponseData().code(400).message("页码和页大小不能为空");
        }
        List<GridUser> users;
        GridRole role = new GridRole();
        role.setRoleId(roleId);
        try {
            PageHelper.startPage(pageNum, pageSize);
            users = userService.getUsersByRole(role);
            PageInfo<GridUser> pageInfo = new PageInfo<>(users);
            return new ResponseData().success().result("count",pageInfo.getTotal()).data(pageInfo.getList());
        }catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

    /**
     * 调用此接口获取用户详情
     * @param accountId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.GET)
    public ResponseData getGridUser(@PathVariable("accountId") Long accountId){
        if (accountId == null) {
            return new ResponseData().code(400).message("用户ID不能为空");
        }
        GridUser gridUser;
        try {
            gridUser = userService.getUserByPrimaryKey(accountId);
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
        if (gridUser == null) {
            return new ResponseData().code(400).message("用户不存在");
        }
        return new ResponseData().success().data(gridUser);
    }

}
