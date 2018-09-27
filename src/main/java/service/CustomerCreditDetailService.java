package service;

import java.util.List;
import java.util.Map;

import model.CustomerCreditDetail;

public interface CustomerCreditDetailService {
	
	boolean deleteByPrimaryKey(Long id) throws Exception;
	  boolean insertSelective(CustomerCreditDetail record)throws Exception;

	 CustomerCreditDetail selectByPrimaryKey(Long id)throws Exception;

	boolean updateByPrimaryKeySelective(CustomerCreditDetail record)throws Exception;
		    
   List<CustomerCreditDetail> getListByPage(Map<String,Object> map)throws Exception;
   int insertByExcel(List<Map<String,Object>> list,String gridCode)throws Exception;
   
   

}
