package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.CustomerGreylistMapper;
import exception.MyException;
import model.CustomerBlackList;
import model.CustomerGreylist;
import service.CustomerGreyListService;
@Service
public class CustomerGreyListServiceImpl implements CustomerGreyListService {
	@Autowired
	private CustomerGreylistMapper customerGreylistMapper;
	
	@Override
	public int insertByExcel(List<Map<String,Object>> list,String gridCode) {
		int sum=0;
		for(Map<String,Object> map:list) {
			if(map.get("1")==null||"".equals(map.get("1")) || (map.get("2")==null || "".equals(map.get("2")))){
				continue;
			}
			CustomerGreylist customerGreylist=new CustomerGreylist();
			customerGreylist.setCustomerName(map.get("1").toString());
			customerGreylist.setIdNumber(map.get("2").toString());
			customerGreylist.setGridCode(gridCode);
			if(map.get("3")!=null&&!"".equals(map.get("3").toString())) {
				customerGreylist.setReason(map.get("3").toString());
			}
			long now=System.currentTimeMillis();
			List<CustomerGreylist>  greylist=customerGreylistMapper.getByIdOrIdnumber(customerGreylist);
			if(greylist.size()>0) {
				customerGreylist.setId(greylist.get(0).getId());
				customerGreylist.setUpdatedAt(now);
				customerGreylistMapper.updateByPrimaryKeySelective(customerGreylist);
			}else {
				customerGreylist.setCreatedAt(now);
				customerGreylist.setUpdatedAt(now);
				customerGreylistMapper.insertSelective(customerGreylist);
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
			return customerGreylistMapper.deleteByPrimaryKey(id)==1;
		} catch (Exception e) {
			 throw new MyException("删除黑名单异常");
		}
		
	}

	@Override
	public boolean insertSelective(CustomerGreylist record) throws Exception {
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
			if (customerGreylistMapper.getByIdOrIdnumber(record).size()>0) {
				throw new MyException("系统中已存在相同的身份证号记录");
			}
			long now=System.currentTimeMillis();
			record.setCreatedAt(now);
			record.setUpdatedAt(now);
			return customerGreylistMapper.insertSelective(record)==1;
		} catch (Exception e) {
			throw new MyException("创建黑名单异常");
		}
		
	}

	@Override
	public CustomerGreylist selectByPrimaryKey(Long id) throws Exception {
		
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerGreylist record) throws Exception {
	

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
			if (customerGreylistMapper.getByIdOrIdnumber(record).size()>0) {
				throw new MyException("系统中已存在相同的身份证号记录");
			}
			record.setUpdatedAt(System.currentTimeMillis());
			return customerGreylistMapper.updateByPrimaryKeySelective(record)==1;
		} catch (Exception e) {
			throw new MyException("修改黑名单异常");
		}
	}

	@Override
	public List<CustomerGreylist> getCustomerGreylistByPage(Map<String, Object> map) {
		if(!map.containsKey("pageNum")) {
			throw new MyException("查询参数异常");
		}
		if(!map.containsKey("pageSize")) {
			throw new MyException("查询参数异常");
		}
		try {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
			return	customerGreylistMapper.getGreyLists(map);
		}catch(Exception e) {
			throw new MyException("查询参数异常");
		}
	}

}
