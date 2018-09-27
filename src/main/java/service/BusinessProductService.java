package service;

import java.util.List;

import model.BusinessProduct;

public interface BusinessProductService {
	/**
	 * 批量插入业务信息
	 * @param record
	 * @return
	 */
	boolean insertBusinesss(List<BusinessProduct> record);
    
    /**
	 * 根据身份证号查询办理过的业务信息
	 * @param idNumber
	 * @return
	 */
    List<BusinessProduct> getBusinesss(String idNumber);
    
    /**
     * 批量修改业务信息
     * @param record
     * @return
     */
    boolean updateBusinesss(List<BusinessProduct> record);
}
