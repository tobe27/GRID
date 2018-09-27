package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.BusinessProduct;

public interface BusinessProductMapper {
	/**
	 * 批量插入业务信息
	 * @param moneys
	 * @param codes
	 * @param record
	 * @return
	 */
    int insertBusinesss(@Param("list") List<BusinessProduct> list);
    
    /**
	 * 根据身份证号查询办理过的业务信息
	 * @param idNumber
	 * @return
	 */
    List<BusinessProduct> getBusinesss(String idNumber);
    
    /**
     * 批量修改业务信息
     * @param money
     * @param ids
     * @param record
     * @return
     */
    int updateBusinesss(@Param("list") List<BusinessProduct> list);
}
