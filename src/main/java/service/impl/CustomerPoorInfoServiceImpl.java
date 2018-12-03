package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CustomerGreylistMapper;
import dao.CustomerInfoMapper;
import dao.CustomerPoorInfoMapper;
import dao.TagCustomerMapper;
import exception.MyException;
import model.CustomerGreylist;
import model.CustomerInfo;
import model.CustomerPoorInfo;
import model.TagCustomer;
import service.CustomerPoorInfoService;
@Service
public class CustomerPoorInfoServiceImpl implements CustomerPoorInfoService {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private CustomerPoorInfoMapper customerPoorInfoMapper;
    @Autowired
    private TagCustomerMapper tagCustomerMapper;
    @Autowired
    private CustomerGreylistMapper customerGreylistMapper;
    
    
    
	@Override
	public boolean deleteByPrimaryKey(Integer id) throws Exception {
		
		return false;
	}

	@Override
	public boolean insertSelective(CustomerPoorInfo record) throws Exception {
		
		
		
		
		return false;
	}

	@Override
	public CustomerPoorInfo selectByPrimaryKey(Integer id) throws Exception {
		
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerPoorInfo record) throws Exception {
		
		return false;
	}

	@Override
	public Map<String,Object> insertFromExcel(List<Map<String,Object>> list) throws Exception {
		Map<String,Object> map=new HashMap<>();
		List<Map<String,Object>> faileList=new ArrayList<>();
		int success=0;
		int faile=0;
		long now =System.currentTimeMillis();
		if(list.isEmpty()) {
			map.put("success", success);
			map.put("faile", faile);
			map.put("faileList", faileList);
		}
		
		//判断字段是否为空   
		for(Map<String,Object> dataMap:list) {
			//判断身份证号是否为空
			if("".equals(dataMap.get("8").toString())) {
				Map<String,Object> faileMap=new HashMap<>();
				faileMap.put("num", dataMap.get("0").toString());
				faileMap.put("reason", "身份证为空");
				faileList.add(faileMap);
				faile++;
				continue;
			}
			//判断该信息是否存在于客户信息中
			CustomerInfo customerInfo= customerInfoMapper.getCustomerByIdNumber(dataMap.get("8").toString());
			if(customerInfo==null || customerInfo.getIdNumber()==null ) {
				Map<String,Object> faileMap=new HashMap<>();
				faileMap.put("num", dataMap.get("0").toString());
				faileMap.put("reason", "客户系统中不存在此客户");
				faileList.add(faileMap);
				faile++;
				continue;
			}
			//判断该信息是否已经存在于贫困户信息
			CustomerPoorInfo customerPoorInfo=new CustomerPoorInfo();
			customerPoorInfo.setIdNumber(dataMap.get("8").toString());
			if(customerPoorInfoMapper.getByIdnumber(customerPoorInfo).size()>0) {
				Map<String,Object> faileMap=new HashMap<>();
				faileMap.put("num", dataMap.get("0").toString());
				faileMap.put("reason", "贫困户信息中已存在此客户");
				faileList.add(faileMap);
				faile++;
				continue;
			}
			customerPoorInfo.setPersonCode(dataMap.get("6").toString());
			customerPoorInfo.setPersonCount(dataMap.get("9").toString());
			customerPoorInfo.setInternalStudent(dataMap.get("13").toString());
			customerPoorInfo.setHealthCondition(dataMap.get("14").toString());
			customerPoorInfo.setLaborSkills(dataMap.get("15").toString());
			customerPoorInfo.setWorkingConditions(dataMap.get("16").toString());
			customerPoorInfo.setWorkingTime(dataMap.get("17").toString());
			customerPoorInfo.setCriticalIllnessInsurance(dataMap.get("18").toString());
			customerPoorInfo.setOvercomePoverty(dataMap.get("19").toString());
			customerPoorInfo.setOvercomePovertyYear(dataMap.get("20").toString());
			customerPoorInfo.setAttribute(dataMap.get("21").toString());
			customerPoorInfo.setReason(dataMap.get("22").toString());
			customerPoorInfo.setDangerousBuilding(dataMap.get("23").toString());
			customerPoorInfo.setSafetyWater(dataMap.get("24").toString());
			customerPoorInfo.setDysdipsia(dataMap.get("25").toString());
			customerPoorInfo.setIncomeAvg(dataMap.get("26").toString());
			customerPoorInfo.setPhone(dataMap.get("27").toString());
			customerPoorInfo.setWriteIme(dataMap.get("28").toString());
			customerPoorInfo.setStatus("0");
			customerPoorInfo.setCreatedAt(now);
			customerPoorInfo.setUpdateAt(now);
			customerPoorInfoMapper.insert(customerPoorInfo);
			success++;
			//插入到客户标签库,插入前先检查是否已有记录
			TagCustomer tagCustomer=new TagCustomer();
			tagCustomer.setIdNumber(customerPoorInfo.getIdNumber());
			tagCustomer.setTagId((long)4);
			tagCustomer.setCreatedAt(now);
			tagCustomer.setUpdatedAt(now);
			tagCustomer.setTagName("贫困户");
			List<TagCustomer> tagList=tagCustomerMapper.getListByTagIdAndIdNumber(tagCustomer);
			if(tagList==null||tagList.isEmpty()) {
				tagCustomerMapper.insertSelective(tagCustomer);
			}
		
			//加入灰名单记录,插入前先判断是否有记录
			CustomerGreylist customerGreylist=new CustomerGreylist();
			customerGreylist.setIdNumber(customerPoorInfo.getIdNumber());
			customerGreylist.setPhoneNumber(Long.valueOf(String.valueOf(customerInfo.getPhoneNumber())));
			customerGreylist.setCreatedAt(now);
			customerGreylist.setUpdatedAt(now);
			customerGreylist.setGridCode(customerInfo.getGridCode());
			customerGreylist.setCustomerName(customerInfo.getCustomerName());
			customerGreylist.setReason("2");
			customerGreylist.setAddress(customerInfo.getCellAddress());
			List<CustomerGreylist> greyList=customerGreylistMapper.getByIdOrIdnumber(customerGreylist);
			if(greyList==null || greyList.isEmpty()) {
				customerGreylistMapper.insertSelective(customerGreylist);
			}
		}
		map.put("success", success);
		map.put("faile", faile);
		map.put("faileList", faileList);
		return map;
	}

	@Override
	public boolean deleteByIdNumber(String idNumber) throws Exception {
		//删除灰名单信息，标签信息已在客户标签模块删除
		CustomerGreylist customerGreylist=new CustomerGreylist();
		customerGreylist.setIdNumber(idNumber);
		customerGreylistMapper.deleteByIdNumber(customerGreylist);
		//删除贫困户信息
		CustomerPoorInfo customerPoorInfo=new CustomerPoorInfo();
		customerPoorInfo.setIdNumber(idNumber);
		customerPoorInfo.setStatus("0");
		customerPoorInfoMapper.deleteByIdNumber(customerPoorInfo);
		
		return true;
	}

	@Override
	public List<CustomerPoorInfo> getListByIdNumber(CustomerPoorInfo record) throws Exception {
	   try {
		if("".equals(record.getIdNumber())) {
			   throw new MyException("未查询到该客户的贫困户信息");
		   }
		   return customerPoorInfoMapper.getByIdnumber(record);
	} catch (Exception e) {
		
		e.printStackTrace();
		throw new MyException("查询出错");
	}
		
	}

}
