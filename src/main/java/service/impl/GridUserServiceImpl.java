package service.impl;

import dao.GridUserMapper;
import exception.MyException;
import model.GridRole;
import model.GridUser;
import model.GridUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.GridUserRoleService;
import service.GridUserService;
import util.Md5Util;
import util.ValidUtil;
import java.util.List;
import java.util.Set;

@Service
public class GridUserServiceImpl implements GridUserService {
    @Autowired
    GridUserMapper userMapper;

    @Autowired
    GridUserRoleService userRoleService;

    /**
     * 获取roles
     *
     * @param accountId
     * @return
     * @throws Exception
     */
    @Override
    public Set<String> getStringRolesByUserPrimaryKey(Long accountId) throws Exception {
        try {
            return userMapper.getStringRolesByUserPrimaryKey(accountId);
        } catch (Exception e) {
            throw new MyException("获取角色异常");
        }
    }

    /**
     * 获取perms
     *
     * @param accountId
     * @return
     * @throws Exception
     */
    @Override
    public Set<String> getStringPermissionsByUserPrimaryKey(Long accountId) throws Exception {
        try {
            return userMapper.getStringPermissionsByUserPrimaryKey(accountId);
        } catch (Exception e) {
            throw new MyException("获取权限异常");
        }
    }

    /**
     * 删除账户-暂不调用
     *
     * @param accountId
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long accountId) throws Exception {
        try {
            return userMapper.deleteByPrimaryKey(accountId) == 1;
        } catch (Exception e) {
            throw new MyException("删除用户出现异常");
        }
    }

    /**
     * 新增账户
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean insertSelective(GridUser record, String roleIds) throws Exception {
        if (ValidUtil.isEmpty(roleIds)) {
            throw  new MyException("角色ID不能为空");
        }
        if (!ValidUtil.loginName(record.getAccountName(),20)) {
            throw  new MyException("登录名错误，是2-16位英文、数字、下划线，且英文开头");
        }
        if (!ValidUtil.length(record.getPassword(), 6, 20)) {
            throw  new MyException("密码长度是6-20位");
        }
        // 查询数据库校验用户名是否存在
        if (userMapper.getUserByUniqueIndex(record) != null){
            throw  new MyException("用户名或者号码或者身份证号码已被注册");
        }

        // 密码加密
        String password = Md5Util.createSaltMD5(record.getPassword());
        record.setPassword(password);
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            int count = userMapper.insertSelective(record);
            GridUserRole userRole = new GridUserRole();
            userRole.setAccountId(record.getAccountId());
            String[] roleId = roleIds.trim().split(",");
            for (String id : roleId) {
                userRole.setRoleId(Long.valueOf(id));
                userRoleService.insert(userRole);
            }
            return count == 1 ;

        } catch (Exception e) {
            throw new MyException("新增用户出现异常");
        }

    }

    /**
     * 查看账户详情
     *
     * @param accountId
     * @return
     * @throws Exception
     */
    @Override
    public GridUser getUserByPrimaryKey(Long accountId) throws Exception {
        try {
            return userMapper.getUserByPrimaryKey(accountId);
        } catch (Exception e) {
            throw new MyException("查询用户详情出现异常");
        }
    }

    /**
     * 查询唯一索引字段是否存在
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public GridUser getUsersByUniqueIndex(GridUser record) throws Exception {
        try {
            return userMapper.getUserByUniqueIndex(record);
        } catch (Exception e) {
            throw new MyException("通过唯一索引查询用户出现异常");
        }
    }

    /**
     * 查看用户列表with roles
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<GridUser> getUsersByAccountNameOrRealNameOrOrgName(GridUser record) throws Exception {
        try {
            return userMapper.getUsersByAccountNameOrRealNameOrOrgName(record);
        } catch (Exception e) {
        	e.printStackTrace();
            throw new MyException("查询用户列表出现异常");
        }
    }

    /**
     * by roleId查询用户列表with role
     *
     * @param role
     * @return
     * @throws Exception
     */
    @Override
    public List<GridUser> getUsersByRole(GridRole role) throws Exception {
        try {
            return userMapper.getUsersByRole(role);
        } catch (Exception e) {
            throw new MyException("通过角色查询用户列表出现异常");
        }
    }

    /**
     * 编辑账户
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean updateByPrimaryKeySelective(GridUser record, String roleIds) throws Exception {
        if (ValidUtil.isEmpty(roleIds)) {
            throw  new MyException("角色ID不能为空");
        }
        if (!ValidUtil.loginName(record.getAccountName(),20)) {
            throw  new MyException("登录名错误，是2-16位英文、数字、下划线，且英文开头");
        }
        // 查询数据库校验用户名是否存在
        GridUser user;
        if ((user = userMapper.getUserByUniqueIndex(record)) != null){
            if (!record.getAccountId().equals(user.getAccountId())) {
                throw new MyException("用户名或者号码或者身份证号码已被注册");
            }
        }

        // 密码加密
        if (ValidUtil.notEmpty(record.getPassword())) {
            if (!ValidUtil.length(record.getPassword(), 6, 20)) {
                throw new MyException("密码长度是6-20位");
            }
            String password = Md5Util.createSaltMD5(record.getPassword());
            record.setPassword(password);
        }

        record.setUpdatedAt(System.currentTimeMillis());
        try {
            int count = userMapper.updateByPrimaryKeySelective(record);
            //删除用户角色关联表的数据
            userRoleService.deleteByUser(record.getAccountId());
            //添加用户角色关联表的数据
            GridUserRole userRole = new GridUserRole();
            userRole.setAccountId(record.getAccountId());
            String[] roleId = roleIds.trim().split(",");
            for (String id : roleId) {
                userRole.setRoleId(Long.valueOf(id));
                userRoleService.insert(userRole);
            }
            return  count == 1;
        } catch (Exception e) {
            throw new MyException("修改用户出现异常");
        }
    }

    @Override
    public boolean updatePasswordByOldPassword(GridUser record, String oldPassword, String newPassword) throws Exception{
        if (ValidUtil.isEmpty(record.getAccountId()) || ValidUtil.isEmpty(oldPassword) || ValidUtil.isEmpty(newPassword)) {
            throw new MyException("请求数据不能为空");
        }
        if (!ValidUtil.length(oldPassword, 6, 20) || !ValidUtil.length(newPassword, 6, 20)) {
            throw new MyException("密码长度为6-20");
        }
        GridUser user;
        try {
            user = userMapper.getUserByPrimaryKey(record.getAccountId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("获取客户出现异常");
        }

        if (user == null) {
            throw new MyException("用户不存在");
        }

        if (!Md5Util.verify(user.getPassword(),oldPassword)) {
            throw new MyException("旧密码不正确");
        }
        record.setPassword(Md5Util.createSaltMD5(newPassword));
        try {
            return userMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("修改密码出现异常");
        }

    }
}
