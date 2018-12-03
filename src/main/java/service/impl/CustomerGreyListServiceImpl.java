package service.impl;

import java.util.ArrayList;
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
import model.GridInfo;
import model.TagCustomer;
import service.CustomerGreyListService;
@Service
public class CustomerGreyListServiceImpl implements CustomerGreyListService {
	@Autowired
	private CustomerGreylistMapper customerGreylistMapper;
	@Autowired
	private CustomerWhitelistMapper customerWhitelistMapper;
	@Autowired
	private CustomerBlackListMapper customerBlackListMapper;
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
			CustomerGreylist customerGreylist=new CustomerGreylist();
			customerGreylist.setCustomerName(map.get("1").toString());
			customerGreylist.setIdNumber(map.get("2").toString());
			customerGreylist.setGridCode(gridCode);
			if(map.get("3")!=null&&!"".equals(map.get("3").toString())) {
				customerGreylist.setReason(map.get("3").toString());
			}
			long now=System.currentTimeMillis();
			
			
			//查看信息是否存在于客户库中
			CustomerInfo customer= customerInfoMapper.getCustomerByIdNumber(customerGreylist.getIdNumber());
			if(customer==null ||customer.getIdNumber()==null ||(customer.getStatus()!=5)) {
				/*throw new MyException("客户库中未建立相关客户信息");*/
				failCount++;
				continue;
			}
			if(!customer.getGridCode().equals(customerGreylist.getGridCode())) {
				failCount++;
				continue;
			}
			//查看信息是否存在于白名单中
			CustomerWhitelist customerWhitelist=new CustomerWhitelist();
			customerWhitelist.setIdNumber(customerGreylist.getIdNumber());
			if (customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist).size()>0) {
				/*throw new MyException("该客户已存在白名单记录");*/
				failCount++;
				continue;
			}
			//查看信息是否存在于黑名单中
			CustomerBlackList customerBlackList=new CustomerBlackList();
			customerBlackList.setIdNumber(customerGreylist.getIdNumber());
			if(customerBlackListMapper.getByIdOrIdnumber(customerBlackList).size()>0) {
				/*throw new MyException("该客户已存在黑名单记录");*/
				failCount++;
				continue;
			}
			
			List<CustomerGreylist>  greylist=customerGreylistMapper.getByIdOrIdnumber(customerGreylist);
			if(greylist.size()>0) {
				/*customerGreylist.setId(greylist.get(0).getId());
				customerGreylist.setUpdatedAt(now);
				customerGreylistMapper.updateByPrimaryKeySelective(customerGreylist);*/
				failCount++;
				continue;
			}else {
				customerGreylist.setCreatedAt(now);
				customerGreylist.setUpdatedAt(now);
				customerGreylistMapper.insertSelective(customerGreylist);
				//将灰名单标签插入客户标签库
				TagCustomer tagCustomer=new TagCustomer();
				tagCustomer.setTagId((long)3);
				tagCustomer.setIdNumber(customerGreylist.getIdNumber());
				tagCustomer.setTagName("灰名单");
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
			CustomerGreylist customerGreylist=new CustomerGreylist();
			customerGreylist=customerGreylistMapper.selectByPrimaryKey(id);
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setIdNumber(customerGreylist.getIdNumber());
			tagCustomer.setTagId((long)3);
			tagCustomerMapper.deleteTagByIdNumberAndTagId(tagCustomer);
			
			
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
			CustomerInfo customer= customerInfoMapper.getCustomerByIdNumber(record.getIdNumber());
			if(customer==null ||customer.getIdNumber()==null ||(customer.getStatus()!=5)) {
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
			CustomerBlackList customerBlackList=new CustomerBlackList();
			customerBlackList.setIdNumber(record.getIdNumber());
			if(customerBlackListMapper.getByIdOrIdnumber(customerBlackList).size()>0) {
				throw new MyException("该客户已存在黑名单记录");
			}
			if (customerGreylistMapper.getByIdOrIdnumber(record).size()>0) {
				throw new MyException("该客户已存在灰名单记录");
			}
			long now=System.currentTimeMillis();
			//将灰名单标签插入客户标签库
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setTagId((long)3);
			tagCustomer.setIdNumber(record.getIdNumber());
			tagCustomer.setTagName("灰名单");
			tagCustomer.setCreatedAt(now);
			tagCustomer.setUpdatedAt(now);
			tagCustomerMapper.insertSelective(tagCustomer);
			
			
			record.setCreatedAt(now);
			record.setUpdatedAt(now);
			return customerGreylistMapper.insertSelective(record)==1;
		} catch (Exception e) {
			throw new MyException(e.getMessage());
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
			throw new MyException(e.getMessage());
		}
	}

	@Override
	public List<Map<String, Object>> getCustomerGreylistByPage(Map<String, Object> map) {
		 List<Integer> rolelist=(List<Integer>)map.get("rolelist");
			//根据登录的账号和角色来查出能查看的网格编号,如果是客户经理登录
			List<String> gridCodeList=new ArrayList<>();
			GridInfo gridInfo=new GridInfo();
			if("1".equals(rolelist.get(0)+"")) {
				gridInfo.setAccountId(Long.parseLong(map.get("accountId").toString()));
				gridCodeList=gridInfoMapper.getGridCodesByAccountIdOrOrgCode(gridInfo);
			}else {
				gridInfo.setOrgCode(Long.parseLong(map.get("orgCode").toString()));
				gridCodeList=gridInfoMapper.getGridCodesByAccountIdOrOrgCode(gridInfo);
			}
			if(gridCodeList.isEmpty()) {
				throw new MyException("未检索到登录人可查看的网格数据信息");
			}
			map.put("gridCodeList", gridCodeList);
		
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
			throw new MyException(e.getMessage());
		}
	}

}
