package service;

import com.github.pagehelper.PageInfo;

import model.GridPermission;
import model.GridRole;

public interface GridPermissionService {
	boolean deleteByPrimaryKey(Long permissionId) throws Exception;

	boolean insert(GridPermission record)throws Exception;

	boolean insertSelective(GridPermission record)throws Exception;

    GridPermission selectByPrimaryKey(Long permissionId)throws Exception;

    boolean updateByPrimaryKeySelective(GridPermission record)throws Exception;

    boolean updateByPrimaryKey(GridPermission record)throws Exception;
    PageInfo<GridPermission> getGridPermissions(int pageNo,int pageSize,String permissionNmae)throws Exception;
    
}
