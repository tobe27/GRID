package service;

import java.util.List;
import java.util.Map;

import model.CustomerBackToBackPreCredit;
import model.CustomerBlackList;

public interface CustomerBlackToBackPreCreditService {
	boolean deleteByPrimaryKey(Long id) throws Exception;

   

	boolean insertSelective(CustomerBackToBackPreCredit record)throws Exception;

    CustomerBackToBackPreCredit selectByPrimaryKey(Long id)throws Exception;

    boolean updateByPrimaryKeySelective(CustomerBackToBackPreCredit record)throws Exception;
    List<CustomerBackToBackPreCredit> getListByPage(Map<String,Object> map) throws Exception;;

   
}
