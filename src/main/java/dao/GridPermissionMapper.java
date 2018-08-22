package dao;

import model.GridPermission;

public interface GridPermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(GridPermission record);

    int insertSelective(GridPermission record);

    GridPermission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(GridPermission record);

    int updateByPrimaryKey(GridPermission record);
}