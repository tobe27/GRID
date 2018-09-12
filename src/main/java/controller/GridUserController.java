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
    public ResponseData insertGridUser(GridUser gridUser, String roleIds) {
        logger.info("添加用户及关联角色");
        try {
            //新增用户及角色
            if (userService.insertSelective(gridUser, roleIds)) {
                return new ResponseData().success();
            }else {
                return new ResponseData().fail();
            }
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
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
        GridUser user = new GridUser();
        user.setAccountId(accountId);
        try {
            if (userService.updatePasswordByOldPassword(user, oldPassword, newPassword)) {
                return new ResponseData().success();
            } else {
                return new ResponseData().fail();
            }
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口进行修改用户信息
     * @param gridUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.PUT)
    public ResponseData updateAccount(GridUser gridUser, String roleIds) {
        try {
            //编辑用户
            if (userService.updateByPrimaryKeySelective(gridUser, roleIds)) {
                return new ResponseData().success();
            } else {
                return new ResponseData().fail();
            }
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }

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
            return new ResponseData().fail("页码和页大小不能为空");
        }
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<GridUser> users = userService.getUsersByAccountNameOrRealNameOrOrgName(user);
            PageInfo<GridUser> pageInfo = new PageInfo<>(users);
            return new ResponseData().success().result("count",pageInfo.getTotal()).data(pageInfo.getList());
        }catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
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
            return new ResponseData().fail("角色ID不能为空");
        }
        if (pageNum == null || pageSize == null){
            return new ResponseData().fail("页码和页大小不能为空");
        }
        GridRole role = new GridRole();
        role.setRoleId(roleId);
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<GridUser> users = userService.getUsersByRole(role);
            PageInfo<GridUser> pageInfo = new PageInfo<>(users);
            return new ResponseData().success().result("count",pageInfo.getTotal()).data(pageInfo.getList());
        }catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口获取用户详情
     * @param accountId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.GET)
    public ResponseData getGridUser(@PathVariable Long accountId){
        if (accountId == null) {
            return new ResponseData().fail("用户ID不能为空");
        }
        GridUser gridUser;
        try {
            gridUser = userService.getUserByPrimaryKey(accountId);
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
        if (gridUser == null) {
            return new ResponseData().fail("用户不存在");
        }
        return new ResponseData().success().data(gridUser);
    }

}
