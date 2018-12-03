package dao;

import java.util.List;
import java.util.Map;

import model.CustomerCreditDetail;

public interface CustomerCreditDetailMapper {
    int deleteByPrimaryKey(Long id);
    int insertSelective(CustomerCreditDetail record);
    CustomerCreditDetail selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(CustomerCreditDetail record);
    List<CustomerCreditDetail> getList(Map<String,Object> map);
    List<CustomerCreditDetail>  getByIdNumber (Map<String,Object> map);
    int deleteByIdNumber(Map<String,Object> map);
}