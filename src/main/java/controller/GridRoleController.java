package controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import model.GridRole;

import model.ResponseData;
import service.GridRoleService;



@RestController
@RequestMapping("super")
public class GridRoleController {
	@Autowired
    private GridRoleService gridRoleService;
	
	 /**
     * 调用此接口进行新增角色
     * @param gridRole 角色实体类
     * @return
     * @throws Exception
     */
	 @RequestMapping(value = "/role",method = RequestMethod.POST)
	 public ResponseData insertRole(GridRole gridRole,@RequestParam("permissionIds[]") List<Long> permissionIds) {
	    if(gridRole.getRoleName()==null || "".equals(gridRole.getRoleName())) {
	    	return new ResponseData().code(400).message("角色名不能为空");
	    }
	    
	        try {
	        	gridRoleService.insertSelective(gridRole,permissionIds);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
	 
	 /**
	     * 调用此接口修改角色
	     * @param gridRole 角色实体类
	     * @return
	     * @throws Exception
	     */
	 @RequestMapping(value = "/role/{roleId}",method = RequestMethod.PUT)
	 public ResponseData updateRole( GridRole gridRole,@RequestParam("permissionIds[]") List<Long> permissionIds) {
		 if(gridRole.getRoleName()==null || "".equals(gridRole.getRoleName())) {
		    	return new ResponseData().code(400).message("角色名不能为空");
		    }
		 if(gridRole.getRoleId()==null ) {
		    	return new ResponseData().code(400).message("角ID不能为空");
		    }
	        try {
	        	gridRoleService.updateByPrimaryKeySelective(gridRole,permissionIds);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
	 
	 /**
	     * 调用此接口获取角色详情
	     * @param roleId
	     * @return
	     * @throws Exception
	     */
	  @RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET)
	  public ResponseData getGridUser(@PathVariable("roleId") Long roleId){
	        GridRole gridRole = null;
	       
	        
	        try {
	        	gridRole = gridRoleService.selectByPrimaryKey(roleId);
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	        if (gridRole == null) {
	            return new ResponseData().code(400).message("角色不存在");
	        }
	        return new ResponseData().success().data(gridRole);
	    }
	  /**
	     * 调用此接口删除角色
	     * @param roleId
	     * @return
	     * @throws Exception
	     */
	  @RequestMapping(value = "/role/{roleId}", method = RequestMethod.DELETE)
	  public ResponseData roleDelete(@PathVariable("roleId") Long roleId){
	    
	        try {
	        	  gridRoleService.deleteByPrimaryKey(roleId);
	        	  return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
			
	       
	    }
	  
	  /**
	     * 分页和条件显示角色列表
	     * @param pageNo,PageSize
	     * @return
	     * @throws Exception
	     */
	  @RequestMapping(value = "/role/list", method = RequestMethod.GET)
	  public ResponseData getRolesByPage(int pageNo,int pageSize,String roleName,String roleScope) {
		 
		  PageInfo<GridRole> pageInfo;
		  try {
	        	 pageInfo = gridRoleService.getGridRoles(pageNo, pageSize,roleName,roleScope);
	        } catch (Exception e) {
	            return new ResponseData().code(400).message("查询角色信息出错");
	        }
	        
	      
			return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
		  
	  }
}
