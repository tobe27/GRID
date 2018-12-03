package service.impl;

import dao.TagCustomerMapper;
import dao.TagInfoMapper;
import exception.MyException;
import model.TagInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.TagInfoService;
import util.ValidUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class TagInfoServiceImpl implements TagInfoService {
    @Autowired
    TagInfoMapper tagInfoMapper;
    @Autowired
    TagCustomerMapper tagCustomerMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除标签信息
     * @param tagId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean deleteByPrimaryKey(Long tagId) throws Exception {
        if (tagId == 1 || tagId == 2 || tagId == 3 || tagId == 4 || tagId == 5) {
            throw new MyException("系统标签不可删除！");
        }

        try {
            int count = tagInfoMapper.deleteByPrimaryKey(tagId);
            int count1 = tagCustomerMapper.deleteTagByTagId(tagId);
            return count == 1 && count1 == 1;
        } catch (Exception e) {
            logger.info("删除标签异常："+e.getMessage());
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

        if (tagInfoMapper.getByTagName(record.getTagName()) != null) {
            throw new MyException("标签名已存在！");
        }
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return tagInfoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            logger.info("新建标签异常："+e.getMessage());
            throw new MyException("新建标签出现异常");
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
            logger.info("查询标签异常："+e.getMessage());
            throw new MyException("查询标签详情出现异常");
        }
    }

    /**
     * 通过标签名获取详情
     *
     * @param tagName
     * @return
     * @throws Exception
     */
    @Override
    public TagInfo getByTagName(String tagName) throws Exception {
        try {
            return tagInfoMapper.getByTagName(tagName);
        } catch (Exception e) {
            logger.info("通过标签名查询标签异常："+e.getMessage());
            throw new MyException("通过标签名查询详情出现异常");
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
            logger.info("查询标签列表异常："+e.getMessage());
            throw new MyException("查询标签列表出现异常");
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
            logger.info("编辑标签异常："+e.getMessage());
            if (tagInfoMapper.getByTagName(record.getTagName()) != null) {
                throw new MyException("标签名已存在！");
            }
            throw new MyException("编辑标签出现异常");
        }
    }
}
