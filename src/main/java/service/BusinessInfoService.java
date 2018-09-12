package service;

import java.util.Map;

import exception.MyException;

public interface BusinessInfoService {
	/**
	 * 查找业务信息
	 * @param idNumber
	 * @return
	 * @throws MyException 
	 */
	Map<String,Object> getBusinessByPrimaryKey(String idNumber) throws MyException;
	
	/**
	 * 插入业务信息
	 * @param record
	 * @return
	 * @throws Exception
	 */
	boolean insertSelective(Map<String,Object> record) throws Exception; 
	
	
	 /**
     * 修改业务信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(Map<String,Object> record) throws Exception;
}
