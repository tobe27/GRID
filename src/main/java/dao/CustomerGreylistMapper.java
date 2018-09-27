package dao;

import java.util.List;
import java.util.Map;

import model.CustomerGreylist;

public interface CustomerGreylistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerGreylist record);

    int insertSelective(CustomerGreylist record);

    CustomerGreylist selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerGreylist record);

    int updateByPrimaryKey(CustomerGreylist record);
List<CustomerGreylist> getByIdOrIdnumber(CustomerGreylist record);
    
    List<CustomerGreylist> getGreyLists(Map<String,Object> map);
}