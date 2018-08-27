package service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import model.GridRole;

public interface GridRoleService {
	 boolean deleteByPrimaryKey(Long roleId) throws Exception;

	 boolean insert(GridRole record)throws Exception;

	 boolean insertSelective(GridRole record,List<Long> permissionIds)throws Exception;

	    GridRole selectByPrimaryKey(Long roleId)throws Exception;

	    boolean updateByPrimaryKeySelective(GridRole record,List<Long> permissionIds)throws Exception;

	    boolean updateByPrimaryKey(GridRole record)throws Exception;
	    PageInfo<GridRole> getGridRoles(int pageNo,int pageSize,String roleName,String roleScope)throws Exception;
}
