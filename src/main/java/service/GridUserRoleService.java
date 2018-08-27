package service;

import model.GridUserRole;

public interface GridUserRoleService {
	 boolean deleteByPrimaryKey(Long id) throws Exception;

	 boolean insert(GridUserRole record)throws Exception;

	 boolean insertSelective(GridUserRole record)throws Exception;

	    GridUserRole selectByPrimaryKey(Long id)throws Exception;

	    boolean updateByPrimaryKeySelective(GridUserRole record)throws Exception;

	    boolean updateByPrimaryKey(GridUserRole record)throws Exception;
	    
	    boolean deleteByUser(Long accountId) throws Exception;

}
