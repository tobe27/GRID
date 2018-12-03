package service;

import model.CustomerCreditInfo;

public interface CustomerCreditInfoService {
	
	/**
	 * 得到客户信用信息
	 * @param idNumber
	 * @return
	 */
	CustomerCreditInfo selectByIdNUmber(String idNumber);
}
