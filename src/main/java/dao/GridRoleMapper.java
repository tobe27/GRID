package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import model.GridRole;

public interface GridRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(GridRole record);

    int insertSelective(GridRole record);

    GridRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(GridRole record);

    int updateByPrimaryKey(GridRole record);
    List<GridRole> getGridRoles(Map<String,Object> map);
}