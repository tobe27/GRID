package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BusinessProductMapper;
import exception.MyException;
import model.BusinessProduct;
import service.BusinessProductService;
import util.ValidUtil;

@Service
public class BusinessProductServiceImpl implements BusinessProductService{
	@Autowired
	private BusinessProductMapper businessProducrMapper;
	
	/**
	 * 批量插入业务信息
	 * @param map
	 * @param record
	 * @return
	 */
	public boolean insertBusinesss(List<BusinessProduct> record) {
		try{
			// 创建时间
			long now = System.currentTimeMillis();
			for(BusinessProduct bp:record){
				if (ValidUtil.isEmpty(bp.getIdNumber())) {
		            throw new MyException("身份证号不能为空");
		        }
				
				bp.setCreatedAt(now);
				bp.setUpdatedAt(now);
			}
			businessProducrMapper.insertBusinesss(record);
		}catch(Exception e){
			throw new MyException("批量添加业务信息出错");
		}
		return true;
	}

	/**
	 * 根据身份证号查询办理过的业务信息
	 * @param idNumber
	 * @return
	 */
	public List<BusinessProduct> getBusinesss(String idNumber) {
		try{
			return businessProducrMapper.getBusinesss(idNumber);
		}catch(Exception e){
			throw new MyException("根据身份证号查询业务信息出错");
		}
	}

	/**
     * 批量修改业务信息
     * @param map
     * @param updatedAt
     * @return
     */
	public boolean updateBusinesss(List<BusinessProduct> record) {
		try{
			long now = System.currentTimeMillis();
			for(BusinessProduct bp:record){
				if (ValidUtil.isEmpty(bp.getId())) {
		            throw new MyException("修改的业务id不能为空");
		        }
				
				bp.setUpdatedAt(now);
			}
			businessProducrMapper.updateBusinesss(record);
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("批量修改业务信息出错");
		}
		return true;
	}

}
