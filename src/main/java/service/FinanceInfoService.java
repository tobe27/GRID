package service;

import model.FinanceInfo;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface FinanceInfoService {
    /**
     * 删除客户财务信息
     * @param idNumber
     * @return
     * @throws Exception
     */
    boolean deleteByIdNumber(String idNumber) throws Exception;

    /**
     * 添加客户财务信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(FinanceInfo record) throws Exception;

    /**
     * 获取客户财务信息
     * @param idNumber
     * @return
     * @throws Exception
     */
    FinanceInfo getFinanceInfoByIdNumber(String idNumber) throws Exception;

    /**
     * 修改客户财务信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByIdNumberSelective(FinanceInfo record) throws Exception;
}
