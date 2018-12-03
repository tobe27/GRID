package dao;

import model.CustomerBusiness;

public interface CustomerBusinessMapper {
	/**
	 * 插入业务信息
	 * @param record
	 * @return
	 */
	int insertSelective(CustomerBusiness record);
			
	/**
	 * 根据身份证号查询业务信息
	 * @param idNumber
	 * @return
	 */
	CustomerBusiness getBusinesssByIdNumber(String idNumber);
	
	/**
	 * 修改业务信息
	 * @param record
	 * @return
	 */
	int updateSelective(CustomerBusiness record);

}
