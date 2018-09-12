package dao;

import model.FinanceInfo;

public interface FinanceInfoMapper {
    int deleteByIdNumber(String idNumber);

    int insertSelective(FinanceInfo record);

    FinanceInfo getFinanceInfoByIdNumber(String idNumber);

    int updateByIdNumberSelective(FinanceInfo record);
}