package service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.CustomerCreditAdviserMapper;
import exception.MyException;
import model.CustomerBlackList;
import model.CustomerCreditAdviser;
import service.CustomerCreditAdviserService;
@Service
public class CustomerCreditAdviserServiceImpl implements CustomerCreditAdviserService {
	@Autowired
	private CustomerCreditAdviserMapper customerCreditAdviserMapper;

	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		if(id!=null) {
			CustomerCreditAdviser record=customerCreditAdviserMapper.selectByPrimaryKey(id);
			record.setStatus("1");
			record.setUpdatedAt(System.currentTimeMillis());
			return customerCreditAdviserMapper.updateByPrimaryKeySelective(record)==1;
		}
		return false;
		
	}
   
	public boolean checkParams(CustomerCreditAdviser record) throws Exception{
		if(record.getCustomerName()==null ||"".equals(record.getCustomerName())) {
			throw new  MyException("客户姓名不能为空");
		}
		if(record.getIdNumber()==null ||"".equals(record.getIdNumber())) {
			throw new  MyException("客户身份证号不能为空");
		}
		if(record.getPhone()==null ||"".equals(record.getPhone())) {
			throw new  MyException("客户电话号码不能为空");
		}
		if(record.getGridCode()==null ||"".equals(record.getGridCode())) {
			throw new  MyException("客户网格编号不能为空");
		}
		if(record.getSigningTime()==null ||"".equals(record.getSigningTime())) {
			throw new  MyException("签字日期不能为空");
		}
		if(record.getAdviserName()==null ||"".equals(record.getAdviserName())) {
			throw new  MyException("金融顾问姓名不能为空");
		}
		if(record.getAddress()==null ||"".equals(record.getAddress())) {
			throw new  MyException("客户地址不能为空");
		}
		return true;
	}
	
	
	
	@Override
	public boolean insertSelective(CustomerCreditAdviser record) throws Exception {
		if(checkParams(record)) {
			long now=System.currentTimeMillis();
			record.setStatus("0");
			record.setCredtedAt(now);
			record.setUpdatedAt(now);
			return customerCreditAdviserMapper.insertSelective(record)==1;
		}
		return false;
	}

	@Override
	public CustomerCreditAdviser selectByPrimaryKey(Long id) throws Exception {
		if(id==null) {
			throw new  MyException("参数有误");
		}
		return customerCreditAdviserMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerCreditAdviser record) throws Exception {
		if(record.getId()==null) {
			throw new  MyException("参数有误");
		}
		if(checkParams(record)) {
			record.setUpdatedAt(System.currentTimeMillis());
			return customerCreditAdviserMapper.updateByPrimaryKeySelective(record)==1;
		}
		return false;
	}

	@Override
	public List<CustomerCreditAdviser> getCustomerCreditAdviserist(Map<String,Object> map) throws Exception {
		if(!map.containsKey("pageNum")) {
			throw new MyException("查询参数异常");
		}
		if(!map.containsKey("pageSize")) {
			throw new MyException("查询参数异常");
		}
		try {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
			return	customerCreditAdviserMapper.getCustomerCreditAdviserList(map);
		}catch(Exception e) {
			throw new MyException("查询参数异常");
		}
		
	}

	@Override
	public int insertByExcel(List<Map<String, Object>> list, String gridCode) throws Exception {
		int sum=0;
		long now=System.currentTimeMillis();
		for(Map<String,Object> map:list) {
			if(map.get("1")==null||"".equals(map.get("1")) || (map.get("2")==null || "".equals(map.get("2")))|| (map.get("9")==null || "".equals(map.get("9"))) || (map.get("5")==null || "".equals(map.get("5"))) || (map.get("6")==null || "".equals(map.get("6"))) || (map.get("7")==null || "".equals(map.get("7")))){
				continue;
			}
			CustomerCreditAdviser customerCreditAdviser=new CustomerCreditAdviser();
			customerCreditAdviser.setCustomerName(map.get("1").toString());
			customerCreditAdviser.setIdNumber(map.get("2").toString());
			customerCreditAdviser.setPhone(map.get("3").toString());
			customerCreditAdviser.setAddress(map.get("4").toString());
			
			 BigDecimal bdIncome=new BigDecimal(map.get("5").toString());
			customerCreditAdviser.setIncome(bdIncome);
			
			BigDecimal bdExpenditure=new BigDecimal(map.get("6").toString());
			customerCreditAdviser.setExpenditure(bdExpenditure);
			
			BigDecimal bdRental=new BigDecimal(map.get("7").toString());
			customerCreditAdviser.setRental(bdRental);
			customerCreditAdviser.setGridCode(gridCode);
			customerCreditAdviser.setSigningTime(map.get("8").toString());
			customerCreditAdviser.setAdviserName(map.get("9").toString());
			if(map.get("10")!=null) {
				customerCreditAdviser.setComment(map.get("10").toString());
			}
			customerCreditAdviser.setCredtedAt(now);
			customerCreditAdviser.setUpdatedAt(now);
			customerCreditAdviserMapper.insertSelective(customerCreditAdviser);
			sum++;
			
		}
		return sum;
		
	}

}
