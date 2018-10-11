package service;

import model.HomeWarnInfo;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface HomeWarnInfoService {

    /**
     * 获取存贷款异动信息列表
     * @param record
     * @return
     * @throws Exception
     */
    List<HomeWarnInfo> listInfo(HomeWarnInfo record) throws Exception;

    /**
     * 新增存贷款异动信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(HomeWarnInfo record) throws Exception;
}
