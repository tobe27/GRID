package dao;

import model.GridSenatorRelation;

import java.util.List;

public interface GridSenatorRelationMapper {
    int deleteBySenatorId(Long senatorId);

    int deleteByGridCode(String gridCode);

    int insertSelective(GridSenatorRelation record);

    GridSenatorRelation getBySenatorId(Long senatorId);

    List<GridSenatorRelation> listByGridCode(String gridCode);
}