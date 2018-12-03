package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dao.GridPermissionMapper;
import dao.GridRolePermissionMapper;
import exception.MyException;
import model.GridPermission;
import model.GridRolePermission;
import service.GridPermissionService;
@Service
public class GridPermissionServiceImpl implements GridPermissionService {
	
	
	
  @Autowired
  private GridPermissionMapper gridPermissionMapper;
  @Autowired
  private GridRolePermissionMapper gridRolePermissionMapper;
  
  /**
   * 根据key删除权限
   *
   * @param  permissionId
   * @return 
   * @throws Exception
   */
  
	@Override
	public boolean deleteByPrimaryKey(Long permissionId) throws Exception{
		//删除权限前先查看系统中有没有角色关联当前的权限
		GridRolePermission gridRolePermission=new GridRolePermission();
		gridRolePermission.setPermissionId(permissionId);
		List<GridRolePermission> list= gridRolePermissionMapper.getGridRolePermissionsByPermissionId(gridRolePermission);
		if(list!=null && list.size()>0) {
			 throw new MyException("该权限关联了角色，请先解除角色关联再删除");
		}
		
		try {
			
			return gridPermissionMapper.deleteByPrimaryKey(permissionId)==1;
		}catch (Exception e){
			  throw new MyException("删除权限出现异常");
		 }
		
		
	}

	
	
	/**
	   * 新增权限（全参数）
	   *
	   * @param  GridPermission
	   * @return 
	   * @throws Exception
	   */
	@Override
	public boolean insert(GridPermission record) throws Exception{
		try {
			return gridPermissionMapper.insert(record)==1;
		}catch (Exception e){
			  throw new MyException("新增权限出现异常");
		 }
		
		
		
	}

	
	/**
	   * 新增权限（参数可为空）
	   *
	   * @param  GridPermission
	   * @return 
	   * @throws Exception
	   */
	@Override
	public boolean insertSelective(GridPermission record)throws Exception {
	long now =System.currentTimeMillis();
		record.setCreatedAt(now);
		record.setUpdatedAt(now);
		
		try {
			return gridPermissionMapper.insertSelective(record)==1;
		}catch (Exception e){
			  throw new MyException("新增权限出现异常");
		 }
		
		
		
	}

	
	
	/**
	   * 根据主键查询
	   *
	   * @param  permissionId
	   * @return  gridPermission
	   * @throws Exception
	   */
	@Override
	public GridPermission selectByPrimaryKey(Long permissionId)throws Exception {
		try {
			return gridPermissionMapper.selectByPrimaryKey(permissionId);
		}catch (Exception e){
			  throw new MyException("查询权限出现异常");
		 }
	
	
	}
	
	
	
	/**
	   * 修改权限（参数可为空）
	   *
	   * @param  permissionId
	   * @return  
	   * @throws Exception
	   */

	@Override
	public boolean updateByPrimaryKeySelective(GridPermission record)throws Exception {
		record.setUpdatedAt(System.currentTimeMillis());
		try {
			return gridPermissionMapper.updateByPrimaryKeySelective(record)==1;
		}catch (Exception e){
			  throw new MyException("修改权限出现异常");
		 }
		
		
	}

	
	
	
	/**
	   * 修改权限（全参数）
	   *
	   * @param  GridPermission
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean updateByPrimaryKey(GridPermission record)throws Exception {
		try {
			return gridPermissionMapper.updateByPrimaryKey(record)==1;
		}catch (Exception e){
			  throw new MyException("修改权限出现异常");
		 }
		
	
		
	}
    
	/**
	   * 分页和条件查询权限
	   *
	   * @param  pageNo,pageSize,permissionName
	   * @return  
	   * @throws Exception
	   */
	@Override
	public PageInfo<GridPermission> getGridPermissions(int pageNo, int pageSize, String permissionName)
			throws Exception {
		try {
		
		Map<String,Object> map=new HashMap<>();
		PageHelper.startPage(pageNo, pageSize);
		map.put("permissionName", permissionName);
		List<GridPermission> list=gridPermissionMapper.getPermissions(map);
		PageInfo<GridPermission> pageInfo=new PageInfo<GridPermission>(list);
		
		return pageInfo;
		}catch(Exception e) {
			 throw new MyException("查询权限出现异常");
		}
	}

}
