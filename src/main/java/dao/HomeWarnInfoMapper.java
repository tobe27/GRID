package dao;

import model.HomeWarnInfo;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface HomeWarnInfoMapper {
    List<HomeWarnInfo> listInfo(HomeWarnInfo record);
    int insertSelective(HomeWarnInfo record);
}