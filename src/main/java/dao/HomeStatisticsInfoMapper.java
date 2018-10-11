package dao;

import model.HomeStatisticsInfo;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface HomeStatisticsInfoMapper {
    List<HomeStatisticsInfo> listInfo(HomeStatisticsInfo record);
    int insertSelective(HomeStatisticsInfo record);
}