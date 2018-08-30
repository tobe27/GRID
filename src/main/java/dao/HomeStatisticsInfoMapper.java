package dao;

import model.HomeStatisticsInfo;

import java.util.List;

public interface HomeStatisticsInfoMapper {
    List<HomeStatisticsInfo> listInfo(HomeStatisticsInfo record);
    int insertSelective(HomeStatisticsInfo record);
}