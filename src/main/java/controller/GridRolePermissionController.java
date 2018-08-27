package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.GridRolePermission;

import model.ResponseData;
import service.GridRolePermissionService;

@RestController
@RequestMapping("super")
public class GridRolePermissionController {
	@Autowired
	private  GridRolePermissionService gridRolePermissionService;
	
	/**
     * 调用此接口新增权限和角色关联数据
     * @param gridRolePermission 角色权限关联实体类
     * @return
     * @throws Exception
     */
	 @RequestMapping(value = "/rolepermission",method = RequestMethod.POST)
	 public ResponseData insertRole(GridRolePermission gridRolePermission) {
	     
	        try {
	        	gridRolePermissionService.insertSelective(gridRolePermission);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
	 /**
	     * 调用此接口用户修改角色权限关联
	     * @param gridRolePermission 角色权限关联实体类
	     * @return
	     * @throws Exception
	     */
	 @RequestMapping(value = "/rolepermission/{rolePermissionId}",method = RequestMethod.PUT)
	 public ResponseData updateRole(GridRolePermission gridRolePermission) {
	      
	        try {
	        	gridRolePermissionService.updateByPrimaryKeySelective(gridRolePermission);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
	 /**
	     * 调用此接口删除角色权限关联信息
	     * @param rolePermissionId
	     * @return
	     * @throws Exception
	     */
	  @RequestMapping(value = "/rolepermission/{rolePermissionId}", method = RequestMethod.DELETE)
	  public ResponseData roleDelete(@PathVariable("rolePermissionId") Long rolePermissionId){
	    
	        try {
	        	gridRolePermissionService.deleteByPrimaryKey(rolePermissionId);
	        	  return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
			
	       
	    }
	 
	

}
