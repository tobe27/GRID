package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.CustomerBlackListMapper;
import dao.CustomerGreylistMapper;
import dao.CustomerInfoMapper;
import dao.CustomerWhitelistMapper;
import dao.GridInfoMapper;
import dao.TagCustomerMapper;
import exception.MyException;
import model.CustomerBlackList;
import model.CustomerGreylist;
import model.CustomerInfo;
import model.CustomerWhitelist;
import model.TagCustomer;
import service.CustomerBlackListService;
@Service
public class CustomerBlackListServiceImpl implements CustomerBlackListService {
	@Autowired
	private CustomerBlackListMapper customerBlackListMapper;
	@Autowired
	private CustomerGreylistMapper customerGreylistMapper;
	@Autowired
	private CustomerWhitelistMapper customerWhitelistMapper;

	@Autowired
	private TagCustomerMapper tagCustomerMapper;
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	@Autowired
	private GridInfoMapper gridInfoMapper;
	
	@Override
	public Map<String,Object> insertByExcel(List<Map<String,Object>> list,String gridCode) {
		int successCount=0;
		int failCount=0;
		for(Map<String,Object> map:list) {
			if(map.get("1")==null||"".equals(map.get("1")) || (map.get("2")==null || "".equals(map.get("2")))){
				failCount++;
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
			//查看信息是否存在于客户库中
			CustomerInfo customer= customerInfoMapper.getCustomerByIdNumber(customerBlackList.getIdNumber());
			if(customer==null ||customer.getIdNumber()==null ||(customer.getStatus()!=5)) {
				/*throw new MyException("客户库中未建立相关客户信息");*/
				failCount++;
				continue;
			}
			if(!customer.getGridCode().equals(customerBlackList.getGridCode())) {
				failCount++;
				continue;
			}
			
			//查看信息是否存在于白名单中
			CustomerWhitelist customerWhitelist=new CustomerWhitelist();
			customerWhitelist.setIdNumber(customerBlackList.getIdNumber());
			if (customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist).size()>0) {
				/*throw new MyException("该客户已存在白名单记录");*/
				failCount++;
				continue;
			}
			
			//查看信息是否存在于灰名单中
			
			CustomerGreylist customerGreylist=new CustomerGreylist();
			customerGreylist.setIdNumber(customerBlackList.getIdNumber());
			if(customerGreylistMapper.getByIdOrIdnumber(customerGreylist).size()>0) {
			/*	throw new MyException("该客户已存在灰名单记录");*/
				failCount++;
				continue;
			}
			
			
			//查看信息是否存在于黑名单中
			List<CustomerBlackList> blackList=customerBlackListMapper.getByIdOrIdnumber(customerBlackList);
			if(blackList.size()>0) {
				/*customerBlackList.setId(blackList.get(0).getId());
				customerBlackList.setUpdatedAt(now);
				customerBlackListMapper.updateByPrimaryKeySelective(customerBlackList);*/
				failCount++;
				continue;
			}else {
				customerBlackList.setCreatedAt(now);
				customerBlackList.setUpdatedAt(now);
				customerBlackListMapper.insertSelective(customerBlackList);
				//将黑名单标签插入客户标签库
				TagCustomer tagCustomer=new TagCustomer();
				tagCustomer.setTagId((long)1);
				tagCustomer.setIdNumber(customerBlackList.getIdNumber());
				tagCustomer.setTagName("黑名单");
				tagCustomer.setCreatedAt(now);
				tagCustomer.setUpdatedAt(now);
				tagCustomerMapper.insertSelective(tagCustomer);
				
			}
			successCount++;
			
		}
		Map<String,Object> map=new HashMap<>();
		map.put("failCount", failCount);
		map.put("successCount", successCount);
		return map;
	}
	
	

	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		if(id==null) {
			throw new MyException("参数有误");
		}
		try {
			CustomerBlackList customerBlackList=new CustomerBlackList();
			customerBlackList=customerBlackListMapper.selectByPrimaryKey(id);
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setIdNumber(customerBlackList.getIdNumber());
			tagCustomer.setTagId((long)1);
			tagCustomerMapper.deleteTagByIdNumberAndTagId(tagCustomer);
			
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
		
		
		
		CustomerInfo customer= customerInfoMapper.getCustomerByIdNumber(record.getIdNumber());
		if(customer==null || customer.getIdNumber() == null ||(customer.getStatus() != 5)) {
			throw new MyException("客户库中未找到该客户信息，请先新建该客户");
		}
		if(!customer.getGridCode().equals(record.getGridCode())) {
			//查询出网格的名称
			Map<String,Object> map=new HashMap<>();
			map.put("gridCode", customer.getGridCode());
			List<Map<String,Object>>list=gridInfoMapper.getGridInfoList(map);
			throw new MyException("您选择的客户属于网格："+list.get(0).get("gridName").toString()+"，请重新选择");
		}
		
		CustomerWhitelist customerWhitelist=new CustomerWhitelist();
		customerWhitelist.setIdNumber(record.getIdNumber());
		if (customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist).size()>0) {
			throw new MyException("该客户已存在白名单记录");
		}
		CustomerGreylist customerGreylist=new CustomerGreylist();
		customerGreylist.setIdNumber(record.getIdNumber());
		if(customerGreylistMapper.getByIdOrIdnumber(customerGreylist).size()>0) {
			throw new MyException("该客户已存在灰名单记录");
		}
		//查询下系统中有没有相同的身份证号的黑名单
		if (customerBlackListMapper.getByIdOrIdnumber(record).size()>0) {
			throw new MyException("该客户已存在黑名单记录");
		}
		try {
			
			long now=System.currentTimeMillis();
			
			
			//将黑名单标签插入客户标签库
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setTagId((long)1);
			tagCustomer.setIdNumber(record.getIdNumber());
			tagCustomer.setTagName("黑名单");
			tagCustomer.setCreatedAt(now);
			tagCustomer.setUpdatedAt(now);
			tagCustomerMapper.insertSelective(tagCustomer);
			
			
			record.setCreatedAt(now);
			record.setUpdatedAt(now);
			return customerBlackListMapper.insertSelective(record)==1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
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
			throw new MyException(e.getMessage());
		}	
	}

	@Override
	public List<Map<String,Object>> getCustomerBlackListByPage(Map<String, Object> map) {
		
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
