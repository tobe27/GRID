package service.impl;

import dao.GridUserMapper;
import exception.MyException;
import model.GridUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.GridUserService;

@Service
public class GridUserServiceImpl implements GridUserService {
    @Autowired
    GridUserMapper gridUserMapper;

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
    public GridUser selectByPrimaryKey(Long accountId) throws Exception {
        try {
            return gridUserMapper.selectByPrimaryKey(accountId);
        }catch (Exception e) {
            throw new MyException("查询用户详情出现异常");
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
