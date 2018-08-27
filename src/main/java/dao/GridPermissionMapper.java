package dao;

import java.util.List;
import java.util.Map;

import model.GridPermission;

public interface GridPermissionMapper {
    int deleteByPrimaryKey(Long permissionId);

    int insert(GridPermission record);

    int insertSelective(GridPermission record);

    GridPermission selectByPrimaryKey(Long permissionId);

    int updateByPrimaryKeySelective(GridPermission record);

    int updateByPrimaryKey(GridPermission record);
    List<GridPermission> getPermissions(Map<String,Object> map);
}