package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.ResponseData;
import service.BusinessInfoService;
import service.ProductDictService;

@RestController
@RequestMapping
public class BusinessInfoController {
	@Autowired
	private BusinessInfoService businessInfoService;
	@Autowired
	private ProductDictService productDictService;
	
	/**
	 * 调用此接口    查询银行所有开启的产品
	 *    客户可与银行的这些产品发生业务关系
	 * @return
	 */
	@RequestMapping(value = "/product/all/open", method = RequestMethod.GET)
	public ResponseData getProductAllOpen() {
		return new ResponseData().success().data(productDictService.listAllOpenProducts());
	}
	
	/**
	 * 调用此接口得到客户业务信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/business/{idNumber}", method = RequestMethod.GET)
	public ResponseData getBusiness(@PathVariable String idNumber) {
		Map<String,Object> info = businessInfoService.getBusinessByPrimaryKey(idNumber);
        if (info == null) {
            return new ResponseData().fail("该客户不存业务信息在");
        }
        return new ResponseData().success().data(info);
	}
	
	 /**
     * 调用此接口新增客户业务信息
     * @param info
     * @return
	 * @throws Exception 
     */
    @RequestMapping(value = "/business", method = RequestMethod.POST)
    public ResponseData insertBusiness(@RequestParam Map<String,Object> info) throws Exception {
    	businessInfoService.insertSelective(info);
        return new ResponseData().success();
    }
    
    /**
     * 调用此接口编辑客户业务信息
     * @param info
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/business/{id}", method = RequestMethod.PUT)
    public ResponseData updateBusiness(@RequestParam Map<String,Object> info) throws Exception {
    	businessInfoService.updateByPrimaryKeySelective(info);
        return new ResponseData().success();
    }
    
}
