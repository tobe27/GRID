package dao;

import java.util.List;

import model.ProductDict;

public interface ProductDictMapper {
	/**
	 * 插入银行产品信息
	 * @param record
	 * @return
	 */
	int insertSelective(ProductDict record);
	
	/**
	 * 获取目前的最大编码
	 * @return
	 */
	String getMaxCode();
	
	/**
	 * 修改银行产品信息
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(ProductDict record);
	
	/**
	 * 银行产品列表查询
	 * @param state
	 * @return
	 */
	List<ProductDict> listProducts(Integer state);
	
	/**
	 * 修改银行产品状态
	 * @param record
	 * @return
	 */
	int updateProductState(ProductDict record);
	
	/**
	 * 查询所有开启的银行产品名称及编码
	 * @return
	 */
	List<ProductDict> listAllOpenProducts();
}
