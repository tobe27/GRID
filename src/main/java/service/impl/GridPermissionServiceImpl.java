package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dao.GridPermissionMapper;
import exception.MyException;
import model.GridPermission;
import service.GridPermissionService;
@Service
public class GridPermissionServiceImpl implements GridPermissionService {
	
	
	
  @Autowired
  private GridPermissionMapper gridPermissionMapper;
  
  
  
	@Override
	public boolean deleteByPrimaryKey(Long permissionId) throws Exception{
		try {
			return gridPermissionMapper.deleteByPrimaryKey(permissionId)==1;
		}catch (Exception e){
			  throw new MyException("删除权限出现异常");
		 }
		
		
	}

	@Override
	public boolean insert(GridPermission record) throws Exception{
		try {
			return gridPermissionMapper.insert(record)==1;
		}catch (Exception e){
			  throw new MyException("新增权限出现异常");
		 }
		
		
		
	}

	@Override
	public boolean insertSelective(GridPermission record)throws Exception {
		record.setCreatedAt(System.currentTimeMillis());
		try {
			return gridPermissionMapper.insertSelective(record)==1;
		}catch (Exception e){
			  throw new MyException("新增权限出现异常");
		 }
		
		
		
	}

	@Override
	public GridPermission selectByPrimaryKey(Long permissionId)throws Exception {
		try {
			return gridPermissionMapper.selectByPrimaryKey(permissionId);
		}catch (Exception e){
			  throw new MyException("查询权限出现异常");
		 }
	
	
	}

	@Override
	public boolean updateByPrimaryKeySelective(GridPermission record)throws Exception {
		record.setUpdatedAt(System.currentTimeMillis());
		try {
			return gridPermissionMapper.updateByPrimaryKeySelective(record)==1;
		}catch (Exception e){
			  throw new MyException("修改权限出现异常");
		 }
		
		
	}

	@Override
	public boolean updateByPrimaryKey(GridPermission record)throws Exception {
		try {
			return gridPermissionMapper.updateByPrimaryKey(record)==1;
		}catch (Exception e){
			  throw new MyException("修改权限出现异常");
		 }
		
	
		
	}

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
