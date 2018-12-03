package dao;

import model.CustomerBlackList;

import java.util.List;
import java.util.Map;

public interface CustomerBlackListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerBlackList record);

    int insertSelective(CustomerBlackList record);

    CustomerBlackList selectByPrimaryKey(Long id);

    CustomerBlackList getByIdNumber(String idNumber);

    int updateByPrimaryKeySelective(CustomerBlackList record);

    int updateByPrimaryKey(CustomerBlackList record);

    List<CustomerBlackList> getByIdOrIdnumber(CustomerBlackList record);

    List<Map<String,Object>> getBlackLists(Map<String,Object> map);
}