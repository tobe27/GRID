package service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BusinessInfoMapper;
import exception.MyException;
import service.BusinessInfoService;
import util.SqlUtil;
import util.ValidUtil;

@Service
public class BusinessInfoServiceImpl implements BusinessInfoService{
	@Autowired
	private BusinessInfoMapper infoMapper;
	 
	/**
	 * 查找业务信息
	 * @param idNumber
	 * @return
	 * @throws MyException 
	 */
	public Map<String,Object> getBusinessByPrimaryKey(String idNumber) throws MyException {
		try {
            return infoMapper.getBusinessByIdNumber(idNumber);
        } catch (Exception e) {
            throw new MyException("获取客户业务信息出现异常");
        }
	}

	/**
	 * 插入业务信息
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean insertSelective(Map<String,Object> record) throws Exception {
		//过滤record中的key
		sql_inj(record);
		
		if (ValidUtil.isEmpty(record.get("id_number"))) {
            throw new MyException("身份证号不能为空");
        }
		// 创建时间
        long now = System.currentTimeMillis();
        record.put("created_at",now);
        record.put("updated_at",now);
        try {
            return infoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("添加客户业务信息出现异常");
        }
	}

	/**
     * 修改业务信息
     * @param record
     * @return
     * @throws Exception
     */
	public boolean updateByPrimaryKeySelective(Map<String,Object> record) throws Exception {
		//过滤record中的key
		sql_inj(record);
		
		if (ValidUtil.isEmpty(record.get("id"))) {
            throw new MyException("id不能为空");
        }
		
		record.remove("id_number");
		// 创建时间
        long now = System.currentTimeMillis();
        record.put("updated_at",now);
        try {
            return infoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new MyException("修改客户业务信息出现异常");
        }
	}

	 /**
     * sql注入过滤
     * @param info
     * @return
     */
    private Map<String,Object> sql_inj(Map<String,Object> info){
    	for(String key:info.keySet()){
    		if(SqlUtil.sql_inj(key)){
        		info.remove(key);
        	}
    	}
    	return info;
    }
}
