package service;

import java.util.List;

import exception.MyException;
import model.ProductDict;

public interface ProductDictService {
	
	/**
	 * 查询银行所有产品
	 * @param state
	 * @return
	 * @throws MyException
	 */
	List<ProductDict> listProducts(Integer state) throws MyException;
	
	/**
	 * 添加银行产品
	 * @param record
	 * @return
	 * @throws MyException 
	 */
	boolean insertProduct(ProductDict record) throws MyException;
	
	/**
	 * 修改产品
	 * @param record
	 * @return
	 * @throws MyException 
	 */
	boolean updateProduct(ProductDict record) throws MyException;
	
	/**
	 * 修改产品状态  state 0关闭  1开启
	 * @param record
	 * @return
	 * @throws MyException 
	 */
	boolean updateProductState(ProductDict record) throws MyException;
	
	/**
	 * 查询银行所有开启的产品
	 * @return
	 */
	List<ProductDict> listAllOpenProducts();
	
}
