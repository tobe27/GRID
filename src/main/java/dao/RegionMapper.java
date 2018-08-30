package dao;

import model.Region;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Long regionCode);

    int insertSelective(Region record);

    Region getRegionByPrimaryKey(Long regionCode);

    List<Region> getRegionsByPrimaryKey(Long preRegionCode);

    int updateByPrimaryKeySelective(Region record);

}