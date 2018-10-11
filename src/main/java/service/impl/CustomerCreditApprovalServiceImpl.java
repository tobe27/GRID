package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CustomerCreditApprovalMapper;
import dao.CustomerCreditDetailMapper;
import exception.MyException;
import model.CustomerCreditApproval;
import model.CustomerCreditDetail;
import service.CustomerCreditApprovalService;
@Service
public class CustomerCreditApprovalServiceImpl implements CustomerCreditApprovalService {
    @Autowired
    private CustomerCreditApprovalMapper customerCreditApprovalMapper;
    @Autowired
    private CustomerCreditDetailMapper customerCreditDetailMapper;
	
	
	@Override
	public int batchSave(ArrayList<CustomerCreditApproval> list,List<Integer> roleIds,ArrayList<CustomerCreditDetail> customerCreditDetailList) throws Exception {
		List<CustomerCreditApproval> savaList=new ArrayList<>();
		if(list !=null && list.size()>0) {
			for(CustomerCreditApproval customerCreditApproval:list) {
				if(customerCreditApproval.getIdNumber()==null || "".equals(customerCreditApproval.getIdNumber())) {
					continue;
				}
				if(customerCreditApproval.getApprovalNode()==null || "".equals(customerCreditApproval.getApprovalNode())) {
					continue;
				}
				if(customerCreditApproval.getApprovalResult()==null || "".equals(customerCreditApproval.getApprovalResult())) {
					continue;
				}
				if(customerCreditApproval.getApprovalAccountId()==null ) {
					continue;
				}
				if(customerCreditApproval.getApprovalName()==null || "".equals(customerCreditApproval.getApprovalName())) {
					continue;
				}
				long now=System.currentTimeMillis();
				customerCreditApproval.setCreatedAt(now);
				customerCreditApproval.setUpdatedAt(now);
				savaList.add(customerCreditApproval);
			}	
		}
		//根据roleid判断审批的人
		
		for(CustomerCreditDetail customerCreditDetail:customerCreditDetailList) {
			//如果审批数据状态为0 并且审批人角色id包含客户经理
			if(customerCreditDetail.getApprovalStatus().equals("0") && roleIds.contains(1)) {
				//审批意见为通过
				if(list.get(0).getApprovalResult().equals("0")) {
					customerCreditDetail.setApprovalStatus("1");
				}else {
					customerCreditDetail.setApprovalStatus("2");
				}
				customerCreditDetailMapper.updateApprovalStatus(customerCreditDetail);
				continue;
			}
			
			//如果审批数据状态为1并且审批角色为会计
			if(customerCreditDetail.getApprovalStatus().equals("1")&& roleIds.contains(2)) {
				//审批意见为通过
				if(list.get(0).getApprovalResult().equals("0")) {
					customerCreditDetail.setApprovalStatus("3");
				}else {
					customerCreditDetail.setApprovalStatus("4");
				}
				customerCreditDetailMapper.updateApprovalStatus(customerCreditDetail);
				continue;
			}
			//如果审批数据状态为2并且审批角色为支行长
			if(customerCreditDetail.getApprovalStatus().equals("2")&& roleIds.contains(3)) {
				if(list.get(0).getApprovalResult().equals("0")) {
					customerCreditDetail.setApprovalStatus("5");
				}else {
					customerCreditDetail.setApprovalStatus("6");
				}
				customerCreditDetailMapper.updateApprovalStatus(customerCreditDetail);
				continue;
			}
		}
		
		try {
			if(savaList.size()>0) {
				customerCreditApprovalMapper.batchSave(savaList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			 throw new MyException("审批操作异常");
			
		}
		return savaList.size();
	}

	@Override
	public List<CustomerCreditApproval> getListByIdNumber(CustomerCreditApproval record) throws Exception {
		if(record.getIdNumber()==null || "".equals(record.getIdNumber())) {
			 throw new MyException("查询操作异常");
		}
		return customerCreditApprovalMapper.getListByIdNumber(record);
	}

}
