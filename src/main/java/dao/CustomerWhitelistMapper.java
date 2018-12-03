package dao;

import java.util.List;
import java.util.Map;

import model.CustomerWhitelist;

public interface CustomerWhitelistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerWhitelist record);

    int insertSelective(CustomerWhitelist record);

    CustomerWhitelist selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerWhitelist record);

    int updateByPrimaryKey(CustomerWhitelist record);
    List<CustomerWhitelist> getByIdOrIdnumber(CustomerWhitelist record);
    
    List<Map<String,Object>> getWhiteLists(Map<String,Object> map);
}