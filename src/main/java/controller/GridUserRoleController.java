package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import model.GridUserRole;
import model.ResponseData;
import service.GridUserRoleService;


@RestController
@RequestMapping("super")
public class GridUserRoleController {
	@Autowired
	private GridUserRoleService gridUserRolerService;
	/*
	 *//**
     * 调用此接口新增用户和角色关联数据
     * @param gridUserRole 角色实体类
     * @return
     * @throws Exception
     *//*
	 @RequestMapping(value = "/userrole",method = RequestMethod.POST)
	 public ResponseData insertRole(GridUserRole gridUserRole) {
	     
	        try {
	        	gridUserRolerService.insertSelective(gridUserRole);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }*/
	/* *//**
	     * 调用此接口用户修改用户角色关联
	     * @param gridUserRole 角色实体类
	     * @return
	     * @throws Exception
	     *//*
	 @RequestMapping(value = "/userrole/{}",method = RequestMethod.PUT)
	 public ResponseData updateRole(GridUserRole gridUserRole) {
	      
	        try {
	        	gridUserRolerService.updateByPrimaryKeySelective(gridUserRole);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
	 */
	 /**
	     * 调用此接口删除用户角色关联信息
	     * @param roleId
	     * @return
	     * @throws Exception
	     *//*
	  @RequestMapping(value = "/userRoleDelete", method = RequestMethod.GET)
	  public ResponseData roleDelete( Long roleId,Long accountId){
	    
	        try {
	        	gridUserRolerService.deleteByUsetRole(roleId, accountId);
	        	  return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
			
	       
	    }*/
	 

}
