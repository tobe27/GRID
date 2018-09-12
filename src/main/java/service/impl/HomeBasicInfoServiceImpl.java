package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.HomeBasicInfoMapper;
import exception.MyException;
import model.HomeBasicInfo;
import service.HomeBasicInfoService;

@Service
public class HomeBasicInfoServiceImpl implements HomeBasicInfoService {
	
	@Autowired
	private HomeBasicInfoMapper homeBasicInfoMapper;

	
	
	 /**
     * 插入基础数据（home_basic_info表）
     *
     * @param HomeBasicInfo
     * @return
     * @throws Exception
     */
	@Override
	public boolean insertSelective(HomeBasicInfo record) throws Exception {
		try {
			return homeBasicInfoMapper.insertSelective(record)==1;
		} catch(Exception e) {
			throw new MyException("新增基础数据异常");
		}
		
	
		
	}
 
	
	
	 /**
     * 查询基础数据（home_basic_info表）
     *
     * @param id
     * @return
     * @throws Exception
     */
	@Override
	public HomeBasicInfo selectByPrimaryKey(Long id) throws Exception {
		try {
		return homeBasicInfoMapper.selectByPrimaryKey(id);
		} catch(Exception e) {
			throw new MyException("查询基础数据异常");
		}
	}
     
	
	
	/**
     * 修改基础数据（home_basic_info表）
     *
     * @param HomeBasicInfo
     * @return
     * @throws Exception
     */
	@Override
	public boolean updateByPrimaryKeySelective(HomeBasicInfo record) throws Exception {
		try {
		return  homeBasicInfoMapper.updateByPrimaryKeySelective(record)==1;
		} catch(Exception e) {
			throw new MyException("修改基础数据异常");
		}
		
	}

	/**
     * 董事长、中层干部首页的基本信息查询
     *
     * @param 
     * @return HomeBasicInfo
     * @throws Exception
     */

	@Override
	public HomeBasicInfo getPresidentAndMiddleHomeBasicInfo() throws Exception {
		try {
			return homeBasicInfoMapper.getPresidentAndMiddleHomeBasicInfo();
		}catch(Exception e) {
			throw new MyException("查询首页数据异常");
		}
	}


	/**
     * 基层干部首页的基本信息查询
     *
     * @param 
     * @return HomeBasicInfo
     * @throws Exception
     */

	@Override
	public HomeBasicInfo getBasicInfo() throws Exception {
		try {
			return homeBasicInfoMapper.getBasicInfo();
		}catch(Exception e) {
			throw new MyException("查询首页数据异常");
		}
	}


	/**
     *    董事长、中层干部首页支行排名数据查询
     *
     * @param 
     * @return List<Map<String, Object>>
     * @throws Exception
     */

	@Override
	public List<Map<String, Object>> getPresidentAndMiddleBranchInfoSort(Map<String, Object> map) throws Exception {
	
		try {
			return homeBasicInfoMapper.getPresidentAndMiddleBranchInfoSort(map);
		}catch(Exception e) {
			throw new MyException("查询首页数据异常");
		}
	}


	/**
     *    董事长、中层干部首页客户经理排名数据查询
     *
     * @param 
     * @return List<Map<String, Object>>
     * @throws Exception
     */

	@Override
	public List<Map<String, Object>> getPresidentAndMiddleAccountInfoSort(Map<String, Object> map) throws Exception {
		try {
			return homeBasicInfoMapper.getPresidentAndMiddleAccountInfoSort(map);
		}catch(Exception e) {
			throw new MyException("查询首页数据异常");
		}
		
	}


	
	/**
     *    基层干部首页客户经理排名数据查询
     *
     * @param Map<String, Object>
     * @return List<Map<String, Object>>
     * @throws Exception
     */


	@Override
	public List<Map<String, Object>> getBasicAccountInfoSort(Map<String, Object> map) throws Exception {
		Map<String, Object> accountIdMap=null;
		List<Map<String, Object>> list=new ArrayList<>();
		String [] accountIds=null;
		try {
		Map<String,Object> orgCodeMap=homeBasicInfoMapper.getSubordinateOrgCode(map);
		if(!"".equals(orgCodeMap.get("orgCode").toString()) ) {
			String [] orgCode= orgCodeMap.get("orgCode").toString().split(",");
			map.put("orgCode", orgCode);
			accountIdMap=homeBasicInfoMapper.getAccountIdsByOrgCodes(map);
		}else {
			return list;
		}
		if(!"".equals(accountIdMap.get("accountId").toString())) {
			 accountIds= accountIdMap.get("accountId").toString().split(",");
			 map.put("accountId", accountIds);
			
		}else {
			return list;
		}
		return homeBasicInfoMapper.getBasicAccountInfo(map);
		
    }catch(Exception e) {
    	throw new MyException("查询首页数据异常");
		}
	}
		

}
