package service;

import model.GridRolePermission;

public interface GridRolePermissionService {
	boolean deleteByPrimaryKey(Long id) throws Exception;

	boolean insert(GridRolePermission record) throws Exception;

	boolean insertSelective(GridRolePermission record) throws Exception;

    GridRolePermission selectByPrimaryKey(Long id) throws Exception;

    boolean updateByPrimaryKeySelective(GridRolePermission record) throws Exception;

    boolean updateByPrimaryKey(GridRolePermission record)throws Exception;
 
}
