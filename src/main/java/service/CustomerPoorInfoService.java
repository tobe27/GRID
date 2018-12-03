package service;

import java.util.List;
import java.util.Map;

import model.CustomerPoorInfo;

public interface CustomerPoorInfoService {
    boolean deleteByPrimaryKey(Integer id) throws Exception;

    boolean insertSelective(CustomerPoorInfo record)throws Exception;

    CustomerPoorInfo selectByPrimaryKey(Integer id)throws Exception;

    boolean updateByPrimaryKeySelective(CustomerPoorInfo record)throws Exception;
    
    Map<String,Object> insertFromExcel(List<Map<String,Object>> list)throws Exception;
    boolean deleteByIdNumber(String idNumber)throws Exception;
    List<CustomerPoorInfo> getListByIdNumber(CustomerPoorInfo record)throws Exception;
 
}
