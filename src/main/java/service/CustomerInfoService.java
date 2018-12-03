package service;

import model.CustomerInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
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
     * 添加客户信息-草稿，不做参数校验
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertDraft(CustomerInfo record) throws Exception;

    /**
     * 通过Excel导入
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertByExcel(CustomerInfo record) throws Exception;

    /**
     * 把户籍信息转换成正常客户
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertByResidentInfo(CustomerInfo record) throws Exception;


    /**
     * 批量导入客户信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean batchSave(List<CustomerInfo> record) throws Exception;

    /**
     * 获取客户信息详情
     * @param customerId
     * @return
     * @throws Exception
     */
    CustomerInfo getCustomerByPrimaryKey(Long customerId) throws Exception;

    /**
     * 获取客户信息详情BY身份证
     * @param idNumber
     * @return
     * @throws Exception
     */
    CustomerInfo getCustomerByIdNumber(String idNumber) throws Exception;

    /**
     * 申请表聚和接口
     * @param customerId
     * @return
     * @throws Exception
     */
    Map<String, Object> getCustomerForm(Long customerId) throws Exception;

    /**
     * 条件获取客户信息列表
     * @param record
     * @return
     * @throws Exception
     */
    List<CustomerInfo> listCustomers(CustomerInfo record) throws Exception;

    /**
     * 通过用户ID获取客户列表
     * @param record
     * @return
     * @throws Exception
     */
    List<CustomerInfo> listCustomersByAccountId(CustomerInfo record) throws Exception;

    /**
     * 通过机构号获取客户列表
     * @param record
     * @return
     * @throws Exception
     */
    List<CustomerInfo> listCustomersByOrgCode(CustomerInfo record) throws Exception;

    /**
     * 修改客户信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(CustomerInfo record) throws Exception;


    /**
     * 修改客户信息-草稿，不做参数校验
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateDraft(CustomerInfo record) throws Exception;


    /**
     * 修改客户信息可用状态
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateCustomerStatus(CustomerInfo record) throws Exception;

    /**
     * 批量修改客户信息可用状态
     * @param list
     * @return
     * @throws Exception
     */
    boolean batchUpdateStatus(List<CustomerInfo> list) throws Exception;
}
