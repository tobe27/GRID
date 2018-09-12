package dao;

import model.GridInfo;

public interface GridInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GridInfo record);

    int insertSelective(GridInfo record);

    GridInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridInfo record);

    int updateByPrimaryKey(GridInfo record);
}