package dao;

import java.util.List;

import model.CustomerPoorInfo;

public interface CustomerPoorInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerPoorInfo record);

    int insertSelective(CustomerPoorInfo record);

    CustomerPoorInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerPoorInfo record);

    int updateByPrimaryKey(CustomerPoorInfo record);
    List<CustomerPoorInfo> getByIdnumber(CustomerPoorInfo record);
    
    int deleteByIdNumber(CustomerPoorInfo record);
    
    
}