package dao;

import java.util.List;
import java.util.Map;

import model.GridMap;

public interface GridMapMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GridMap record);

    int insertSelective(GridMap record);

    GridMap selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridMap record);

    int updateByPrimaryKey(GridMap record);
    
   int  batchSave(List<GridMap> list);
     List<GridMap> getGridMapList(Map<String,Object> map);
    int setDeleteFlag(Map<String,Object> map);
}