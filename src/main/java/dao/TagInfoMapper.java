package dao;

import model.TagInfo;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface TagInfoMapper {
    int deleteByPrimaryKey(Long tagId);

    int insertSelective(TagInfo record);

    TagInfo selectByPrimaryKey(Long tagId);

    TagInfo getByTagName(String tagName);

    List<TagInfo> listTags(TagInfo record);

    int updateByPrimaryKeySelective(TagInfo record);
}