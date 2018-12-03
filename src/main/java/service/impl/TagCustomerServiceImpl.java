package service.impl;

import dao.CustomerBlackListMapper;
import dao.TagCustomerMapper;
import exception.MyException;
import model.CustomerBlackList;
import model.CustomerInfo;
import model.TagCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.CustomerPoorInfoService;
import service.TagCustomerService;
import util.ValidUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class TagCustomerServiceImpl implements TagCustomerService {
    @Autowired
    TagCustomerMapper tagCustomerMapper;
    @Autowired
    private CustomerPoorInfoService customerPoorInfoService;
    @Autowired
    CustomerBlackListMapper customerBlackListMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
            logger.info("删除客户所有标签异常："+e.getMessage());
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
        //删除黑白灰名单标签的操作
        if((record.getTagId() == 1)||(record.getTagId() == 5)||(record.getTagId() == 3)) {
            throw new MyException("客户黑白灰名相关操作请到背靠背信息评议模块中进行");
        }
        try {
        	//删除贫困户相关信息的操作
        	if(record.getTagId() == 4) {
        		customerPoorInfoService.deleteByIdNumber(record.getIdNumber());
        	}
            return tagCustomerMapper.deleteTagByIdNumberAndTagId(record) == 1;
        } catch (Exception e) {
            logger.info("删除客户当前标签异常："+e.getMessage());
            throw new MyException("删除客户当前标签出现异常");
        }
    }

    /**
     * 添加客户标签信息
     * @param  idNumber
     * @param tagIds 多个标签以逗号隔开,如果为空直接删除全部
     * @return
     */
    @Override
    @Transactional
    public boolean insertSelective(String idNumber, String tagIds) throws Exception {
        if (!ValidUtil.isLength(idNumber, 18)) {
            throw new MyException("身份证号码错误");
        }
        int delCount;
        int count = 0;
        if (ValidUtil.isEmpty(tagIds)) {
            logger.info("删除所有标签");
            delCount = tagCustomerMapper.deleteTagByIdNumber(idNumber);
            logger.info("删除标签数：" + delCount);
            return true;
        } else {
            //添加标签之前先删除之前的标签
            tagCustomerMapper.deleteTagByIdNumber(idNumber);
            TagCustomer record = new TagCustomer();
            record.setIdNumber(idNumber);
            // 创建时间
            long now = System.currentTimeMillis();
            record.setCreatedAt(now);
            record.setUpdatedAt(now);

            String[] tags = tagIds.split(",");
            // 判断-黑白灰名单不能同时选择
            int times = 0;
            for (String tagId : tags) {
                if ("1".equals(tagId) || "2".equals(tagId) || "3".equals(tagId) || "4".equals(tagId) || "5".equals(tagId)) {
                    times ++;
                }
            }
            logger.info("黑白灰名单勾选数：" + times);
            if (times >= 2) {
                throw new MyException("黑白灰名单、贫困户、授信客户标签只可选择其中一个");
            }

            for (String tagId : tags) {
                if (!ValidUtil.isNumber(tagId)) {
                    throw new MyException("标签编号参数传入错误");
                }
                record.setTagId(Long.valueOf(tagId));
                try {
                    tagCustomerMapper.insertSelective(record);
                    count ++;
                } catch (Exception e) {
                    logger.info("新建客户标签异常：" + e.getMessage());
                    throw new MyException("新建客户标签出现异常");
                }
            }
            return count == tags.length;
        }


    }

    /**
     * 获取客户的所有标签
     *
     * @param idNumber
     * @return
     */
    @Override
    public List<TagCustomer> listTagsByIdNumber(String idNumber) throws Exception {
        List<TagCustomer> tagCustomerList;
        List<TagCustomer> tagCustomers = new ArrayList<>();
        try {
            tagCustomerList = tagCustomerMapper.listTagsByIdNumber(idNumber);
        } catch (Exception e) {
            logger.info("获取客户标签异常："+e.getMessage());
            throw new MyException("获取客户所有标签出现异常");
        }
        for (TagCustomer tagCustomer : tagCustomerList) {
            if (tagCustomer.getTagId() != 1) {
                tagCustomers.add(tagCustomer);
            }
            if (tagCustomer.getTagId() == 1) {
                CustomerBlackList blackList;
                try {
                    blackList = customerBlackListMapper.getByIdNumber(tagCustomer.getIdNumber());
                } catch (Exception e) {
                    logger.info("获取黑名单原因出现异常："+e.getMessage());
                    throw new MyException("获取黑名单原因出现异常");
                }

                if (blackList != null) {
                    tagCustomer.setReason(blackList.getReason());
                    tagCustomers.add(tagCustomer);
                }

            }
        }
        return tagCustomers;
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
            logger.info("查询标签客户数异常："+e.getMessage());
            throw new MyException("查询该标签的客户数出现异常");
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
            logger.info("查询标签客户异常："+e.getMessage());
            throw new MyException("查询该标签的客户信息出现异常");
        }
    }

}
