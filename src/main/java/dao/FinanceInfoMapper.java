package dao;

import model.FinanceInfo;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface FinanceInfoMapper {
    int deleteByIdNumber(String idNumber);

    int insertSelective(FinanceInfo record);

    FinanceInfo getFinanceInfoByIdNumber(String idNumber);

    int updateByIdNumberSelective(FinanceInfo record);
}