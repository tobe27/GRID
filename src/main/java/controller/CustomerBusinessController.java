package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.CustomerBusiness;
import model.CustomerBusinessProduct;
import model.ResponseData;
import service.CustomerBusinessService;
import service.ProductDictService;

@RestController
@RequestMapping
public class CustomerBusinessController {
	@Autowired
	private CustomerBusinessService businessService;
	@Autowired
	private ProductDictService productDictService;
	
	/**
	 * 调用此接口    查询银行所有开启的产品
	 *    客户可与银行的这些产品发生业务关系
	 * @return
	 */
	@RequestMapping(value = "/customer/business/product/list", method = RequestMethod.GET)
	public ResponseData getProductAllOpen() {
		return new ResponseData().success().data(productDictService.listAllOpenProducts());
	}
	
	/**
	 * 调用此接口得到客户业务信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/customer/business/{idNumber}", method = RequestMethod.GET)
	public ResponseData getBusiness(@PathVariable String idNumber) {
        return new ResponseData().success().data(businessService.getBusines(idNumber));
	}
	
	/**
	 * 调用此接口添加客户业务信息
	 * @param record
	 * @param info
	 * @return
	 */
    @RequestMapping(value = "/customer/business", method = RequestMethod.POST)
    public ResponseData insertBusiness(CustomerBusiness record) {
    	businessService.insertBusiness(record);
        return new ResponseData().success();
    }
    
    /**
     * 调用此接口编辑客户业务信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/business", method = RequestMethod.PUT)
    public ResponseData updateBusiness(CustomerBusiness record) {
    	businessService.updateBusiness(record);
        return new ResponseData().success();
    }
    
	/**
	 * 调用此接口批量添加客户业务与产品关联信息
	 * @param record
	 * @param info
	 * @return
	 */
    @RequestMapping(value = "/customer/business/product", method = RequestMethod.POST)
    public ResponseData insertBusinessProduct(@RequestBody ArrayList<CustomerBusinessProduct> record) {
    	businessService.insertBusinessProduct(record);
        return new ResponseData().success();
    }
    
    /**
     * 调用此接口批量编辑客户业务与产品关联信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/business/product", method = RequestMethod.PUT)
    public ResponseData updateBusinessProduct(@RequestBody List<CustomerBusinessProduct> record) {
    	businessService.updateBusinessProduct(record);
        return new ResponseData().success();
    }
}
