package dao;

import model.CustomerInfo;

import java.util.List;

public interface CustomerInfoMapper {
    int deleteByPrimaryKey(Long customerId);

    int insertSelective(CustomerInfo record);

    int batchSave(List<CustomerInfo> record);

    CustomerInfo getCustomerByPrimaryKey(Long customerId);

    CustomerInfo getCustomerByIdNumber(String idNumber);

    List<CustomerInfo> listCustomers(CustomerInfo record);

    List<CustomerInfo> listCustomersByAccountId(CustomerInfo record);

    List<CustomerInfo> listCustomersByOrgCode(CustomerInfo record);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateCustomerStatus(CustomerInfo record);

    int batchUpdateStatus(List<CustomerInfo> list);
   
    

}