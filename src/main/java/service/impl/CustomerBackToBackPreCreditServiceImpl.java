package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.CustomerBackToBackPreCreditMapper;
import exception.MyException;
import model.CustomerBackToBackPreCredit;
import service.CustomerBlackToBackPreCreditService;
@Service
public class CustomerBackToBackPreCreditServiceImpl implements CustomerBlackToBackPreCreditService{
	@Autowired
	private CustomerBackToBackPreCreditMapper customerBackToBackPreCreditMapper;

	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {

		if(id==null) {
			throw new MyException("参数有误");
		}
		try {
			return customerBackToBackPreCreditMapper.deleteByPrimaryKey(id)==1;
		} catch (Exception e) {
			 throw new MyException("删除预授信信息异常");
		}
	}

	@Override
	public boolean insertSelective(CustomerBackToBackPreCredit record) throws Exception {
	
		if(record.getIdNumber()==null || "".equals(record.getIdNumber())) {
			throw new MyException("身份证号不能为空");
		}
		if(record.getCustomerName()==null || "".equals(record.getCustomerName())) {
			throw new MyException("姓名不能为空");
		}
		if(record.getGridCode()==null || "".equals(record.getGridCode())) {
			throw new MyException("网格编号不能为空");
		}
		
		if(record.getRental()==null || "".equals(record.getRental())) {
			throw new MyException("授信额度不能为空");
		}
		
		//查询下系统中有没有相同的身份证号的白名单
		if (customerBackToBackPreCreditMapper.getByIdOrIdnumber(record).size()>0) {
			throw new MyException("系统中已存在相同的身份证号记录");
		}
		try {
			
			long now=System.currentTimeMillis();
			record.setCreatedAt(now);
			record.setUpdatedAt(now);
			return customerBackToBackPreCreditMapper.insertSelective(record)==1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("创建预授信信息异常");
		}
	}

	@Override
	public CustomerBackToBackPreCredit selectByPrimaryKey(Long id) throws Exception {
	
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerBackToBackPreCredit record) throws Exception {
		if(record.getIdNumber()==null || "".equals(record.getIdNumber())) {
			throw new MyException("身份证号不能为空");
		}
		if(record.getCustomerName()==null || "".equals(record.getCustomerName())) {
			throw new MyException("姓名不能为空");
		}
		if(record.getGridCode()==null || "".equals(record.getGridCode())) {
			throw new MyException("网格编号不能为空");
		}
		if(record.getRental()==null || "".equals(record.getRental())) {
			throw new MyException("授信额度不能为空");
		}
		
		if (customerBackToBackPreCreditMapper.getByIdOrIdnumber(record).size()>0) {
			throw new MyException("系统中已存在相同的身份证号记录");
		}
		//查询下系统中有没有相同的身份证号的白名单
		try {
			
			record.setUpdatedAt(System.currentTimeMillis());
			return customerBackToBackPreCreditMapper.updateByPrimaryKeySelective(record)==1;
		} catch (Exception e) {
			throw new MyException("修改预授信信息异常");
		}
	}

	@Override
	public List<CustomerBackToBackPreCredit> getListByPage(Map<String, Object> map) throws Exception {
		
		if(!map.containsKey("pageNum")) {
			throw new MyException("查询参数异常");
		}
		if(!map.containsKey("pageSize")) {
			throw new MyException("查询参数异常");
		}
		try {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
			return	customerBackToBackPreCreditMapper.getBackToBackPreCreditList(map);
		}catch(Exception e) {
			throw new MyException("查询参数异常");
		}
		
	}
  
	
}
