package service.impl;

import dao.GridUserMapper;
import exception.MyException;
import model.GridRole;
import model.GridUser;
import model.GridUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.GridUserRoleService;
import service.GridUserService;
import util.Md5Util;
import util.ValidUtil;
import java.util.List;
import java.util.Set;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class GridUserServiceImpl implements GridUserService {
    @Autowired
    GridUserMapper userMapper;
    @Autowired
    GridUserRoleService userRoleService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
            logger.info("查询角色异常："+e.getMessage());
            throw new MyException("查询角色异常");
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
            logger.info("查询权限异常："+e.getMessage());
            throw new MyException("查询权限异常");
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
            logger.info("删除用户异常："+e.getMessage());
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
            throw  new MyException("请分配角色");
        }
        if (ValidUtil.isNotUserName(record.getAccountName(),20)) {
            throw  new MyException("用户名错误，必须是2-20位英文、数字或下划线!");
        }
        if (!ValidUtil.isLength(record.getPassword(), 6, 20)) {
            throw  new MyException("请输入6-20位密码");
        }
        if (!ValidUtil.isLength(record.getRealName(), 2, 20)) {
            throw  new MyException("请输入真实姓名");
        }
        if (!ValidUtil.isLength(record.getPhoneNumber(), 11)) {
            throw new MyException("请输入11位有效手机号");
        }
        if (!ValidUtil.isLength(record.getIdNumber(), 18)) {
            throw new MyException("请输入18位有效身份证号");
        }
        if (ValidUtil.isEmpty(record.getOrgCode())) {
            throw new MyException("请选择所在机构");
        }
        // 查询数据库校验用户名是否存在
        if (userMapper.getUserByUniqueIndex(record) != null){
            throw  new MyException("用户名已存在");
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
            logger.info("新建用户异常："+e.getMessage());
            throw new MyException("新建用户出现异常!");
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
            logger.info("查询用户异常："+e.getMessage());
            throw new MyException("查询用户详情出现异常!");
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
            logger.info("通过用户名查询用户异常："+e.getMessage());
            throw new MyException("通过用户名查询用户出现异常!");
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
            logger.info("查询用户列表异常："+e.getMessage());
            throw new MyException("查询用户列表出现异常!");
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
            logger.info("角色查询用户异常："+e.getMessage());
            throw new MyException("通过角色查询用户列表出现异常!");
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
            throw  new MyException("请分配角色");
        }
        if (ValidUtil.isNotUserName(record.getAccountName(),20)) {
            throw  new MyException("用户名错误，必须是2-20位英文、数字或下划线!");
        }

        if (!ValidUtil.isLength(record.getRealName(), 2, 20)) {
            throw  new MyException("请输入真实姓名");
        }
        if (!ValidUtil.isLength(record.getPhoneNumber(), 11)) {
            throw new MyException("请输入11位有效手机号");
        }
        if (!ValidUtil.isLength(record.getIdNumber(), 18)) {
            throw new MyException("请输入18位有效身份证号");
        }

        // 机构不能编辑
        record.setOrgCode(null);
        record.setOrgName(null);
        // 密码在此不予修改
        record.setPassword(null);

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
            logger.info("编辑用户异常："+e.getMessage());
            // 查询数据库校验用户名是否存在
            if (userMapper.getUserByUniqueIndex(record) != null){
                throw  new MyException("用户名已存在");
            }
            throw new MyException("编辑用户出现异常!");
        }
    }

    /**
     * 根据旧密码修改新密码
     * @param record
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws Exception
     */
    @Override
    public boolean updatePasswordByOldPassword(GridUser record, String oldPassword, String newPassword) throws Exception{
        if (ValidUtil.isEmpty(record.getAccountId()) || ValidUtil.isEmpty(oldPassword) || ValidUtil.isEmpty(newPassword)) {
            throw new MyException("请求数据不能为空!");
        }
        if (!ValidUtil.isLength(oldPassword, 6, 20) || !ValidUtil.isLength(newPassword, 6, 20)) {
            throw new MyException("请输入6-20位密码");
        }
        GridUser user;
        try {
            user = userMapper.getUserByPrimaryKey(record.getAccountId());
        } catch (Exception e) {
            throw new MyException("获取客户出现异常!");
        }

        if (user == null) {
            throw new MyException("用户不存在!");
        }

        if (!Md5Util.verify(user.getPassword(),oldPassword)) {
            throw new MyException("旧密码不正确!");
        }
        record.setPassword(Md5Util.createSaltMD5(newPassword));
        try {
            return userMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("用户修改密码异常："+e.getMessage());
            throw new MyException("用户修改密码出现异常!");
        }

    }

    /**
     * 直接修改密码
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updatePassword(GridUser record) throws Exception {
        if (!ValidUtil.isLength(record.getPassword(), 6, 20)) {
            throw new MyException("请输入6-20位密码");
        }
        // 密码加密
        record.setPassword(Md5Util.createSaltMD5(record.getPassword()));
        logger.info("user:"+record);
        try {
            return userMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("后台修改密码异常："+e.getMessage());
            throw new MyException("后台修改密码出现异常!");
        }
    }

    /**
     * 修改用户状态
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateStatusByPrimaryKey(GridUser record) throws Exception {
        Integer status = record.getStatus();
        if (status != 0 && status != 1) {
            throw new MyException("用户状态码不正确："+status);
        }
        record.setUpdatedAt(System.currentTimeMillis());
        try {
            return userMapper.updateStatusByPrimaryKey(record) == 1;
        } catch (Exception e) {
            logger.info("修改用户状态异常："+e.getMessage());
            throw new MyException("修改用户状态出现异常!");
        }
    }
}
