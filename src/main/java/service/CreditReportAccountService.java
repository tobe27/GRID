package service;

import java.util.List;
import java.util.Map;

import model.CreditReportAccount;

public interface CreditReportAccountService {
	    boolean deleteByPrimaryKey(Long id) throws Exception;
	    boolean insertSelective(CreditReportAccount record)throws Exception;
	    CreditReportAccount selectByPrimaryKey(Long id)throws Exception;
	    boolean updateByPrimaryKeySelective(CreditReportAccount record)throws Exception;
	    List<CreditReportAccount> getList(Map<String,Object> map)throws Exception;
}
