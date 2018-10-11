package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.CustomerCreditApproval;

public interface CustomerCreditApprovalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerCreditApproval record);

    int insertSelective(CustomerCreditApproval record);

    CustomerCreditApproval selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerCreditApproval record);

    int updateByPrimaryKey(CustomerCreditApproval record);
    int batchSave(@Param("list")List<CustomerCreditApproval> list);
    List<CustomerCreditApproval> getListByIdNumber(CustomerCreditApproval record);
}