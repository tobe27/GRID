package dao;

import model.TagInfo;

import java.util.List;

public interface TagInfoMapper {
    int deleteByPrimaryKey(Long tagId);

    int insertSelective(TagInfo record);

    TagInfo selectByPrimaryKey(Long tagId);

    List<TagInfo> listTags(TagInfo record);

    int updateByPrimaryKeySelective(TagInfo record);
}