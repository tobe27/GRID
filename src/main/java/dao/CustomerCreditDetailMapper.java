package dao;

import java.util.List;
import java.util.Map;

import model.CustomerBlackList;
import model.CustomerCreditDetail;

public interface CustomerCreditDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerCreditDetail record);

    int insertSelective(CustomerCreditDetail record);

    CustomerCreditDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerCreditDetail record);

    int updateByPrimaryKey(CustomerCreditDetail record);
List<CustomerCreditDetail> getByIdOrIdnumber(CustomerCreditDetail record);
    
    List<CustomerCreditDetail> getList(Map<String,Object> map);
}