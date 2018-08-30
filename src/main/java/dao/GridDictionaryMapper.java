package dao;

import java.util.List;
import java.util.Map;

import model.GridDictionary;

public interface GridDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GridDictionary record);

    int insertSelective(GridDictionary record);

    GridDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridDictionary record);

    int updateByPrimaryKey(GridDictionary record);
    int deleteByCode(Map<String,Object> map);
    List<GridDictionary> getListByName(Map<String,Object> map);
    List<GridDictionary>  getList(Map<String,Object> map);
    
    List<GridDictionary> getListByCode(Map<String,Object> map);
}