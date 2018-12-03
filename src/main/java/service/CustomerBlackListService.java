package service;

import java.util.List;
import java.util.Map;

import model.CustomerBlackList;
import model.CustomerWhitelist;

public interface CustomerBlackListService {
	 boolean deleteByPrimaryKey(Long id) throws Exception;
     boolean insertSelective(CustomerBlackList record)throws Exception;

      CustomerBlackList selectByPrimaryKey(Long id)throws Exception;

	    boolean updateByPrimaryKeySelective(CustomerBlackList record)throws Exception;
	    
	    List<Map<String,Object>> getCustomerBlackListByPage(Map<String,Object> map)throws Exception;
	    Map<String,Object>  insertByExcel(List<Map<String,Object>> list,String gridCode)throws Exception;
}
