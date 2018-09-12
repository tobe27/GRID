package service;

import model.CustomerInfo;

import java.util.List;

public interface CustomerInfoService {
    /**
     * 删除客户信息
     * @param customerId
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long customerId) throws Exception;

    /**
     * 添加客户信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(CustomerInfo record) throws Exception;

    /**
     * 获取客户信息详情
     * @param customerId
     * @return
     * @throws Exception
     */
    CustomerInfo getCustomerByPrimaryKey(Long customerId) throws Exception;

    /**
     * 条件获取客户信息列表
     * @param record
     * @return
     * @throws Exception
     */
    List<CustomerInfo> listCustomers(CustomerInfo record) throws Exception;

    /**
     * 修改客户信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(CustomerInfo record) throws Exception;
}
