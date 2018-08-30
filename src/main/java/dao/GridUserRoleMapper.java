package dao;

import java.util.List;
import java.util.Map;

import model.GridUserRole;

public interface GridUserRoleMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByUserRole(Map<String,Object> map);

    int insert(GridUserRole record);

    int insertSelective(GridUserRole record);

    GridUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridUserRole record);

    int updateByPrimaryKey(GridUserRole record);
    List<GridUserRole> getGridUserRole(GridUserRole record);
}