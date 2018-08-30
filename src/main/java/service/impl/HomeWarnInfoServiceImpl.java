package service.impl;

import dao.HomeWarnInfoMapper;
import exception.MyException;
import model.HomeWarnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HomeWarnInfoService;

import java.util.List;

@Service
public class HomeWarnInfoServiceImpl implements HomeWarnInfoService {
    @Autowired
    HomeWarnInfoMapper warnInfoMapper;

    /**
     * 获取存贷款异动信息列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<HomeWarnInfo> listInfo(HomeWarnInfo record) throws Exception {
        try {
            return warnInfoMapper.listInfo(record);
        } catch (Exception e) {
            throw new MyException("获取存贷款异动信息列表出现异常");
        }
    }

    /**
     * 新增存贷款异动信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(HomeWarnInfo record) throws Exception {
        try {
            return warnInfoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("新增存贷款异动信息出现异常");
        }
    }
}
