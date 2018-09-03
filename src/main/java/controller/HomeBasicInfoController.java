package controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.ResponseData;
import service.HomeBasicInfoService;

@RestController
public class HomeBasicInfoController {
	
	@Autowired
	private HomeBasicInfoService homeBasicInfoService;
	
	/**
     * 调用此接口查询董事长首页的基本信息
     * @param 
     * @return
     */
	@RequestMapping(value = "/president/home/basicinfo", method = RequestMethod.GET)
	public ResponseData getPresidentHomeInfo() {
		try {
			 return new ResponseData().success().data(homeBasicInfoService.getPresidentAndMiddleHomeBasicInfo());
		} catch (Exception e) {
			  return new ResponseData().code(400).message(e.getMessage());
		}
		
	}
	
	/**
     * 调用此接口查询中层首页的基本信息
     * @param 
     * @return
     */
	@RequestMapping(value = "/middle/home/basicinfo", method = RequestMethod.GET)
	public ResponseData getMiddleHomeInfo() {
		
		try {
			 return new ResponseData().success().data(homeBasicInfoService.getPresidentAndMiddleHomeBasicInfo());
		} catch (Exception e) {
			  return new ResponseData().code(400).message(e.getMessage());
		}
	}
	
	

	/**
     * 调用此接口查询基层领导首页的基本信息
     * @param 
     * @return
     */
	@RequestMapping(value = "/basic/home/basicinfo", method = RequestMethod.GET)
	public ResponseData getBasicHomeInfo() {
		
		try {
			 return new ResponseData().success().data(homeBasicInfoService.getBasicInfo());
		} catch (Exception e) {
			  return new ResponseData().code(400).message(e.getMessage());
		}
	}
	
	/**
     * 调用此接口查询董事长首页的支行排名信息
     * @param 
     * @return
     */
	@RequestMapping(value = "/president/home/branchinfosort", method = RequestMethod.GET)
       public ResponseData getPresidentHomeBasicInfoSort(int sortType) {
		if(sortType !=1 && sortType !=2 && sortType !=3 && sortType !=4 &&sortType !=5) {
			 return new ResponseData().code(400).message("查询参数有误");
		}
		Map<String,Object> map=new HashMap<>();
		map.put("sortType", sortType);
		try {
			 return new ResponseData().success().data(homeBasicInfoService.getPresidentAndMiddleBranchInfoSort(map));
		} catch (Exception e) {
			  return new ResponseData().code(400).message(e.getMessage());
		}
	}
	
	/**
     * 调用此接口查询中层领导首页的支行排名信息
     * @param 
     * @return
     */
	@RequestMapping(value = "/middle/home/branchinfosort", method = RequestMethod.GET)
       public ResponseData getMiddleHomeBasicInfoSort(int sortType) {
		if(sortType !=1 && sortType !=2 && sortType !=3 && sortType !=4 &&sortType !=5) {
			 return new ResponseData().code(400).message("查询参数有误");
		}
		Map<String,Object> map=new HashMap<>();
		map.put("sortType", sortType);
		try {
			 return new ResponseData().success().data(homeBasicInfoService.getPresidentAndMiddleBranchInfoSort(map));
		} catch (Exception e) {
			  return new ResponseData().code(400).message(e.getMessage());
		}
	}
	
	
	
	/**
     * 调用此接口查询董事长首页的客户经理排名信息
     * @param 
     * @return
     */
	@RequestMapping(value = "/president/home/accountinfosort", method = RequestMethod.GET)
       public ResponseData getPresidentHomeBasicAccountInfoSort(int sortType) {
		if(sortType !=1 && sortType !=2 && sortType !=3 && sortType !=4 &&sortType !=5) {
			 return new ResponseData().code(400).message("查询参数有误");
		}
		Map<String,Object> map=new HashMap<>();
		map.put("sortType", sortType);
		try {
			 return new ResponseData().success().data(homeBasicInfoService.getPresidentAndMiddleAccountInfoSort(map));
		} catch (Exception e) {
			  return new ResponseData().code(400).message(e.getMessage());
		}
	}
	
	/**
     * 调用此接口查询中层领导首页的客户经理排名信息
     * @param 
     * @return
     */
	@RequestMapping(value = "/middle/home/accountinfosort", method = RequestMethod.GET)
       public ResponseData getMiddleHomeBasicAccountInfoSort(int sortType) {
		if(sortType !=1 && sortType !=2 && sortType !=3 && sortType !=4 &&sortType !=5) {
			 return new ResponseData().code(400).message("查询参数有误");
		}
		Map<String,Object> map=new HashMap<>();
		map.put("sortType", sortType);
		try {
			 return new ResponseData().success().data(homeBasicInfoService.getPresidentAndMiddleAccountInfoSort(map));
		} catch (Exception e) {
			  return new ResponseData().code(400).message(e.getMessage());
		}
	}
	/**
     * 调用此接口查询基层领导首页的客户经理排名信息
     * @param 
     * @return
     */
	
	@RequestMapping(value = "/basic/home/accountinfosort", method = RequestMethod.GET)
	public ResponseData getBasicHomeBasicAccountInfoSort(int sortType,String orgCode) {
		if(sortType !=1 && sortType !=2 && sortType !=3 && sortType !=4 &&sortType !=5) {
			 return new ResponseData().code(400).message("查询参数有误");
		}
		if(orgCode==null || "".equals(orgCode)) {
			 return new ResponseData().code(400).message("查询参数有误");	
		}
		Map<String,Object> map=new HashMap<>();
		map.put("sortType", sortType);
		map.put("orgCode", orgCode);
		try {
			 return new ResponseData().success().data(homeBasicInfoService.getBasicAccountInfoSort(map));
		} catch (Exception e) {
			  return new ResponseData().code(400).message(e.getMessage());
		}
	}

}
