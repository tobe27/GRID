package service;

import model.GridRole;

public interface GridRoleService {
	boolean deleteByPrimaryKey(Integer roleId);
	boolean insert(GridRole record);

	boolean insertSelective(GridRole record);

	    GridRole selectByPrimaryKey(Integer roleId);

	    boolean updateByPrimaryKeySelective(GridRole record);

	    boolean updateByPrimaryKey(GridRole record);
}
