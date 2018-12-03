package service;

import java.util.List;
import java.util.Map;

import model.CustomerCreditDetail;

public interface CustomerCreditDetailService {
	
		 boolean deleteByPrimaryKey(Long id) throws Exception;
		 boolean insertSelective(CustomerCreditDetail record)throws Exception;
		 CustomerCreditDetail selectByPrimaryKey(Long id)throws Exception;
		boolean updateByPrimaryKeySelective(CustomerCreditDetail record)throws Exception;	    
	    /*List<CustomerCreditDetail> getFaceTackListByPage(Map<String,Object> map)throws Exception;*/
	    List<CustomerCreditDetail> getListByPage(Map<String,Object> map)throws Exception;
	     Map<String,Object> insertByExcel(List<Map<String,Object>> list,Map<String,Object> map)throws Exception;
	     String  getCreditReportHtmlCode(long creditDetailId)throws Exception;
	     Map<String,Object> batchSave(List<CustomerCreditDetail> list,Map<String,Object> map)throws Exception;
   

}
