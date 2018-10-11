package service;

import model.ResidentInfo;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface ResidentInfoService {
    /**
     * 删除居民信息
     * @param residentId
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long residentId) throws Exception;

    /**
     * 新增居民信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(ResidentInfo record) throws Exception;

    /**
     * 获取居民详情
     * @param residentId
     * @return
     * @throws Exception
     */
    ResidentInfo getResidentByPrimaryKey(Long residentId) throws Exception;

    /**
     * 获取居民列表
     * @param record
     * @return
     * @throws Exception
     */
    List<ResidentInfo> listResidents(ResidentInfo record) throws Exception;

    /**
     * 编辑居民信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(ResidentInfo record) throws Exception;
    /**
     * 批量新增居民信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean batchSave(List<ResidentInfo> list) throws Exception;
}
