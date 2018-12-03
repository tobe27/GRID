package service;

import java.util.List;
import java.util.Map;

import model.CustomerWhitelist;

public interface CustomerWhiteListService {
	 boolean deleteByPrimaryKey(Long id) throws Exception;
     boolean insertSelective(CustomerWhitelist record)throws Exception;

	    CustomerWhitelist selectByPrimaryKey(Long id)throws Exception;

	    boolean updateByPrimaryKeySelective(CustomerWhitelist record)throws Exception;
	    
	    List<Map<String,Object>> getCustomerWhiteListByPage(Map<String,Object> map);
	    
	    Map<String,Object> insertByExcel(List<Map<String,Object>> list,String gridCode)throws Exception;
	    boolean moveCustomerInfo(String idNumber,String type,String reason )throws Exception;

	
}
