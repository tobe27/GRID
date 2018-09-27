package service.impl;

import dao.TagCustomerMapper;
import dao.TagInfoMapper;
import exception.MyException;
import model.TagInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.TagInfoService;
import util.ValidUtil;

import java.util.List;

@Service
public class TagInfoServiceImpl implements TagInfoService {
    @Autowired
    TagInfoMapper tagInfoMapper;
    @Autowired
    TagCustomerMapper tagCustomerMapper;

    /**
     * 删除标签信息
     * @param tagId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean deleteByPrimaryKey(Long tagId) throws Exception {
        try {
            int count = tagInfoMapper.deleteByPrimaryKey(tagId);
            int count1 = tagCustomerMapper.deleteTagByTagId(tagId);
            return count == 1 && count1 == 1;
        } catch (Exception e) {
            throw new MyException("删除标签出现异常");
        }
    }

    /**
     * 添加标签信息
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(TagInfo record) throws Exception {
        if (ValidUtil.isEmpty(record.getTagName())) {
            throw new MyException("标签名不能为空");
        }
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return tagInfoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("添加标签出现异常");
        }
    }

    /**
     * 查看标签详情
     * @param tagId
     * @return
     * @throws Exception
     */
    @Override
    public TagInfo selectByPrimaryKey(Long tagId) throws Exception {
        try {
            return tagInfoMapper.selectByPrimaryKey(tagId);
        } catch (Exception e) {
            throw new MyException("查看标签详情出现异常");
        }
    }

    /**
     * 获取标签列表
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<TagInfo> listTags(TagInfo record) throws Exception {
        try {
            return tagInfoMapper.listTags(record);
        } catch (Exception e) {
            throw new MyException("获取标签列表出现异常");
        }
    }

    /**
     * 更新标签信息
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(TagInfo record) throws Exception {
        if (ValidUtil.isEmpty(record.getTagName())) {
            throw new MyException("标签名不能为空");
        }
        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return tagInfoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("编辑标签出现异常");
        }
    }
}
