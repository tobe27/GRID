package dao;

import java.util.List;
import java.util.Map;

import model.CustomerInfo;
import model.GridInfo;

public interface GridInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GridInfo record);

    int insertSelective(GridInfo record);

    GridInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridInfo record);

    int updateByPrimaryKey(GridInfo record);

    List<GridInfo> checkSameGridCode(Map<String,Object> map);
    
    List<Map<String,Object>> getGridInfoList(Map<String,Object> map);

    List<String> getGridCodesByAccountIdOrOrgCode(GridInfo record);

    List<GridInfo>  getGridinfosByAccountIdOrOrgCode(GridInfo record);
    
}