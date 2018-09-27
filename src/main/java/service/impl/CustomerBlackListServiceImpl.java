package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.CustomerBlackListMapper;
import exception.MyException;
import model.CustomerBlackList;
import model.CustomerWhitelist;
import service.CustomerBlackListService;
@Service
public class CustomerBlackListServiceImpl implements CustomerBlackListService {
	@Autowired
	private CustomerBlackListMapper customerBlackListMapper;
	
	@Override
	public int insertByExcel(List<Map<String,Object>> list,String gridCode) {
		int sum=0;
		for(Map<String,Object> map:list) {
			if(map.get("1")==null||"".equals(map.get("1")) || (map.get("2")==null || "".equals(map.get("2")))){
				continue;
			}
			CustomerBlackList customerBlackList=new CustomerBlackList();
			customerBlackList.setCustomerName(map.get("1").toString());
			customerBlackList.setIdNumber(map.get("2").toString());
			customerBlackList.setGridCode(gridCode);
			if(map.get("3")!=null&&!"".equals(map.get("3").toString())) {
				customerBlackList.setReason(map.get("3").toString());
			}
			long now=System.currentTimeMillis();
			List<CustomerBlackList> blackList=customerBlackListMapper.getByIdOrIdnumber(customerBlackList);
			if(blackList.size()>0) {
				customerBlackList.setId(blackList.get(0).getId());
				customerBlackList.setUpdatedAt(now);
				customerBlackListMapper.updateByPrimaryKeySelective(customerBlackList);
			}else {
				customerBlackList.setCreatedAt(now);
				customerBlackList.setUpdatedAt(now);
				customerBlackListMapper.insertSelective(customerBlackList);
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
			return customerBlackListMapper.deleteByPrimaryKey(id)==1;
		} catch (Exception e) {
			 throw new MyException("删除黑名单异常");
		}
	}

	@Override
	public boolean insertSelective(CustomerBlackList record) throws Exception {
		
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
		if (customerBlackListMapper.getByIdOrIdnumber(record).size()>0) {
			throw new MyException("系统中已存在相同的身份证号记录");
		}
		try {
			
			long now=System.currentTimeMillis();
			record.setCreatedAt(now);
			record.setUpdatedAt(now);
			return customerBlackListMapper.insertSelective(record)==1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("创建黑名单异常");
		}
	}

	@Override
	public CustomerBlackList selectByPrimaryKey(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerBlackList record) throws Exception {
	
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
		try {
			if (customerBlackListMapper.getByIdOrIdnumber(record).size()>0) {
				throw new MyException("系统中已存在相同的身份证号记录");
			}
			record.setUpdatedAt(System.currentTimeMillis());
			return customerBlackListMapper.updateByPrimaryKeySelective(record)==1;
		} catch (Exception e) {
			throw new MyException("修改黑名单异常");
		}
	}

	@Override
	public List<CustomerBlackList> getCustomerBlackListByPage(Map<String, Object> map) {
		
		if(!map.containsKey("pageNum")) {
			throw new MyException("查询参数异常");
		}
		if(!map.containsKey("pageSize")) {
			throw new MyException("查询参数异常");
		}
		try {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
			return	customerBlackListMapper.getBlackLists(map);
		}catch(Exception e) {
			throw new MyException("查询参数异常");
		}
		
	}

}
