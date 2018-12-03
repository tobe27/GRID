package dao;

import model.GridRole;
import model.GridUser;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Repository
public interface GridUserMapper {
    //获取roles
    Set<String> getStringRolesByUserPrimaryKey(Long accountId);
    //获取perms
    Set<String> getStringPermissionsByUserPrimaryKey(Long accountId);
    //删除用户-暂不调用
    int deleteByPrimaryKey(Long accountId);
    //新增用户
    int insertSelective(GridUser record);
    //查看用户详情 with role
    GridUser getUserByPrimaryKey(Long accountId);
    //查询唯一索引字段是否存在
    GridUser getUserByUniqueIndex(GridUser record);
    //条件查看用户列表with role
    List<GridUser> getUsersByAccountNameOrRealNameOrOrgName(GridUser record);
    //by role查询用户列表with role
    List<GridUser> getUsersByRole(GridRole role);
    //编辑用户
    int updateByPrimaryKeySelective(GridUser record);
    //编辑用户状态
    int updateStatusByPrimaryKey(GridUser record);
}