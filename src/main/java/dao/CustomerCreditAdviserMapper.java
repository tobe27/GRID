package dao;

import java.util.List;
import java.util.Map;

import model.CustomerCreditAdviser;

public interface CustomerCreditAdviserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerCreditAdviser record);

    int insertSelective(CustomerCreditAdviser record);

    CustomerCreditAdviser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerCreditAdviser record);

    int updateByPrimaryKey(CustomerCreditAdviser record);
    
    List<CustomerCreditAdviser> getCustomerCreditAdviserList(Map<String,Object> record);
}