package service.impl;

import dao.CustomerInfoMapper;
import exception.MyException;
import model.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CustomerInfoService;
import util.ValidUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    CustomerInfoMapper infoMapper;

    /**
     * 删除客户信息
     * @param customerId
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long customerId) throws Exception {
        try {
            return infoMapper.deleteByPrimaryKey(customerId) == 1;
        } catch (Exception e) {
            throw new MyException("删除客户信息出现异常");
        }
    }

    /**
     * 添加客户信息
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(CustomerInfo record) throws Exception {
        if (ValidUtil.isEmpty(record.getCustomerName())) {
            throw new MyException("姓名不能为空");
        }
        if (!ValidUtil.length(record.getIdNumber(), 18)) {
            throw new MyException("身份证号必须是18位");
        }
        if (!ValidUtil.number(record.getHouseholdId())) {
            throw new MyException("客户户号必须是纯数字");
        }
        if (!ValidUtil.phone(record.getPhoneNumber())) {
            throw new MyException("客户手机号必须是1开头的11位有效号码");
        }
        if (!ValidUtil.notEmpty(record.getGridCode())) {
            throw new MyException("客户网格号不能为空");
        }
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return infoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("添加客户信息出现异常");
        }
    }

    /**
     * 获取客户信息详情
     * @param customerId
     * @return
     * @throws Exception
     */
    @Override
    public CustomerInfo getCustomerByPrimaryKey(Long customerId) throws Exception {
        try {
            return infoMapper.getCustomerByPrimaryKey(customerId);
        } catch (Exception e) {
            throw new MyException("获取客户信息出现异常");
        }
    }

    /**
     * 获取客户信息详情BY身份证
     *
     * @param idNumber
     * @return
     * @throws Exception
     */
    @Override
    public CustomerInfo getCustomerByIdNumber(String idNumber) throws Exception {
        try {
            return infoMapper.getCustomerByIdNumber(idNumber);
        } catch (Exception e) {
            throw new MyException("通过身份证获取客户信息出现异常");
        }
    }

    /**
     * 条件获取客户信息列表
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerInfo> listCustomers(CustomerInfo record) throws Exception {
        try {
            return infoMapper.listCustomers(record);
        } catch (Exception e) {
            throw new MyException("获取客户信息列表出现异常");
        }
    }

    /**
     * 修改客户信息
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(CustomerInfo record) throws Exception {
        if (ValidUtil.isEmpty(record.getCustomerName())) {
            throw new MyException("姓名不能为空");
        }
        if (!ValidUtil.length(record.getIdNumber(), 18)) {
            throw new MyException("身份证号必须是18位");
        }
        if (!ValidUtil.number(record.getHouseholdId())) {
            throw new MyException("客户户号必须是纯数字");
        }
        if (!ValidUtil.phone(record.getPhoneNumber())) {
            throw new MyException("客户手机号必须是1开头的11位有效号码");
        }
        if (!ValidUtil.notEmpty(record.getGridCode())) {
            throw new MyException("客户网格号不能为空");
        }
        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return infoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("编辑客户信息出现异常");
        }
    }
}
