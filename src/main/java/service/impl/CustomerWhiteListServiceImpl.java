package service.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.CustomerWhitelistMapper;
import exception.MyException;
import model.CustomerWhitelist;
import service.CustomerWhiteListService;
@Service
public class CustomerWhiteListServiceImpl implements CustomerWhiteListService {
	@Autowired
	private CustomerWhitelistMapper customerWhitelistMapper;
	
	@Override
	public int insertByExcel(List<Map<String,Object>> list,String gridCode) throws Exception {
		int sum=0;
		for(Map<String,Object> map:list) {
			if(map.get("1")==null||"".equals(map.get("1")) || (map.get("2")==null || "".equals(map.get("2")))){
				continue;
			}
			CustomerWhitelist customerWhitelist=new CustomerWhitelist();
			customerWhitelist.setCustomerName(map.get("1").toString());
			customerWhitelist.setIdNumber(map.get("2").toString());
			customerWhitelist.setGridCode(gridCode);
			if(map.get("3")!=null&&!"".equals(map.get("3").toString())) {
				customerWhitelist.setPhoneNumber((long)Double.parseDouble(map.get("3").toString()));
				
			}
			long now=System.currentTimeMillis();
			List<CustomerWhitelist> whitelist=customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist);
			if(whitelist.size()>0) {
				customerWhitelist.setUpdatedAt(now);
				customerWhitelist.setId(whitelist.get(0).getId());
				customerWhitelistMapper.updateByPrimaryKeySelective(customerWhitelist);
			}else {
				customerWhitelist.setCreatedAt(now);
				customerWhitelist.setUpdatedAt(now);
				customerWhitelistMapper.insertSelective(customerWhitelist);
			}
			sum++;
			
		}
		return sum;
	}
	public static boolean isValidLong(String str){
		   try{
		       long _v = Long.parseLong(str);
		       return true;
		   }catch(NumberFormatException e){
		     return false;
		   }
		}
	
	

	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		if(id==null) {
			throw new MyException("参数有误");
		}
		try {
			return customerWhitelistMapper.deleteByPrimaryKey(id)==1;
		} catch (Exception e) {
			 throw new MyException("删除白名单异常");
		}
		
	}

	

	@Override
	public boolean insertSelective(CustomerWhitelist record) throws Exception {
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
			if (customerWhitelistMapper.getByIdOrIdnumber(record).size()>0) {
				throw new MyException("系统中已存在相同的身份证号记录");
			}
			long now=System.currentTimeMillis();
			record.setCreatedAt(now);
			record.setUpdatedAt(now);
			return customerWhitelistMapper.insertSelective(record)==1;
		} catch (Exception e) {
			throw new MyException("创建白名单异常");
		}
	}

	@Override
	public CustomerWhitelist selectByPrimaryKey(Long id) throws Exception {
		
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerWhitelist record) throws Exception {
		
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
			if (customerWhitelistMapper.getByIdOrIdnumber(record).size()>0) {
				throw new MyException("系统中已存在相同的身份证号记录");
			}
			record.setUpdatedAt(System.currentTimeMillis());
			return customerWhitelistMapper.updateByPrimaryKeySelective(record)==1;
		} catch (Exception e) {
			throw new MyException("修改白名单异常");
		}
	}



	@Override
	public List<CustomerWhitelist> getCustomerWhiteListByPage(Map<String,Object> map) {
		
		if(!map.containsKey("pageNum")) {
			throw new MyException("查询参数异常");
		}
		if(!map.containsKey("pageSize")) {
			throw new MyException("查询参数异常");
		}
		try {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
			return	customerWhitelistMapper.getWhiteLists(map);
		}catch(Exception e) {
			throw new MyException("查询参数异常");
		}
		
	}

	
	
}
