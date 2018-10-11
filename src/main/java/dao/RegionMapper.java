package dao;

import model.Region;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface RegionMapper {
    int deleteByPrimaryKey(Long regionCode);

    int insertSelective(Region record);

    Region getRegionByPrimaryKey(Long regionCode);

    List<Region> getRegionsByPrimaryKey(Long preRegionCode);

    List<Region> listRegions(Region record);

    int updateByPrimaryKeySelective(Region record);

}