package service;

import java.util.List;
import java.util.Map;

import model.CustomerGreylist;
import model.CustomerWhitelist;

public interface CustomerGreyListService {
	 boolean deleteByPrimaryKey(Long id) throws Exception;
	  boolean insertSelective(CustomerGreylist record)throws Exception;

	  CustomerGreylist selectByPrimaryKey(Long id)throws Exception;

		    boolean updateByPrimaryKeySelective(CustomerGreylist record)throws Exception;
		    
		    List<CustomerGreylist> getCustomerGreylistByPage(Map<String,Object> map);
		    int insertByExcel(List<Map<String,Object>> list,String gridCode)throws Exception;
}
