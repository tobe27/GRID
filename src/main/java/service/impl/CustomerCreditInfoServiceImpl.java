package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CustomerCreditInfoMapper;
import exception.MyException;
import model.CustomerCreditInfo;
import service.CustomerCreditInfoService;

@Service
public class CustomerCreditInfoServiceImpl implements CustomerCreditInfoService {
	@Autowired
	private CustomerCreditInfoMapper creditInfo;
	/**
	 * 得到客户信用信息
	 * @param idNumber
	 * @return
	 */
	public CustomerCreditInfo selectByIdNUmber(String idNumber) {
		try {
            return creditInfo.selectByIdNUmber(idNumber);
        } catch (Exception e) {
            throw new MyException("获取客户信用信息出现异常");
        }
	}

}
