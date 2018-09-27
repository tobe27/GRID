package service;

import model.GridRole;
import model.GridUser;

import java.util.List;
import java.util.Set;

public interface GridUserService {
    /**
     * 获取roles
     * @param accountId
     * @return
     * @throws Exception
     */
    Set<String> getStringRolesByUserPrimaryKey(Long accountId) throws Exception;

    /**
     * 获取perms
     * @param accountId
     * @return
     * @throws Exception
     */
    Set<String> getStringPermissionsByUserPrimaryKey(Long accountId) throws Exception;

    /**
     * 删除账户-暂不调用
     * @param accountId
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long accountId) throws Exception;

    /**
     * 新增账户
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(GridUser record, String roleIds) throws Exception;

    /**
     * 查看账户详情
     * @param accountId
     * @return
     * @throws Exception
     */
    GridUser getUserByPrimaryKey(Long accountId) throws Exception;

    /**
     * 查询唯一索引字段是否存在
     * @param record
     * @return
     * @throws Exception
     */
    GridUser getUsersByUniqueIndex(GridUser record) throws Exception;

    /**
     * 查看用户列表with roles
     * @param record
     * @return
     * @throws Exception
     */
    List<GridUser> getUsersByAccountNameOrRealNameOrOrgName(GridUser record) throws Exception;

    /**
     * by roleId查询用户列表with role
     * @param role
     * @return
     * @throws Exception
     */
    List<GridUser> getUsersByRole(GridRole role) throws Exception;

    /**
     * 编辑账户
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(GridUser record, String roleIds) throws Exception;

    boolean updatePasswordByOldPassword(GridUser record, String oldPassword, String newPassword) throws Exception;
}
