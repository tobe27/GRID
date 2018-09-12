package dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BusinessInfoMapper {
	/**
	 * 插入业务信息
	 * @param map
	 * @return
	 */
    int insertSelective(@Param("map") Map<String,Object> map);
    
    /**
	 * 查找业务信息
	 * @param idNumber
	 * @return
	 */
    Map<String,Object> getBusinessByPrimaryKey(String idNumber);
    
    /**
     * 修改业务信息
     * @param map
     * @return
     */
    int updateByPrimaryKeySelective(@Param("map") Map<String,Object> map);
    
    /**
     * 修改表结构  添加产品列cloName
     * @param cloName
     * @return
     */
    int updateTableStructure(@Param("cloName")String cloName);

}