package service.impl;

import dao.PlatformInfoMapper;
import exception.MyException;
import model.PlatformInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PlatformInfoService;
import util.ValidUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author Created by L.C.Y on 2018-11-9
 */
@Service
public class PlatformInfoServiceImpl implements PlatformInfoService {
    @Autowired
    PlatformInfoMapper platformInfoMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除平台信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long id) throws Exception {
        try {
            return platformInfoMapper.deleteByPrimaryKey(id) == 1;
        } catch (Exception e) {
            logger.info("删除平台信息异常："+e.getMessage());
            throw new MyException("删除工作平台信息出现异常");
        }
    }

    /**
     * 新建平台信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(PlatformInfo record) throws Exception {
        if (!ValidUtil.isNumber(record.getType())) {
            throw new MyException("待办类型格式不支持");
        }
        if (ValidUtil.isEmpty(record.getDescription())) {
            throw new MyException("待办说明不能为空");
        }
        if (!ValidUtil.isNumber(record.getOriginatorId())) {
            throw new MyException("发起人编号格式不支持");
        }
        if (!ValidUtil.isNumber(record.getEntrance())) {
            throw new MyException("快捷入口格式不支持");
        }
        if (!ValidUtil.isNumber(record.getHandlerId())) {
            throw new MyException("处理人编号格式不支持");
        }
        if (!ValidUtil.isNumber(record.getStatus())) {
            throw new MyException("状态格式不支持");
        }

        // 创建时间
        long now = System.currentTimeMillis();
        record.setTaskUuid(UUID.randomUUID().toString()); // 任务ID唯一标识
        record.setStartTime(now); // 开始时间
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return platformInfoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            logger.info("新建平台信息异常："+e.getMessage());
            throw new MyException("新建工作平台信息出现异常");
        }
    }

    /**
     * 发起平台信息
     *
     * @param type 待办类型
     * @param description 待办说明
     * @param originatorId 发起人编号
     * @param status 状态
     * @param entrance 快捷入口
     * @param handlerIdList 处理人编号列表,ID
     * @return
     * @throws Exception
     */
    @Override
    public boolean sendPlatformInfo(Integer type, String description, Long originatorId, Integer status, Integer entrance, List<Long> handlerIdList) throws Exception {
        PlatformInfo platformInfo = new PlatformInfo();
        platformInfo.setType(type)
                .setDescription(description)
                .setOriginatorId(originatorId)
                .setStatus(status)
                .setEntrance(entrance);
        int total = 0;
        for (Long handlerId : handlerIdList) {
            platformInfo.setHandlerId(handlerId);
            insertSelective(platformInfo);
            total++;
        }
        return handlerIdList.size() == total;
    }

    /**
     * 查询平台详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public PlatformInfo getByPrimaryKey(Long id) throws Exception {
        try {
            return platformInfoMapper.getByPrimaryKey(id);
        } catch (Exception e) {
            logger.info("查询平台信息异常："+e.getMessage());
            throw new MyException("查询工作平台信息出现异常");
        }
    }

    /**
     * 查询平台列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<PlatformInfo> listByHandlerId(PlatformInfo record) throws Exception {
        try {
            return platformInfoMapper.listByHandlerId(record);
        } catch (Exception e) {
            logger.info("查询平台列表异常："+e.getMessage());
            throw new MyException("查询工作平台信息列表出现异常");
        }
    }

    /**
     * 编辑平台信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(PlatformInfo record) throws Exception {
        // 创建时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return platformInfoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑平台信息异常："+e.getMessage());
            throw new MyException("编辑工作平台信息出现异常");
        }
    }
}
