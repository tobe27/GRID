package dao;

import model.GridUserRole;

public interface GridUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GridUserRole record);

    int insertSelective(GridUserRole record);

    GridUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GridUserRole record);

    int updateByPrimaryKey(GridUserRole record);
}