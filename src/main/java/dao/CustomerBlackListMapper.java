package dao;

import java.util.List;
import java.util.Map;

import model.CustomerBlackList;

public interface CustomerBlackListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerBlackList record);

    int insertSelective(CustomerBlackList record);

    CustomerBlackList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerBlackList record);

    int updateByPrimaryKey(CustomerBlackList record);
List<CustomerBlackList> getByIdOrIdnumber(CustomerBlackList record);
    
    List<CustomerBlackList> getBlackLists(Map<String,Object> map);
}