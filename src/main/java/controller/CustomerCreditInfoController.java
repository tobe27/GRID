package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.CustomerCreditInfo;
import model.ResponseData;
import service.CustomerCreditInfoService;

@RestController
@RequestMapping
public class CustomerCreditInfoController {
	@Autowired
	private CustomerCreditInfoService creditInfo;
	
	/**
	 * 调用此接口得到客户业务信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/customer/credit/{idNumber}", method = RequestMethod.GET)
	public ResponseData getCreditInfo(@PathVariable String idNumber) {
		CustomerCreditInfo info=creditInfo.selectByIdNUmber(idNumber);
		if(info==null){
			return new ResponseData().blank("客户信用信息不存在");
		}
        return new ResponseData().success().data(info);
	}
}
