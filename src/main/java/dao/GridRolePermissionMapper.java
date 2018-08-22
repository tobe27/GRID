package dao;

import model.GridRolePermission;

public interface GridRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GridRolePermission record);

    int insertSelective(GridRolePermission record);

    GridRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GridRolePermission record);

    int updateByPrimaryKey(GridRolePermission record);
}