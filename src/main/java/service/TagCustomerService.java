package service;

import model.CustomerInfo;
import model.TagCustomer;

import java.util.List;
import java.util.Set;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface TagCustomerService {
    /**
     * 通过身份证删除客户标签信息
     * @param idNumber
     * @return
     */
    boolean deleteTagByIdNumber(String idNumber) throws Exception;

    /**
     * 通过身份证与标签删除客户标签信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean deleteTagByIdNumberAndTagId(TagCustomer record) throws Exception;

    /**
     * 添加客户标签信息
     * @param record
     * @return
     */
    boolean insertSelective(TagCustomer record) throws Exception;

    /**
     * 获取客户的所有标签
     * @param idNumber
     * @return
     */
    Set<TagCustomer> listTagsByIdNumber(String idNumber) throws Exception;

    /**
     * 获取该标签所有客户数
     * @param tagId
     * @return
     */
    int countCustomersByTagId(Long tagId) throws Exception;

    /**获取该标签所有的客户信息
     * @param tagId
     * @return
     * @throws Exception
     */
    List<CustomerInfo> listCustomersByTagId(Long tagId) throws Exception;

}
