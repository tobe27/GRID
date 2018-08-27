package dao;

import model.GridRolePermission;

public interface GridRolePermissionMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByRoleId(Long id);

    int insert(GridRolePermission record);

    int insertSelective(GridRolePermission record);

    GridRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridRolePermission record);

    int updateByPrimaryKey(GridRolePermission record);
}