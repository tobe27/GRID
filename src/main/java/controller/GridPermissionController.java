package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import model.GridPermission;
import model.GridRole;
import model.ResponseData;
import service.GridPermissionService;

@RestController
@RequestMapping("super")
public class GridPermissionController {
	@Autowired
	private GridPermissionService  gridPermissionService;
	
	 /**
     * 调用此接口新增角色
     * @param gridPermission 权限实体类
     * @return
     * @throws Exception
     */
	 @RequestMapping(value = "/permission",method = RequestMethod.POST)
	 public ResponseData insertPermission(GridPermission gridPermission) {
		 if(gridPermission.getPermissionName()==null || "".equals(gridPermission.getPermissionName())) {
		    	return new ResponseData().code(400).message("权限名不能为空");
		    }
		 if(gridPermission.getPermissionType()==null || "".equals(gridPermission.getPermissionType())) {
		    	return new ResponseData().code(400).message("权限类型不能为空");
		    }
		 if(gridPermission.getPermissionUrl()==null || "".equals(gridPermission.getPermissionUrl())) {
		    	return new ResponseData().code(400).message("权限路径不能为空");
		    }
	        try {
	        	gridPermissionService.insertSelective(gridPermission);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
	
	 /**
	     * 调用此接口修改权限
	     * @param gridPermission 权限实体类
	     * @return
	     * @throws Exception
	     */
	 @RequestMapping(value = "/permission/{permissionId}",method = RequestMethod.PUT)
	 public ResponseData updateGridPermission( GridPermission gridPermission) {
		 if(gridPermission.getPermissionId()==null ) {
		    	return new ResponseData().code(400).message("权限Id不能为空");
		    }
		 if(gridPermission.getPermissionName()==null || "".equals(gridPermission.getPermissionName())) {
		    	return new ResponseData().code(400).message("权限名不能为空");
		    }
		 if(gridPermission.getPermissionType()==null || "".equals(gridPermission.getPermissionType())) {
		    	return new ResponseData().code(400).message("权限类型不能为空");
		    }
		 if(gridPermission.getPermissionUrl()==null || "".equals(gridPermission.getPermissionUrl())) {
		    	return new ResponseData().code(400).message("权限路径不能为空");
		    }
	        try {
	        	gridPermissionService.updateByPrimaryKeySelective(gridPermission);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
	 
	 /**
	     * 调用此接口获取权限详情
	     * @param permissionId
	     * @return
	     * @throws Exception
	     */
	  @RequestMapping(value = "/permission/{permissionId}", method = RequestMethod.GET)
	  public ResponseData getGridUser(@PathVariable("permissionId") Long permissionId){
		  GridPermission gridPermission = null;
	        try {
	        	gridPermission = gridPermissionService.selectByPrimaryKey(permissionId);
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	        if (gridPermission == null) {
	            return new ResponseData().code(400).message("权限不存在");
	        }
	        return new ResponseData().success().data(gridPermission);
	    }
	  /**
	     * 调用此接口删除权限
	     * @param gridPermissionId
	     * @return
	     * @throws Exception
	     */
	  @RequestMapping(value = "/permission/{permissionId}", method = RequestMethod.DELETE)
	  public ResponseData gridPermissionDelete(@PathVariable("permissionId") Long permissionId){
	    
	        try {
	        	gridPermissionService.deleteByPrimaryKey(permissionId);
	        	  return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
			
	       
	    }
	  
	  /**
	     * 分页和条件显示权限列表
	     * @param pageNo,PageSize
	     * @return
	     * @throws Exception
	     */
	  @RequestMapping(value = "/permission/list", method = RequestMethod.GET)
	  public ResponseData getPermissionList(int pageNo,int pageSize,String permissionName) {
		  
		  PageInfo<GridPermission> pageInfo;
		  try {
	        	 pageInfo = gridPermissionService.getGridPermissions(pageNo, pageSize, permissionName);
	        } catch (Exception e) {
	            return new ResponseData().code(400).message("查询权限信息出错");
	        }
	      
			return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
		  
		
	  }
}
