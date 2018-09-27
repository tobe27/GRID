package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.BusinessProduct;
import model.ResponseData;
import service.BusinessProductService;
import service.ProductDictService;

@RestController
@RequestMapping
public class BusinessProductController {
	@Autowired
	private BusinessProductService businessProductService;
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
	@RequestMapping(value = "/customer/business/product/{idNumber}", method = RequestMethod.GET)
	public ResponseData getBusiness(@PathVariable String idNumber) {
        return new ResponseData().success().data(businessProductService.getBusinesss(idNumber));
	}
	
	/**
	 * 调用此接口批量添加客户业务信息
	 * @param record
	 * @param info
	 * @return
	 */
    @RequestMapping(value = "/customer/business/product", method = RequestMethod.POST)
    public ResponseData insertBusiness(@RequestBody ArrayList<BusinessProduct> record) {
    	businessProductService.insertBusinesss(record);
        return new ResponseData().success();
    }
    
    /**
     * 调用此接口批量编辑客户业务信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/business/product", method = RequestMethod.PUT)
    public ResponseData updateBusiness(@RequestBody List<BusinessProduct> record) {
    	businessProductService.updateBusinesss(record);
        return new ResponseData().success();
    }
}
