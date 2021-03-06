package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GridRolePermissionMapper;
import exception.MyException;
import model.GridRolePermission;
import service.GridRolePermissionService;
@Service
public class GridRolePermissionServiceImpl implements GridRolePermissionService {
   
	@Autowired
   private GridRolePermissionMapper gridRolePermissionMapper;
   
   
	
	/**
	   *根据自增主键删除关联
	   *
	   * @param  id
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		try {
			
			return  gridRolePermissionMapper.deleteByPrimaryKey(id)==1;
		}catch(Exception e) {
			throw new MyException("删除操作出现异常");
		}
		
	}

	
	
	/**
	   *插入角色和权限关联（全参数）
	   *
	   * @param  GridRolePermission
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean insert(GridRolePermission record) throws Exception {
   try {		
			return  gridRolePermissionMapper.insert(record)==1;
		}catch(Exception e) {
			throw new MyException("插入数据操作出现异常");
		}
	}

	
	/**
	   *插入角色和权限关联（参数可为空）
	   *
	   * @param  GridRolePermission
	   * @return  
	   * @throws Exception
	   */
	
	@Override
	public boolean insertSelective(GridRolePermission record) throws Exception {
		   try {		
				return  gridRolePermissionMapper.insertSelective(record)==1;
			}catch(Exception e) {
				throw new MyException("插入数据操作出现异常");
			}
	}
	
	
	/**
	   *根据主键删除关联
	   *
	   * @param  id
	   * @return  
	   * @throws Exception
	   */
	@Override
	public GridRolePermission selectByPrimaryKey(Long id) throws Exception {
		   try {		
				return  gridRolePermissionMapper.selectByPrimaryKey(id);
			}catch(Exception e) {
				throw new MyException("查询数据出现异常");
			}
	}
	
	
	/**
	   *修改关联（参数可为空）
	   *
	   * @param  id
	   * @return  
	   * @throws Exception
	   */

	@Override
	public boolean updateByPrimaryKeySelective(GridRolePermission record) throws Exception {
		   try {		
				return  gridRolePermissionMapper.updateByPrimaryKeySelective(record)==1;
			}catch(Exception e) {
				throw new MyException("修改数据操作出现异常");
			}
	}

	
	/**
	   *修改关联（全参数）
	   *
	   * @param  id
	   * @return  
	   * @throws Exception
	   */
	@Override
	public boolean updateByPrimaryKey(GridRolePermission record) throws Exception {
		try {		
			return  gridRolePermissionMapper.updateByPrimaryKey(record)==1;
		}catch(Exception e) {
			throw new MyException("修改数据操作出现异常");
		}
	}

}
