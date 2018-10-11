package service.impl;

import dao.TagCustomerMapper;
import exception.MyException;
import model.CustomerInfo;
import model.TagCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.TagCustomerService;
import util.ValidUtil;
import java.util.List;
import java.util.Set;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class TagCustomerServiceImpl implements TagCustomerService {
    @Autowired
    TagCustomerMapper tagCustomerMapper;

    /**
     * 通过身份证删除客户标签信息
     *
     * @param idNumber
     * @return
     */
    @Override
    public boolean deleteTagByIdNumber(String idNumber) throws Exception {
        try {
            return tagCustomerMapper.deleteTagByIdNumber(idNumber) == 1;
        } catch (Exception e) {
            throw new MyException("删除客户所有标签出现异常");
        }
    }

    /**
     * 通过身份证与标签删除客户标签信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteTagByIdNumberAndTagId(TagCustomer record) throws Exception {
        try {
            return tagCustomerMapper.deleteTagByIdNumberAndTagId(record) == 1;
        } catch (Exception e) {
            throw new MyException("删除客户当前标签出现异常");
        }
    }

    /**
     * 添加客户标签信息
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public boolean insertSelective(TagCustomer record) throws Exception {
        if (ValidUtil.isEmpty(record.getIdNumber()) || ValidUtil.isEmpty(record.getTagName())
                || ValidUtil.isEmpty(record.getTagId())) {
            throw new MyException("身份证、标签ID、标签名不能为空");
        }
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            //添加标签之前先删除之前的标签
//            tagCustomerMapper.deleteTagByIdNumber(record.getIdNumber());
            return tagCustomerMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("添加客户标签出现异常");
        }
    }

    /**
     * 获取客户的所有标签
     *
     * @param idNumber
     * @return
     */
    @Override
    public Set<TagCustomer> listTagsByIdNumber(String idNumber) throws Exception {
        try {
            return tagCustomerMapper.listTagsByIdNumber(idNumber);
        } catch (Exception e) {
            throw new MyException("获取客户所有标签出现异常");
        }
    }

    /**
     * 获取该标签所有客户数
     *
     * @param tagId
     * @return
     */
    @Override
    public int countCustomersByTagId(Long tagId) throws Exception {
        try {
            return tagCustomerMapper.countCustomersByTagId(tagId);
        } catch (Exception e) {
            throw new MyException("获取该标签的客户数出现异常");
        }
    }

    /**
     * 获取该标签所有的客户信息
     *
     * @param tagId
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerInfo> listCustomersByTagId(Long tagId) throws Exception {
        try {
            return tagCustomerMapper.listCustomersByTagId(tagId);
        } catch (Exception e) {
            throw new MyException("获取该标签的客户信息出现异常");
        }
    }

}
