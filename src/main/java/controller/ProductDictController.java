package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import model.ProductDict;
import model.ResponseData;
import service.ProductDictService;

@RestController
@RequestMapping
public class ProductDictController {
	@Autowired
	private ProductDictService productDictService;
	
	/**
	 * 银行产品查询
	 * @param state
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
    @RequestMapping(value = "/product/dict/list", method = RequestMethod.GET)
	public ResponseData listProduct(Integer state,Integer pageNum, Integer pageSize){
		if (pageNum == null || pageSize == null) {
            return new ResponseData().fail("页码和页大小不能为空");
        }
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<ProductDict> list = productDictService.listProducts(state);
            if (list == null || list.isEmpty()) {
                return new ResponseData().fail("产品列表不存在");
            }
            PageInfo<ProductDict> pageInfo = new PageInfo<>(list);
            return new ResponseData().success().result("count", pageInfo.getTotal()).data(pageInfo.getList());
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
	} 
//    /**
//     * 调用此接口批量添加银行产品
//     *  在部署项目前使用 
//     * 注意，调用前将  product_dict表数据清空 
//     *           business_info中产品编码字段删除
//     * @param info
//     * @return
//     */
//    @RequestMapping(value = "/product/dict/add/get", method = RequestMethod.GET)
//    public ResponseData insertProducts() {
//        try {
//    		//银行所有产品
//        	String ss[]={"易贷卡","经营性贷款","一路e贷合计","丰易贷","购易贷","借记卡",
//        			"手机银行","信e付","信用卡","ETC","短信通","POS机","云证通"};
//        	for(String s:ss){
//        		ProductDict record=new ProductDict();
//            	record.setName(s);
//            	productDictService.insertProduct(record);
//        	}
//            return new ResponseData().success();
//        } catch (Exception e) {
//            return new ResponseData().fail(e.getMessage());
//        }
//    }
	 /**
     * 调用此接口新增银行产品信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/product/dict/add", method = RequestMethod.POST)
    public ResponseData insertProduct(ProductDict record) {
        try {
        	productDictService.insertProduct(record);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }
	
    /**
     * 调用此接口编辑银行产品信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/product/dict/update", method = RequestMethod.PUT)
    public ResponseData updateProduct(ProductDict record) {
        try {
        	productDictService.updateProduct(record);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }
    
    /**
     * 调用此接口关闭银行产品
     * @param info
     * @return
     */
    @RequestMapping(value = "/product/dict/close", method = RequestMethod.PUT)
    public ResponseData closeProduct(ProductDict record) {
        try {
        	record.setState(0);
        	productDictService.updateProductState(record);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }
    /**
     * 调用此接口开启银行产品
     * @param info
     * @return
     */
    @RequestMapping(value = "/product/dict/open", method = RequestMethod.PUT)
    public ResponseData openProduct(ProductDict record) {
        try {
        	record.setState(1);
        	productDictService.updateProductState(record);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }
    
}
