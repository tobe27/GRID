package dao;

import model.GridUser;
import org.springframework.stereotype.Repository;

@Repository
public interface GridUserMapper {
//删除用户-暂不调用
    int deleteByPrimaryKey(Long accountId);
//新增用户
    int insertSelective(GridUser record);
//查看用户详情
    GridUser selectByPrimaryKey(Long accountId);
//编辑用户
    int updateByPrimaryKeySelective(GridUser record);
}