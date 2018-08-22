package dao;

import model.GridRole;

public interface GridRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(GridRole record);

    int insertSelective(GridRole record);

    GridRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(GridRole record);

    int updateByPrimaryKey(GridRole record);
}