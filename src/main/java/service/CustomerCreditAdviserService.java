package service;

import java.util.List;
import java.util.Map;

import model.CustomerCreditAdviser;

public interface CustomerCreditAdviserService {
	boolean deleteByPrimaryKey(Long id)throws Exception;
	boolean insertSelective(CustomerCreditAdviser record)throws Exception;
    CustomerCreditAdviser selectByPrimaryKey(Long id)throws Exception;
    boolean updateByPrimaryKeySelective(CustomerCreditAdviser record) throws Exception;
    List<CustomerCreditAdviser> getCustomerCreditAdviserist(Map<String,Object> map)throws Exception;
    int insertByExcel(List<Map<String,Object>> list,String gridCode)throws Exception;
}
