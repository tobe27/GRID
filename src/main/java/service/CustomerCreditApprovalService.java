package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.CustomerCreditApproval;
import model.CustomerCreditDetail;

public interface CustomerCreditApprovalService {
	 int batchSave(@Param("list")ArrayList<CustomerCreditApproval> list,List<Integer> roleIds,ArrayList<CustomerCreditDetail> customerCreditDetailList) throws Exception;
	    List<CustomerCreditApproval> getListByIdNumber(CustomerCreditApproval record)throws Exception;
}
