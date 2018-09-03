package service;

import java.util.List;
import java.util.Map;

import model.HomeBasicInfo;

public interface HomeBasicInfoService {
	// boolean deleteByPrimaryKey(Long id) throws Exception;

	 //boolean insert(HomeBasicInfo record) throws Exception;

	  boolean insertSelective(HomeBasicInfo record) throws Exception;

	   HomeBasicInfo selectByPrimaryKey(Long id) throws Exception;

	   boolean updateByPrimaryKeySelective(HomeBasicInfo record) throws Exception;

	  //  boolean updateByPrimaryKey(HomeBasicInfo record) throws Exception;
	   //查询董事长、中层领导的首页信息
	   HomeBasicInfo  getPresidentAndMiddleHomeBasicInfo() throws Exception;
	   
	 //基层领导的首页信息
	   HomeBasicInfo  getBasicInfo() throws Exception;
	   
	   //董事长、中层干部首页支行排名数据
	   List<Map<String,Object>>  getPresidentAndMiddleBranchInfoSort(Map<String,Object> map) throws Exception;
	   
	   //董事长、中层干部首页客户经理排名数据
	   List<Map<String,Object>>  getPresidentAndMiddleAccountInfoSort(Map<String,Object> map) throws Exception;
	   
	   //基层干部首页客户经理数据排名
	   
	   List<Map<String,Object>>  getBasicAccountInfoSort(Map<String,Object> map) throws Exception;
}
