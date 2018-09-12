package dao;

import model.CustomerInfo;

import java.util.List;

public interface CustomerInfoMapper {
    int deleteByPrimaryKey(Long customerId);

    int insertSelective(CustomerInfo record);

    CustomerInfo getCustomerByPrimaryKey(Long customerId);

    List<CustomerInfo> listCustomers(CustomerInfo record);

    int updateByPrimaryKeySelective(CustomerInfo record);

}