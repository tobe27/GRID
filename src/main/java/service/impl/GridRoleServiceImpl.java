package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dao.GridRoleMapper;
import dao.GridRolePermissionMapper;
import dao.GridUserRoleMapper;
import exception.MyException;
import model.GridRole;
import model.GridRolePermission;
import model.GridUserRole;
import service.GridRoleService;
@Service


public class GridRoleServiceImpl implements GridRoleService {
	@Autowired
	private  GridRoleMapper gridRoleMapper;
	@Autowired
	private GridRolePermissionMapper  gridRolePermissionMapper;
	@Autowired
	private GridUserRoleMapper gridUserRoleMapper;

	
	/**
	   * 新增角色（全参数）
	   *
	   * @param GridRole
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean insert(GridRole record)throws Exception {
		try {
			//判断是否存在重名的角色名称
			if(gridRoleMapper.getGridRolesByIdOrName(record).size()>0) {
				throw new MyException("系统已存在相同的角色名称");
			}
		return gridRoleMapper.insert(record)==1;
		}catch(Exception e) {
			  throw new MyException("新增角色出现异常");
		}
	}
    
	
	/**
	   * 新增角色（参数可为空）
	   *
	   * @param GridRole
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean insertSelective(GridRole record,String permissionIds) throws Exception{
		boolean flag=false;
		//判断是否存在重名的角色名称
		if(gridRoleMapper.getGridRolesByIdOrName(record).size()>0) {
			throw new MyException("系统已存在相同的角色名称");
		}
		long now=System.currentTimeMillis();
		
		record.setCreatedAt(now);
		record.setUpdatedAt(now);
		try {
			if(gridRoleMapper.insertSelective(record)==1) {
				flag=true;
			}
			//传入权限id不为空的话创建角色和权限关联
			if(permissionIds !=null) {
				
				GridRolePermission gridRolePermission=new GridRolePermission();
				gridRolePermission.setRoleId(record.getRoleId());
				for(String permissionId:permissionIds.trim().split(",")) {
					gridRolePermission.setPermissionId(Long.parseLong(permissionId));
					gridRolePermissionMapper.insertSelective(gridRolePermission);
				}
			}
			return flag;
			}catch(Exception e) {
				  throw new MyException("新增角色出现异常");
			}
		
		
	}

	
	
	/**
	   * 根据id查询角色
	   *
	   * @param roleId
	   * @return  
	   * @throws Exception
	   */
	@Override
	public GridRole selectByPrimaryKey(Long roleId)throws Exception {
		try {
			return gridRoleMapper.selectByPrimaryKey(roleId);
			}catch(Exception e) {
				  throw new MyException("查询角色出现异常");
			}
	
		
	}

	
	
	
	/**
	   * 修改权限（参数可为空）
	   *
	   * @param GridRole
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean updateByPrimaryKeySelective(GridRole record,String permissionIds) throws Exception{
		boolean flag=false;
		try {
			//判断是否存在重名的角色名称
			if(gridRoleMapper.getGridRolesByIdOrName(record).size()>0) {
				throw new MyException("系统已存在相同的角色名称");
			}
			record.setUpdatedAt(System.currentTimeMillis());
			if(gridRoleMapper.updateByPrimaryKeySelective(record)==1) {
				flag=true;
			}
			//传入权限id不为空的话创建角色和权限关联
			if(permissionIds !=null) {
				//先删除原有的角色和权限关联
				gridRolePermissionMapper.deleteByRoleId(record.getRoleId());
				//再重新插入关联数据
				GridRolePermission gridRolePermission=new GridRolePermission();
				gridRolePermission.setRoleId(record.getRoleId());
				for(String permissionId:permissionIds.trim().split(",")) {
					gridRolePermission.setPermissionId(Long.parseLong(permissionId));
					gridRolePermissionMapper.insertSelective(gridRolePermission);
				}
			}
			
			
			return flag;
			}catch(Exception e) {
				  throw new MyException("修改角色出现异常");
			}
	
		
	}

	
	
	/**
	   * 修改权限（全参数）
	   *
	   * @param GridRole
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean updateByPrimaryKey(GridRole record)throws Exception {
		try {
			//判断是否存在重名的角色名称
			if(gridRoleMapper.getGridRolesByIdOrName(record).size()>0) {
				throw new MyException("系统已存在相同的角色名称");
			}
			return gridRoleMapper.updateByPrimaryKey(record)==1;
			}catch(Exception e) {
				  throw new MyException("修改角色出现异常");
			}
		
		
	}

	
	
	
	/**
	   * 根据id删除角色
	   *
	   * @param roleId
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean deleteByPrimaryKey(Long roleId) throws Exception {
		//删除前查看下系统中有没有用户是关联了当前的角色的
		GridUserRole gridUserRole=new GridUserRole();
		gridUserRole.setRoleId(roleId);
		List<GridUserRole> list=gridUserRoleMapper.getGridUserRole(gridUserRole);
		
		if(list!=null && list.size()>0) {
			throw new MyException("该角色关联了用户，请先解除关联再删除");
		}
		try {
			
			return gridRoleMapper.deleteByPrimaryKey(roleId)==1;
		}catch (Exception e){
			
			  throw new MyException("删除角色出现异常");
		 }
	}

	
	
	/**
	   * 分页和条件获取角色列表
	   *
	   * @param pageNo,pageSize,roleName,roleScope
	   * @return  
	   * @throws Exception
	   */
	@Override
	public PageInfo<GridRole> getGridRoles(int pageNo,int pageSize, String roleName,String roleScope) throws Exception {
		
		PageHelper.startPage(pageNo, pageSize);
		try {
			Map<String,Object> map=new HashMap<>();
			map.put("roleName", roleName);
			map.put("roleScope", roleScope);
			List<GridRole> list=gridRoleMapper.getGridRoles(map);
			PageInfo<GridRole> pageInfo=new PageInfo<>(list);
			
			return pageInfo;
		}catch (Exception e){
			  throw new MyException("查询角色出现异常");
		 }
		
	}

}
