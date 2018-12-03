package service.impl;

import dao.HomeStatisticsInfoMapper;
import exception.MyException;
import model.HomeStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HomeStatisticsInfoService;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class HomeStatisticsInfoServiceImpl implements HomeStatisticsInfoService {
    @Autowired
    HomeStatisticsInfoMapper statisticsInfoMapper;

    /**
     * 获取首页金额或者人数统计列表-分为月季年三种
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<HomeStatisticsInfo> listInfo(HomeStatisticsInfo record) throws Exception {
        if (record.getOrgCode() == null) {
            throw new MyException("机构代码不能为空");
        }
        if (record.getTimeType() == null){
            throw new MyException("时间类型不能为空");
        }
        if (record.getAmountType() == null){
            throw new MyException("存贷类型不能为空");
        }
        try {
            return statisticsInfoMapper.listInfo(record);
        }catch (Exception e) {
            throw new MyException("获取首页金额人数统计列表出现异常");
        }
    }

    /**
     * 新增首页金额或者人数统计列表-分为月季年三种
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(HomeStatisticsInfo record) throws Exception {
        try {
            return statisticsInfoMapper.insertSelective(record) == 1;
        }catch (Exception e) {
            throw new MyException("新增首页金额人数统计列表出现异常");
        }
    }
}
