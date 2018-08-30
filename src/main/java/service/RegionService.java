package service;

import model.Region;

import java.util.List;

public interface RegionService {
    /**
     * 删除区域
     * @param regionCode
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long regionCode) throws Exception;

    /**
     * 新增区域
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(Region record) throws Exception;

    /**
     * 获取区域详情
     * @param regionCode
     * @return
     * @throws Exception
     */
    Region getRegionByPrimaryKey(Long regionCode) throws Exception;

    /**
     * 获取区域列表
     * @param preRegionCode
     * @return
     * @throws Exception
     */
    List<Region> getRegionsByPrimaryKey(Long preRegionCode) throws Exception;

    /**
     * 编辑区域
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(Region record) throws Exception;
}
