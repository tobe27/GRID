package dao;

import java.util.List;
import java.util.Map;

import model.CreditReportAccount;

public interface CreditReportAccountMapper {
    int deleteByPrimaryKey(Long id);
    int insertSelective(CreditReportAccount record);

    CreditReportAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditReportAccount record);
    List<CreditReportAccount> getList(Map<String,Object> map);
    List<CreditReportAccount> getListByUserNameOrId(CreditReportAccount record);
 
}