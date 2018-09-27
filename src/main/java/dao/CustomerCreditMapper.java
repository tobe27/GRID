package dao;

import model.CustomerCredit;
import service.CustomerCreditService;

public interface CustomerCreditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerCredit record);

    int insertSelective(CustomerCredit record);

    CustomerCredit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerCredit record);

    int updateByPrimaryKey(CustomerCredit record);
}