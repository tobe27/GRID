package service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CustomerCreditMapper;
import dao.TagCustomerMapper;
import exception.MyException;
import model.CustomerCredit;
import model.TagCustomer;
import service.CustomerCreditService;
@Service
public class CustomerCreditServiceImpl implements CustomerCreditService{
	
	@Autowired
	private CustomerCreditMapper  customerCreditMapper;
	@Autowired
	private TagCustomerMapper tagCustomerMapper;

	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		
	
		return false;
	}

	@Override
	public boolean insert(CustomerCredit record) throws Exception {
		
		return false;
	}

	@Override
	public boolean insertSelective(CustomerCredit record) throws Exception {
	 
		TagCustomer tagCustomer=new TagCustomer();
		tagCustomer.setTagId((long)1);
		tagCustomer.setIdNumber(record.getIdNumber());
		//看下客户标签关联表中有没有存过此客户的白名单数据  存了的话直接update，没有存的话直接插入
 try {	
		List<TagCustomer> list= tagCustomerMapper.getListByTagIdAndIdNumber(tagCustomer);
		if(list.size()>0) {
			for(TagCustomer tag:list) {
				tagCustomerMapper.updateByPrimaryKeySelective(tag);
			}
		}else {
			long now=System.currentTimeMillis();
			tagCustomer.setCreatedAt(now);
			tagCustomer.setTagName("白名单");
			tagCustomer.setUpdatedAt(now);
			tagCustomerMapper.insertSelective(tagCustomer);
			
		}
		int i=record.getPreCreditLimit().compareTo((new BigDecimal(0)));
		//如果预授信的额度大于0则插入客户授信表的数据库
		if(i>0) {
			customerCreditMapper.insertSelective(record);
		}
		return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			 throw new MyException("导入信息异常");
		}
		
	}

	@Override
	public CustomerCreditService selectByPrimaryKey(Long id) throws Exception {
		
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerCredit record) throws Exception {
		
		return false;
	}

	@Override
	public boolean updateByPrimaryKey(CustomerCredit record) throws Exception {
	
		return false;
	}

}
