package service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.CustomerCreditDetailMapper;
import exception.MyException;
import model.CustomerBlackList;
import model.CustomerCreditDetail;
import service.CustomerCreditDetailService;
@Service
public class CustomerCreditDetailServiceImpl implements CustomerCreditDetailService {
	@Autowired
	private CustomerCreditDetailMapper customerCreditDetailMapper;

	@Override
	public int insertByExcel(List<Map<String,Object>> list,String gridCode) {
		int sum=0;
		for(Map<String,Object> map:list) {
			if(map.get("1")==null||"".equals(map.get("1")) || (map.get("2")==null || "".equals(map.get("2")))){
				continue;
			}
			CustomerCreditDetail customerCreditDetail=new CustomerCreditDetail();
			customerCreditDetail.setCustomerName(map.get("1").toString());
			customerCreditDetail.setIdNumber(map.get("2").toString());
			customerCreditDetail.setGridCode(gridCode);
			if(map.get("3")!=null&&!"".equals(map.get("3").toString())) {
				BigDecimal rental = new BigDecimal(map.get("3").toString()); 
				customerCreditDetail.setRental(rental);
			}
			if(map.get("4")!=null&&!"".equals(map.get("4").toString())) {
				
				customerCreditDetail.setBeginAt(map.get("4").toString());
			}
            if(map.get("5")!=null&&!"".equals(map.get("5").toString())) {
				
				customerCreditDetail.setEndAt(map.get("5").toString());
			}
            if(map.get("6")!=null&&!"".equals(map.get("6").toString())) {
				
				customerCreditDetail.setComment(map.get("6").toString());
			}
           	long now=System.currentTimeMillis();
			List<CustomerCreditDetail> blackList=customerCreditDetailMapper.getByIdOrIdnumber(customerCreditDetail);
			if(blackList.size()>0) {
				customerCreditDetail.setId(blackList.get(0).getId());
				customerCreditDetail.setUpdatedAt(now);
				customerCreditDetailMapper.updateByPrimaryKeySelective(customerCreditDetail);
			}else {
				customerCreditDetail.setCreatedAt(now);
				customerCreditDetail.setUpdatedAt(now);
				customerCreditDetailMapper.insertSelective(customerCreditDetail);
			}
			sum++;
		}
		return sum;
	}
	
	
	
	
	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
	
		if(id==null) {
			throw new MyException("参数有误");
		}
		try {
			return customerCreditDetailMapper.deleteByPrimaryKey(id)==1;
		} catch (Exception e) {
			 throw new MyException("删除授信详情异常");
		}
	}

	@Override
	public boolean insertSelective(CustomerCreditDetail record) throws Exception {
	
		if(record.getIdNumber()==null || "".equals(record.getIdNumber())) {
			throw new MyException("身份证号不能为空");
		}
		if(record.getCustomerName()==null || "".equals(record.getCustomerName())) {
			throw new MyException("姓名不能为空");
		}
		if(record.getGridCode()==null || "".equals(record.getGridCode())) {
			throw new MyException("网格编号不能为空");
		}
		
		
		
		//查询下系统中有没有相同的身份证号的白名单
		if (customerCreditDetailMapper.getByIdOrIdnumber(record).size()>0) {
			throw new MyException("系统中已存在相同的身份证号记录");
		}
		try {
			
			long now=System.currentTimeMillis();
			record.setCreatedAt(now);
			record.setUpdatedAt(now);
			record.setApprovalStatus("0");
			record.setAttachFlag("0");
			return customerCreditDetailMapper.insertSelective(record)==1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("创建授信详情信息异常");
		}
	}

	@Override
	public CustomerCreditDetail selectByPrimaryKey(Long id) throws Exception {
	
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerCreditDetail record) throws Exception {
		
		if(record.getIdNumber()==null || "".equals(record.getIdNumber())) {
			throw new MyException("身份证号不能为空");
		}
		if(record.getCustomerName()==null || "".equals(record.getCustomerName())) {
			throw new MyException("姓名不能为空");
		}
		if(record.getGridCode()==null || "".equals(record.getGridCode())) {
			throw new MyException("网格编号不能为空");
		}
		
		
		if (customerCreditDetailMapper.getByIdOrIdnumber(record).size()>0) {
			throw new MyException("系统中已存在相同的身份证号记录");
		}
		//查询下系统中有没有相同的身份证号的白名单
		try {
			
			record.setUpdatedAt(System.currentTimeMillis());
			return customerCreditDetailMapper.updateByPrimaryKeySelective(record)==1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("修改授信详情信息异常");
		}
	}

	@Override
	public List<CustomerCreditDetail> getListByPage(Map<String, Object> map) throws Exception {
		
		if(!map.containsKey("pageNum")) {
			throw new MyException("查询参数异常");
		}
		if(!map.containsKey("pageSize")) {
			throw new MyException("查询参数异常");
		}
		try {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
			return	customerCreditDetailMapper.getList(map);
		}catch(Exception e) {
			throw new MyException("查询参数异常");
		}
		
	}

}
