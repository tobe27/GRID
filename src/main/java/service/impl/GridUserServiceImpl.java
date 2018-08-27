package service.impl;

import dao.GridUserMapper;
import exception.MyException;
import model.GridRole;
import model.GridUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.GridUserService;
import java.util.List;
import java.util.Set;

@Service
public class GridUserServiceImpl implements GridUserService {
    @Autowired
    GridUserMapper gridUserMapper;

    @Override
    public Set<String> getStringRolesByUserPrimaryKey(Long accountId) throws Exception{
        try {
            return gridUserMapper.getStringRolesByUserPrimaryKey(accountId);
        }catch (Exception e) {
            throw new MyException("获取角色异常");
        }
    }

    @Override
    public Set<String> getStringPermissionsByUserPrimaryKey(Long accountId) throws Exception{
        try {
            return gridUserMapper.getStringPermissionsByUserPrimaryKey(accountId);
        }catch (Exception e) {
            throw new MyException("获取权限异常");
        }
    }

    @Override
    public boolean deleteByPrimaryKey(Long accountId) throws Exception {
        try {
            return gridUserMapper.deleteByPrimaryKey(accountId) == 1;
        }catch (Exception e) {
            throw new MyException("删除用户出现异常");
        }
    }

    @Override
    public boolean insertSelective(GridUser record) throws Exception {
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return gridUserMapper.insertSelective(record) ==1;
        }catch (Exception e) {
            throw new MyException("新增用户出现异常");
        }

    }

    @Override
    public GridUser getUserByPrimaryKey(Long accountId) throws Exception {
        try {
            return gridUserMapper.getUserByPrimaryKey(accountId);
        }catch (Exception e) {
            throw new MyException("查询用户详情出现异常");
        }
    }

    @Override
    public boolean getUsersByUniqueIndex(GridUser record) throws Exception {
        try {
            return gridUserMapper.getUsersByUniqueIndex(record) != null;
        }catch (Exception e) {
            throw new MyException("通过唯一索引查询用户出现异常");
        }
    }

    @Override
    public List<GridUser> getUsersByAccountNameOrRealNameOrOrgName(GridUser record) throws Exception{
        try {
            return gridUserMapper.getUsersByAccountNameOrRealNameOrOrgName(record);
        }catch (Exception e) {
            throw new MyException("查询用户列表出现异常");
        }
    }


    @Override
    public List<GridUser> getUsersByRole(GridRole role) throws Exception {
        try {
            return gridUserMapper.getUsersByRole(role);
        }catch (Exception e) {
            throw new MyException("通过角色查询用户列表出现异常");
        }
    }


    @Override
    public boolean updateByPrimaryKeySelective(GridUser record) throws Exception {
        record.setUpdatedAt(System.currentTimeMillis());
        try {
            return gridUserMapper.updateByPrimaryKeySelective(record) == 1;
        }catch (Exception e) {
            throw new MyException("修改用户出现异常");
        }
    }
}
