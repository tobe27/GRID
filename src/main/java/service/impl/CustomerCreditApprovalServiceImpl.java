package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CustomerBlackListMapper;
import dao.CustomerCreditApprovalMapper;
import dao.CustomerCreditDetailMapper;
import dao.CustomerGreylistMapper;
import dao.CustomerInfoMapper;
import dao.CustomerWhitelistMapper;
import dao.TagCustomerMapper;
import exception.MyException;
import model.CustomerBlackList;
import model.CustomerCreditApproval;
import model.CustomerCreditDetail;
import model.CustomerGreylist;
import model.CustomerInfo;
import model.CustomerWhitelist;
import model.TagCustomer;
import service.CustomerCreditApprovalService;
import util.ValidUtil;
@Service
public class CustomerCreditApprovalServiceImpl implements CustomerCreditApprovalService {
    @Autowired
    private CustomerCreditApprovalMapper customerCreditApprovalMapper;
    @Autowired
    private CustomerCreditDetailMapper customerCreditDetailMapper;
    @Autowired
    private CustomerWhitelistMapper customerWhitelistMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private CustomerGreylistMapper customerGreylistMapper;
    @Autowired
    private CustomerBlackListMapper customerBlackListMapper;

	
	
	@Override
	public int batchSave(ArrayList<CustomerCreditApproval> list,String roleId) throws Exception {
		
		long now=System.currentTimeMillis();
		int sum=0;
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
				//查询出审批的面谈面签记录
				CustomerCreditDetail record=customerCreditDetailMapper.selectByPrimaryKey(customerCreditApproval.getCreditDetailId());
				if("2".equals(record.getStatus())) {
					continue;
				}
				//验证是否参数齐全
				try {
				CustomerCreditDetailServiceImpl.checkParams(record);
				checkCustomerCreditDetailLogic(record);
				}catch(Exception e){
					continue;
				}
				//如果面谈面签数据状态是 0和4 （待提交和被驳回）
				if("0".equals(record.getApprovalStatus())||"4".equals(record.getApprovalStatus())) {
					if("1".equals(roleId)) {
						record.setApprovalStatus("1").setUpdatedAt(now);
						customerCreditDetailMapper.updateByPrimaryKeySelective(record);
					}
				}
				//如果面谈面签数据状态是 1（客户经理已提交）
				if("1".equals(record.getApprovalStatus())) {
					//如果是会计同意，将面谈面签数据设为3（通过）
					if("2".equals(roleId) && "0".equals(customerCreditApproval.getApprovalResult())) {
						record.setApprovalStatus("3").setUpdatedAt(now);
						customerCreditDetailMapper.updateByPrimaryKeySelective(record);
					}
					//如果是会计驳回，将面谈面签数据设为4（驳回）
					if("2".equals(roleId) && "1".equals(customerCreditApproval.getApprovalResult())) {
						record.setApprovalStatus("4").setUpdatedAt(now);
						customerCreditDetailMapper.updateByPrimaryKeySelective(record);
					}
				}
				
				
				customerCreditApproval.setCreatedAt(now);
				customerCreditApproval.setUpdatedAt(now);
				//savaList.add(customerCreditApproval);
				customerCreditApprovalMapper.insertSelective(customerCreditApproval);
				sum++;
			}	
		}
	
		return sum;
	}
    

    /**
     * 验证新增的面谈面签资料是否符合业务逻辑
     * @param record
     * @return
     * @throws Exception
     */
    public  boolean checkCustomerCreditDetailLogic(CustomerCreditDetail record) throws Exception{
        CustomerInfo customerInfo=customerInfoMapper.getCustomerByIdNumber(record.getIdNumber());
        //检查客户信息是否可用
        if((customerInfo==null) || (ValidUtil.isEmpty(customerInfo.getIdNumber()))||(customerInfo.getStatus()!=5)) {
            throw new MyException("客户库中没有检索到可用的客户信息");
        }
        //验证信息是否已存在于白名单中
        CustomerWhitelist customerWhitelist=new CustomerWhitelist();
        customerWhitelist.setIdNumber(record.getIdNumber());
       if( customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist).isEmpty()) {
    	   throw new MyException("白名单库中未匹配到相关信息");
       }
        
        //验证客户勾选的网格和客户库中的网格是否一致

        if(!customerInfo.getGridCode().equals(record.getGridCode())) {
            throw new MyException("您勾选的网格和客户库中客户所属的网格信息不匹配");
        }
        //验证客户信息是否在黑名单
        CustomerBlackList customerBlackList=new CustomerBlackList();
        customerBlackList.setIdNumber(record.getIdNumber());
        if(customerBlackListMapper.getByIdOrIdnumber(customerBlackList).size()>0) {
            throw new MyException("该客户已存在黑名单记录");
        }
        //验证客户信息是否在灰名单
        CustomerGreylist customerGreylist=new CustomerGreylist();
        customerGreylist.setIdNumber(record.getIdNumber());
        if(customerGreylistMapper.getByIdOrIdnumber(customerGreylist).size()>0) {
            throw new MyException("该客户已存在灰名单记录");
        }
		/*//验证提交的客户信息是否存在还未授信截至的记录
		Map<String,Object> map =new HashMap<>();
		map.put("idNumber",record.getIdNumber());
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        map.put("endAt",dateFormat.format( now ));
		List<CustomerCreditDetail> list=customerCreditDetailMapper.getByIdNumber(map);
		if(list.size()>0) {
			throw new MyException("面谈面签库中已存在授信还未到期的客户资料");
		}*/
        return true;
    }
	
	
	@Override
	public List<CustomerCreditApproval> getListcreditDetailId(CustomerCreditApproval record) throws Exception {
		if(record.getCreditDetailId()==null) {
			 throw new MyException("查询操作异常");
		}
		return customerCreditApprovalMapper.getListcreditDetailId(record);
	}

}
