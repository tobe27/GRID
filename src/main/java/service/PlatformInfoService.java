package service;

import model.PlatformInfo;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-11-9
 */
public interface PlatformInfoService {
    /**
     * 删除平台信息
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long id) throws Exception;

    /**
     * 新建平台信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(PlatformInfo record) throws Exception;

    /**
     * 发起平台信息
     * @param type 待办类型
     * @param description 待办说明
     * @param originatorId 发起人编号
     * @param status 状态
     * @param entrance 快捷入口
     * @param handlerIdList 处理人编号列表
     * @return
     * @throws Exception
     */
    boolean sendPlatformInfo(Integer type, String description, Long originatorId,Integer status, Integer entrance, List<Long> handlerIdList) throws Exception;

    /**
     * 查询平台详情
     * @param id
     * @return
     * @throws Exception
     */
    PlatformInfo getByPrimaryKey(Long id) throws Exception;

    /**
     * 查询平台列表
     * @param record
     * @return
     * @throws Exception
     */
    List<PlatformInfo> listByHandlerId(PlatformInfo record) throws Exception;

    /**
     * 编辑平台信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(PlatformInfo record) throws Exception;
}
