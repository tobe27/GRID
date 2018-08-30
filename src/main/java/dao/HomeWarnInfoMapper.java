package dao;

import model.HomeWarnInfo;

import java.util.List;

public interface HomeWarnInfoMapper {
    List<HomeWarnInfo> listInfo(HomeWarnInfo record);
    int insertSelective(HomeWarnInfo record);
}