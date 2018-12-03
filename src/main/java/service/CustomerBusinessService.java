package service;

import java.util.List;
import java.util.Map;

import model.CustomerBusiness;
import model.CustomerBusinessProduct;

public interface CustomerBusinessService {
	/**
	 * 根据身份证号查询办理过的客户业务与产品关联
	 * @param idNumber
	 * @return
	 */
    Map<String,Object> getBusines(String idNumber);
	
    /**
     * 插入业务信息
     * @param record
     */
    void insertBusiness(CustomerBusiness record);
    
    /**
     * 修改业务信息
     * @param record
     */
    void updateBusiness(CustomerBusiness record);
    
	/**
	 * 批量插入客户业务与产品关联信息
	 * @param record
	 * @return
	 */
	boolean insertBusinessProduct(List<CustomerBusinessProduct> record);       
    
    /**
     * 批量修改客户业务与产品关联
     * @param record
     * @return
     */
    boolean updateBusinessProduct(List<CustomerBusinessProduct> record);
}
