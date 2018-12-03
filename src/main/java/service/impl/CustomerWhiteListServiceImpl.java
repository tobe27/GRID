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
import service.CustomerWhiteListService;
import util.JwtUtil;
@Service
public class CustomerWhiteListServiceImpl implements CustomerWhiteListService {
	@Autowired
	private CustomerWhitelistMapper customerWhitelistMapper;
	@Autowired
	private CustomerGreylistMapper customerGreylistMapper;
	@Autowired
	private CustomerBlackListMapper customerBlackListMapper;
	@Autowired
	private TagCustomerMapper tagCustomerMapper;
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	@Autowired
	private GridInfoMapper gridInfoMapper;
	@Override
	public Map<String,Object> insertByExcel(List<Map<String,Object>> list,String gridCode) throws Exception {
		int successCount=0;
		int failCount=0;
		for(Map<String,Object> map:list) {
			if(map.get("1")==null||"".equals(map.get("1")) || (map.get("2")==null || "".equals(map.get("2")))){
				failCount++;
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
			//查看信息是否存在于客户库中
			CustomerInfo customer= customerInfoMapper.getCustomerByIdNumber(customerWhitelist.getIdNumber());
			if(customer==null ||customer.getIdNumber()==null ||(customer.getStatus()!=5)) {
				/*throw new MyException("客户库中未建立相关客户信息");*/
				failCount++;
				continue;
			}
			if(!customer.getGridCode().equals(customerWhitelist.getGridCode())) {
				failCount++;
				continue;
			}
			//查看信息是否存在于白名单中
			/*CustomerWhitelist customerWhitelist=new CustomerWhitelist();
			customerWhitelist.setIdNumber(customerGreylist.getIdNumber());
			if (customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist).size()>0) {
				throw new MyException("该客户已存在白名单记录");
				continue;
			}*/
			//查看信息是否存在于黑名单中
			CustomerBlackList customerBlackList=new CustomerBlackList();
			customerBlackList.setIdNumber(customerWhitelist.getIdNumber());
			if(customerBlackListMapper.getByIdOrIdnumber(customerBlackList).size()>0) {
				/*throw new MyException("该客户已存在黑名单记录");*/
				failCount++;
				continue;
			}
            //查看信息是否存在于灰名单中
			
			CustomerGreylist customerGreylist=new CustomerGreylist();
			customerGreylist.setIdNumber(customerWhitelist.getIdNumber());
			if(customerGreylistMapper.getByIdOrIdnumber(customerGreylist).size()>0) {
			/*	throw new MyException("该客户已存在灰名单记录");*/
				failCount++;
				continue;
			}
			
			
			List<CustomerWhitelist> whitelist=customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist);
			if(whitelist.size()>0) {
				/*customerWhitelist.setUpdatedAt(now);
				customerWhitelist.setId(whitelist.get(0).getId());
				customerWhitelistMapper.updateByPrimaryKeySelective(customerWhitelist);*/
				failCount++;
				continue;
			}else {
				customerWhitelist.setCreatedAt(now);
				customerWhitelist.setUpdatedAt(now);
				customerWhitelistMapper.insertSelective(customerWhitelist);
				//将白名单标签插入客户标签库
				TagCustomer tagCustomer=new TagCustomer();
				tagCustomer.setTagId((long)5);
				tagCustomer.setIdNumber(customerWhitelist.getIdNumber());
				tagCustomer.setTagName("白名单");
				tagCustomer.setCreatedAt(now);
				tagCustomer.setUpdatedAt(now);
				tagCustomerMapper.insertSelective(tagCustomer);
				
			}
			successCount++;
			
		}
		Map<String,Object> map=new HashMap<>();
		map.put("successCount", successCount);
		map.put("failCount", failCount);
		return map;
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
			//删除客户标签库信息
			CustomerWhitelist customerWhitelist=customerWhitelistMapper.selectByPrimaryKey(id);
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setIdNumber(customerWhitelist.getIdNumber());
			tagCustomer.setTagId((long)5);
			tagCustomerMapper.deleteTagByIdNumberAndTagId(tagCustomer);
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
			if (customerWhitelistMapper.getByIdOrIdnumber(record).size()>0) {
				throw new MyException("该客户已存在白名单记录");
			}
			CustomerBlackList customerBlackList=new CustomerBlackList();
			customerBlackList.setIdNumber(record.getIdNumber());
			if(customerBlackListMapper.getByIdOrIdnumber(customerBlackList).size()>0) {
				throw new MyException("该客户已存在黑名单记录");
			}
			CustomerGreylist customerGreylist=new CustomerGreylist();
			customerGreylist.setIdNumber(record.getIdNumber());
			if(customerGreylistMapper.getByIdOrIdnumber(customerGreylist).size()>0) {
				throw new MyException("该客户已存在灰名单记录");
			}
			long now=System.currentTimeMillis();
			record.setCreatedAt(now);
			record.setUpdatedAt(now);
			
			//将白名单标签插入客户标签库
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setTagId((long)5);
			tagCustomer.setIdNumber(record.getIdNumber());
			tagCustomer.setTagName("白名单");
			tagCustomer.setCreatedAt(now);
			tagCustomer.setUpdatedAt(now);
			tagCustomerMapper.insertSelective(tagCustomer);
			
			return customerWhitelistMapper.insertSelective(record)==1;
		} catch (Exception e) {
			throw new MyException(e.getMessage());
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
	public List<Map<String,Object>> getCustomerWhiteListByPage(Map<String,Object> map) {
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
			return	customerWhitelistMapper.getWhiteLists(map);
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("查询参数异常");
		}
		
	}
	@Override
	public boolean moveCustomerInfo(String idNumber, String type,String reason ) throws Exception {
	if(idNumber==null||"".equals(idNumber) ||type==null|| "".equals(type)) {
		throw new MyException("参数异常");
	}
	long now =System.currentTimeMillis();
	try {
		//白转灰
		if("1".equals(type)) {
			CustomerWhitelist customerWhitelist=new CustomerWhitelist();
			customerWhitelist.setIdNumber(idNumber);
			List<CustomerWhitelist> list=customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist);
			if(list.isEmpty()) {
				throw new MyException("未查询到相关名单");
			}
			
			CustomerGreylist customerGreylist=new CustomerGreylist();
			customerGreylist.setIdNumber(list.get(0).getIdNumber());
			if(customerGreylistMapper.getByIdOrIdnumber(customerGreylist).size()>0) {
				throw new MyException("灰名单中已存在此客户信息相关记录");
			}
			customerGreylist.setCustomerName(list.get(0).getCustomerName());
			customerGreylist.setAddress(list.get(0).getAdress());
			customerGreylist.setCreatedAt(now);
			customerGreylist.setUpdatedAt(now);
			customerGreylist.setPhoneNumber(list.get(0).getPhoneNumber());
			customerGreylist.setGridCode(list.get(0).getGridCode());
			customerGreylist.setReason(reason );
			customerGreylist.setComment(list.get(0).getComment());
			customerGreylistMapper.insertSelective(customerGreylist);
			//删除原来的白名单
			customerWhitelistMapper.deleteByPrimaryKey(list.get(0).getId());
			//更改客户标签关联表中的数据
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setTagId((long)2);
			tagCustomer.setIdNumber(idNumber);
			List<TagCustomer> listTag=tagCustomerMapper.getListByTagIdAndIdNumber(tagCustomer);
			
			if(listTag.size()>0) {
				listTag.get(0).setTagId((long)3);
				listTag.get(0).setUpdatedAt(now);
				listTag.get(0).setTagName("灰名单");
				tagCustomerMapper.updateByPrimaryKeySelective(listTag.get(0));
			}else {
				tagCustomer.setTagId((long)3);
				tagCustomer.setTagName("灰名单");
				tagCustomer.setCreatedAt(now);
				tagCustomer.setUpdatedAt(now);
				tagCustomerMapper.insertSelective(tagCustomer);
			}
			
			
			
		}
		//灰转白
   if("2".equals(type)) {
		   CustomerGreylist customerGreylist=new CustomerGreylist();
		   customerGreylist.setIdNumber(idNumber);
		   List<CustomerGreylist> list=customerGreylistMapper.getByIdOrIdnumber(customerGreylist);
		   if(list.isEmpty()) {
				throw new MyException("未查询到相关名单");
			}
		   CustomerWhitelist customerWhitelist=new CustomerWhitelist();
		
		   customerWhitelist.setIdNumber(list.get(0).getIdNumber());
		   if(customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist).size()>0) {
			   throw new MyException("白名单中已存在此客户信息相关记录"); 
		   }
		   customerWhitelist.setAdress(list.get(0).getAddress());
		   customerWhitelist.setCustomerName(list.get(0).getCustomerName());
		   customerWhitelist.setComment(list.get(0).getComment());
		   customerWhitelist.setPhoneNumber(list.get(0).getPhoneNumber());
		   customerWhitelist.setGridCode(list.get(0).getGridCode());
		   customerWhitelist.setCreatedAt(now);
		   customerWhitelist.setUpdatedAt(now);
		   customerWhitelistMapper.insertSelective(customerWhitelist);
		   customerGreylistMapper.deleteByPrimaryKey(list.get(0).getId());
		   
		 //更改客户标签关联表中的数据
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setTagId((long)3);
			tagCustomer.setIdNumber(idNumber);
			List<TagCustomer> listTag=tagCustomerMapper.getListByTagIdAndIdNumber(tagCustomer);
			if(listTag.size()>0) {
				listTag.get(0).setTagId((long)2);
				listTag.get(0).setTagName("白名单");
				listTag.get(0).setUpdatedAt(now);
				tagCustomerMapper.updateByPrimaryKeySelective(listTag.get(0));
			}else {
				tagCustomer.setTagId((long)2);
				tagCustomer.setTagName("白名单");
				tagCustomer.setCreatedAt(now);
				tagCustomer.setUpdatedAt(now);
				tagCustomerMapper.insertSelective(tagCustomer);
			}
		   
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new MyException("操作异常:"+e.getMessage());
	}
	
	
		return true;
	}

	
	
}
