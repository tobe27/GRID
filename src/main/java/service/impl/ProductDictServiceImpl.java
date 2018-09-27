package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BusinessInfoMapper;
import dao.ProductDictMapper;
import exception.MyException;
import model.ProductDict;
import service.ProductDictService;
import util.ValidUtil;

@Service
public class ProductDictServiceImpl implements ProductDictService{
	@Autowired
	private ProductDictMapper productDictMapper;
	@Autowired
	private BusinessInfoMapper businessInfoMapper;
	
	/**
	 * 查询银行所有产品
	 * @param state
	 * @return
	 * @throws MyException
	 */
	public List<ProductDict> listProducts(Integer state) throws MyException {
		try {
            return productDictMapper.listProducts(state);
        } catch (Exception e) {
            throw new MyException("获取银行产品信息列表出现异常");
        }
	}

	/**
	 * 添加银行产品
	 * @param record
	 * @return
	 * @throws MyException 
	 */
	@Transactional
	public synchronized boolean insertProduct(ProductDict record) throws MyException {
		if (ValidUtil.isEmpty(record.getName())) {
            throw new MyException("银行产品名称不能为空");
        }
		
        int row1=0,row2=0;
        try {
        	String code=productDictMapper.getMaxCode().substring(1);
    		Integer num=Integer.parseInt(code)+1;
    		code=num.toString();
    		if(code.length()==3){
    			code="p"+code;
    		}else if(code.length()==2){
    			code="p0"+code;
    		}else if(code.length()==1){
    			code="p00"+code;
    		}else{
    			throw new MyException("生成银行产品编码出错");
    		}
    		record.setCode(code);
    		
    		// 创建时间
            long now = System.currentTimeMillis();
            record.setCreatedAt(now);
            record.setUpdatedAt(now);
            
        	row1=productDictMapper.insertSelective(record);
        	row2=businessInfoMapper.updateTableStructure(code);
        } catch (Exception e) {
            throw new MyException("添加银行产品信息出现异常");
        }
        
        return row1==1&&row2==1;
	}

	/**
	 * 修改产品
	 * @param record
	 * @return
	 * @throws MyException 
	 */
	public boolean updateProduct(ProductDict record) throws MyException {
		if (ValidUtil.isEmpty(record.getId())) {
            throw new MyException("银行产品id不能为空");
        }
        record.setUpdatedAt(System.currentTimeMillis());
        try {
            return productDictMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("修改银行产品信息出现异常");
        }
	}

	/**
	 * 修改产品状态  state 0关闭  1开启
	 * @param record
	 * @return
	 * @throws MyException 
	 */
	public boolean updateProductState(ProductDict record) throws MyException {
		if (ValidUtil.isEmpty(record.getId())) {
            throw new MyException("银行产品id不能为空");
        }
        record.setUpdatedAt(System.currentTimeMillis());
        try {
            return productDictMapper.updateProductState(record) == 1;
        } catch (Exception e) {
            throw new MyException("修改银行产品状态信息出现异常");
        }
	}
	
	/**
	 * 查询银行所有开启的产品
	 * @return
	 */
	public List<ProductDict> listAllOpenProducts(){
		try {
			return productDictMapper.listAllOpenProducts();
        } catch (Exception e) {
            throw new MyException("查询所有开启的银行产品出现异常");
        }
	}

}
