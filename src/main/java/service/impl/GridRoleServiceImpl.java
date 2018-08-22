package service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GridRoleMapper;
import model.GridRole;
import service.GridRoleService;
@Service


public class GridRoleServiceImpl implements GridRoleService {
	@Autowired
	private  GridRoleMapper gridRoleMapper;

	@Override
	public boolean deleteByPrimaryKey(Integer roleId) {
	
		return gridRoleMapper.deleteByPrimaryKey(roleId)==1;
	}

	@Override
	public boolean insert(GridRole record) {
		
		return gridRoleMapper.insert(record)==1;
	}
    
	@Override
	public boolean insertSelective(GridRole record) {
		
		return gridRoleMapper.insertSelective(record)==1;
	}

	@Override
	public GridRole selectByPrimaryKey(Integer roleId) {
	
		return gridRoleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public boolean updateByPrimaryKeySelective(GridRole record) {
	
		return gridRoleMapper.updateByPrimaryKeySelective(record)==1;
	}

	@Override
	public boolean updateByPrimaryKey(GridRole record) {
		
		return gridRoleMapper.updateByPrimaryKey(record)==1;
	}

}
