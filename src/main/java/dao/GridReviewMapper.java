package dao;

import model.GridReview;

public interface GridReviewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GridReview record);

    int insertSelective(GridReview record);

    GridReview selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridReview record);

    int updateByPrimaryKey(GridReview record);
}