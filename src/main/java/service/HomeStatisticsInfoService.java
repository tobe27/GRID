package service;

import model.HomeStatisticsInfo;

import java.util.List;

public interface HomeStatisticsInfoService {

    /**
     * 获取首页金额或者人数统计列表-分为月季年三种
     * @param record
     * @return
     * @throws Exception
     */
    List<HomeStatisticsInfo> listInfo(HomeStatisticsInfo record) throws Exception;

    /**
     * 新增首页金额或者人数统计列表-分为月季年三种
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(HomeStatisticsInfo record) throws Exception;
}
