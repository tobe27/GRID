package dao;

import java.util.List;
import java.util.Map;

import model.HomeBasicInfo;

public interface HomeBasicInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HomeBasicInfo record);

    int insertSelective(HomeBasicInfo record);

    HomeBasicInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HomeBasicInfo record);

    int updateByPrimaryKey(HomeBasicInfo record);
    
    HomeBasicInfo getPresidentAndMiddleHomeBasicInfo();
    HomeBasicInfo getBasicInfo();
    
    List<Map<String,Object>> getPresidentAndMiddleBranchInfoSort(Map<String,Object> map);
    
    List<Map<String,Object>> getPresidentAndMiddleAccountInfoSort(Map<String,Object> map);
    
    Map<String,Object> getSubordinateOrgCode(Map<String,Object> map);
    Map<String,Object> getAccountIdsByOrgCodes(Map<String,Object> map);
    
    
    List<Map<String,Object>> getBasicAccountInfo(Map<String,Object> map);
}