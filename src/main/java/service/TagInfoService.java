package service;

import model.TagInfo;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface TagInfoService {
    /**
     * 删除标签信息
     * @param tagId
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long tagId) throws Exception;

    /**
     * 添加标签信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(TagInfo record) throws Exception;

    /**
     * 查看标签详情
     * @param tagId
     * @return
     * @throws Exception
     */
    TagInfo selectByPrimaryKey(Long tagId) throws Exception;

    /**
     * 通过标签名获取详情
     * @param tagName
     * @return
     * @throws Exception
     */
    TagInfo getByTagName(String tagName) throws Exception;

    /**
     * 获取标签列表
     * @param record
     * @return
     * @throws Exception
     */
    List<TagInfo> listTags(TagInfo record) throws Exception;

    /**
     * 更新标签信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(TagInfo record) throws Exception;
}
