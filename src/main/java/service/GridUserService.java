package service;

import model.GridUser;

public interface GridUserService {

    //删除账户-暂不调用
    boolean deleteByPrimaryKey(Long accountId) throws Exception;
    //新增账户
    boolean insertSelective(GridUser record) throws Exception;
    //查看账户详情
    GridUser selectByPrimaryKey(Long accountId) throws Exception;
    //编辑账户
    boolean updateByPrimaryKeySelective(GridUser record) throws Exception;
}
