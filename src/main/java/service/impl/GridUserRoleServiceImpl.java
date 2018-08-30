package service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GridUserRoleMapper;
import exception.MyException;
import model.GridUserRole;
import service.GridUserRoleService;

@Service
public class GridUserRoleServiceImpl implements GridUserRoleService {
	@Autowired
	private GridUserRoleMapper gridUserRoleMapper;

	
	
	/**
	   * 根据id删除关联
	   *
	   * @param id
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		try {
			return gridUserRoleMapper.deleteByPrimaryKey(id)==1;
		}catch (Exception e){
			  throw new MyException("删除操作出现异常");
		 }
	}

	
	

	/**
	   * 插入用户角色关联（全参数）
	   *
	   * @param GridUserRole
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean insert(GridUserRole record) throws Exception {
		try {
			return gridUserRoleMapper.insert(record)==1;
		}catch (Exception e){
			  throw new MyException("插入数据操作出现异常");
		 }
	}

	
	

	/**
	   * 插入用户角色关联（参数可为空）
	   *
	   * @param GridUserRole
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean insertSelective(GridUserRole record) throws Exception {
		try {
			return gridUserRoleMapper.insertSelective(record)==1;
		}catch (Exception e){
			  throw new MyException("插入数据操作出现异常");
		 }
	}
    
	
	
	
	/**
	   * 根据id查询关联
	   *
	   * @param id
	   * @return  
	   * @throws Exception
	   */
	@Override
	public GridUserRole selectByPrimaryKey(Long id) throws Exception {
		try {
			return gridUserRoleMapper.selectByPrimaryKey(id);
		}catch (Exception e){
			  throw new MyException("查询数据操作出现异常");
		 }
	}

	
	

	/**
	   * 修改用户角色关联（参数可为空）
	   *
	   * @param GridUserRole
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean updateByPrimaryKeySelective(GridUserRole record) throws Exception {
		try {
			return gridUserRoleMapper.updateByPrimaryKeySelective(record)==1;
		}catch (Exception e){
			  throw new MyException("修改数据操作出现异常");
		 }
	}

	
	
	/**
	   * 修改用户角色关联（全参数）
	   *
	   * @param GridUserRole
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean updateByPrimaryKey(GridUserRole record) throws Exception {
		try {
			return gridUserRoleMapper.updateByPrimaryKey(record)==1;
		}catch (Exception e){
			  throw new MyException("修改数据操作出现异常");
		 }
	}

	
	
	/**
	   * 删除用户的所有关联
	   *
	   * @param accountId
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean deleteByUser( Long accountId) throws Exception {
		Map<String,Object> map=new HashMap<>();
		
		map.put("accountId", accountId);
		try {
			return gridUserRoleMapper.deleteByUserRole(map)==1;
		}catch (Exception e){
			  throw new MyException("删除数据操作出现异常");
		 }
	}

}
