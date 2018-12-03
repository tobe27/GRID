package dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import model.GridReview;

public interface GridReviewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GridReview record);

    int insertSelective(GridReview record);

    GridReview selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridReview record);

    int updateByPrimaryKey(GridReview record);
    int   deleteByGridCode(Map<String,Object> map);
    int updataByGridCode(GridReview record);
}