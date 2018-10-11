package service.impl;

import dao.FinanceInfoMapper;
import exception.MyException;
import model.FinanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FinanceInfoService;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class FinanceInfoServiceImpl implements FinanceInfoService {
    @Autowired
    FinanceInfoMapper financeInfoMapper;

    /**
     * 删除客户财务信息
     *
     * @param idNumber
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByIdNumber(String idNumber) throws Exception {
        try {
            return financeInfoMapper.deleteByIdNumber(idNumber) == 1;
        } catch (Exception e) {
            throw new MyException("删除客户财务信息出现异常");
        }
    }

    /**
     * 添加客户财务信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(FinanceInfo record) throws Exception {
        if (record.getIdNumber() == null || record.getIdNumber().isEmpty()) {
            throw new MyException("身份证不能为空");
        }
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return financeInfoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("添加客户财务信息出现异常");
        }
    }

    /**
     * 获取客户财务信息
     *
     * @param idNumber
     * @return
     * @throws Exception
     */
    @Override
    public FinanceInfo getFinanceInfoByIdNumber(String idNumber) throws Exception {
        try {
            return financeInfoMapper.getFinanceInfoByIdNumber(idNumber);
        } catch (Exception e) {
            throw new MyException("获取客户财务信息出现异常");
        }
    }

    /**
     * 修改客户财务信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByIdNumberSelective(FinanceInfo record) throws Exception {
        if (record.getIdNumber() == null || record.getIdNumber().isEmpty()) {
            throw new MyException("身份证不能为空");
        }
        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return financeInfoMapper.updateByIdNumberSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("编辑客户财务信息出现异常");
        }
    }
}
