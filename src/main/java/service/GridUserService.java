package service;

import model.GridRole;
import model.GridUser;

import java.util.List;
import java.util.Set;

public interface GridUserService {
    //获取roles
    Set<String> getStringRolesByUserPrimaryKey(Long accountId) throws Exception;
    //获取perms
    Set<String> getStringPermissionsByUserPrimaryKey(Long accountId) throws Exception;
    //删除账户-暂不调用
    boolean deleteByPrimaryKey(Long accountId) throws Exception;
    //新增账户
    boolean insertSelective(GridUser record) throws Exception;
    //查看账户详情
    GridUser getUserByPrimaryKey(Long accountId) throws Exception;
    //查询唯一索引字段是否存在
    boolean getUsersByUniqueIndex(GridUser record) throws Exception;

    //查看用户列表with roles
    List<GridUser> getUsersByAccountNameOrRealNameOrOrgName(GridUser record) throws Exception;

    //by roleId查询用户列表with role
    List<GridUser> getUsersByRole(GridRole role) throws Exception;

    //编辑账户
    boolean updateByPrimaryKeySelective(GridUser record) throws Exception;
}
