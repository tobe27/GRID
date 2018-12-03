package service.impl;

import dao.FinanceInfoMapper;
import exception.MyException;
import model.FinanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FinanceInfoService;
import util.ValidUtil;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class FinanceInfoServiceImpl implements FinanceInfoService {
    @Autowired
    FinanceInfoMapper financeInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除客户资产信息
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
            logger.info("删除资产异常："+e.getMessage());
            throw new MyException("删除资产信息出现异常!");
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
        if (!ValidUtil.isLength(record.getIdNumber(),18)) {
            throw new MyException("请输入18位身份证号");
        }
        if (financeInfoMapper.getFinanceInfoByIdNumber(record.getIdNumber()) != null) {
            throw new MyException("已存在该客户资产信息,请勿重复添加");
        }
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return financeInfoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            logger.info("新建资产异常："+e.getMessage());
            throw new MyException("新建资产信息出现异常!");
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
            logger.info("查询资产异常："+e.getMessage());
            throw new MyException("查询资产信息出现异常!");
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
        if (!ValidUtil.isLength(record.getIdNumber(),18)) {
            throw new MyException("请输入18位身份证号");
        }
        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return financeInfoMapper.updateByIdNumberSelective(record) == 1;
        } catch (Exception e) {
            if (financeInfoMapper.getFinanceInfoByIdNumber(record.getIdNumber()) != null) {
                throw new MyException("已存在该客户资产信息，请勿重复添加");
            }
            logger.info("编辑资产异常："+e.getMessage());
            throw new MyException("编辑资产信息出现异常!");
        }
    }
}
