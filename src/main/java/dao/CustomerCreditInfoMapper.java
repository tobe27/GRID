package dao;

import model.CustomerCreditInfo;

public interface CustomerCreditInfoMapper {
	/**
	 * 插入客户信用信息
	 * @param record
	 * @return
	 */
	int insertSelective(CustomerCreditInfo record);
			
	/**
	 * 根据身份证号查询客户信用信息
	 * @param idNumber
	 * @return
	 */
	CustomerCreditInfo selectByIdNUmber(String idNumber);
	
	/**
	 * 修改客户信用信息
	 * @param record
	 * @return
	 */
	int updateSelective(CustomerCreditInfo record);

}
