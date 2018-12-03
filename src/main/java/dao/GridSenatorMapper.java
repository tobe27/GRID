package dao;

import model.GridSenator;

import java.util.List;

/**
 * 网格评议员
 * @author Created by L.C.Y on 2018-11-23
 */
public interface GridSenatorMapper {
    int deleteByPrimaryKey(Long senatorId);

    int insertSelective(GridSenator record);

    GridSenator selectByPrimaryKey(Long senatorId);

    List<GridSenator> listByType(GridSenator record);

    List<GridSenator> listByGridCode(GridSenator record);

    int updateByPrimaryKeySelective(GridSenator record);

}