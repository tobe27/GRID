package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CustomerBusinessMapper;
import dao.CustomerBusinessProductMapper;
import exception.MyException;
import model.CustomerBusiness;
import model.CustomerBusinessProduct;
import service.CustomerBusinessService;
import util.ValidUtil;

@Service
public class CustomerBusinessServiceImpl implements CustomerBusinessService{
	@Autowired
	private CustomerBusinessMapper businessMapper;
	@Autowired
	private CustomerBusinessProductMapper businessProducrMapper;
	
	/**
	 * 根据身份证号查询办理过的客户业务与产品关联
	 * @param idNumber
	 * @return
	 */
	public Map<String,Object> getBusines(String idNumber) {
		try{
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("business", businessMapper.getBusinesssByIdNumber(idNumber));
			map.put("businessProduct", businessProducrMapper.getBusinesss(idNumber));
			return map;
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("根据身份证号查询业务信息出错");
		}
	}
	/**
     * 插入业务信息
     * @param record
     */
    public void insertBusiness(CustomerBusiness record){
    	if (ValidUtil.isEmpty(record.getIdNumber())) {
            throw new MyException("客户身份证号不能为空");
        }
    	if(businessMapper.getBusinesssByIdNumber(record.getIdNumber())!=null){
    		throw new MyException("该客户的业务信息已存在");
    	}
    	try{
    		long now = System.currentTimeMillis();
            record.setCreatedAt(now);
            record.setUpdatedAt(now);
			businessMapper.insertSelective(record);
		}catch(Exception e){
			throw new MyException("插入业务信息出错");
		}
    }
    
    /**
     * 修改业务信息
     * @param record
     */
    public void updateBusiness(CustomerBusiness record){
    	if (ValidUtil.isEmpty(record.getIdNumber())) {
            throw new MyException("客户身份证号不能为空");
        }
    	try{
    		long now = System.currentTimeMillis();
            record.setUpdatedAt(now);
			businessMapper.updateSelective(record);
		}catch(Exception e){
			throw new MyException("插入业务信息出错");
		}
    }
	/**
	 * 批量插入客户业务与产品关联信息
	 * @param record
	 * @return
	 */
	public boolean insertBusinessProduct(List<CustomerBusinessProduct> record) {
		try{
			// 创建时间
			long now = System.currentTimeMillis();
			for(CustomerBusinessProduct bp:record){
				if (ValidUtil.isEmpty(bp.getIdNumber())) {
		            throw new MyException("身份证号不能为空");
		        }
				
				bp.setCreatedAt(now);
				bp.setUpdatedAt(now);
			}
			businessProducrMapper.insertBusinesss(record);
		}catch(Exception e){
			throw new MyException("批量添加业务产品关联信息出错");
		}
		return true;
	}

	/**
     * 批量修改客户业务与产品关联
     * @param record
     * @return
     */
	public boolean updateBusinessProduct(List<CustomerBusinessProduct> record) {
		try{
			long now = System.currentTimeMillis();
			for(CustomerBusinessProduct bp:record){
				if (ValidUtil.isEmpty(bp.getId())) {
		            throw new MyException("修改的业务id不能为空");
		        }
				
				bp.setUpdatedAt(now);
			}
			businessProducrMapper.updateBusinesss(record);
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("批量修改业务产品关联信息出错");
		}
		return true;
	}

}
