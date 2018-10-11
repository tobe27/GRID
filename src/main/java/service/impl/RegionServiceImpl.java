package service.impl;

import dao.RegionMapper;
import exception.MyException;
import model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RegionService;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;

    /**
     * 删除区域
     *
     * @param regionCode
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long regionCode) throws Exception {
        try {
            return regionMapper.deleteByPrimaryKey(regionCode) == 1;
        } catch (Exception e){
            throw new MyException("删除行政区域出现异常");
        }
    }

    /**
     * 新增区域
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(Region record) throws Exception {
        if (record.getRegionCode() == null || record.getRegionName() == null || record.getRegionName().isEmpty()) {
            throw new MyException("行政区域代码或名称不能为空");
        }
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            //插入前判断区域代码是否存在
            if (regionMapper.getRegionByPrimaryKey(record.getRegionCode()) != null) {
                throw new MyException("行政区域代码已存在");
            }
            return regionMapper.insertSelective(record) == 1;
        } catch (Exception e){
            throw new MyException("新增行政区域出现异常");
        }
    }

    /**
     * 获取区域详情
     *
     * @param regionCode
     * @return
     * @throws Exception
     */
    @Override
    public Region getRegionByPrimaryKey(Long regionCode) throws Exception {
        try {
            return regionMapper.getRegionByPrimaryKey(regionCode);
        } catch (Exception e){
            throw new MyException("获取行政区域详情出现异常");
        }
    }

    /**
     * 获取区域列表
     *
     * @param preRegionCode
     * @return
     * @throws Exception
     */
    @Override
    public List<Region> getRegionsByPrimaryKey(Long preRegionCode) throws Exception {
        try {
            return regionMapper.getRegionsByPrimaryKey(preRegionCode);
        } catch (Exception e){
            throw new MyException("获取行政区域列表出现异常");
        }
    }

    /**
     * 获取所有区域列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<Region> listRegions(Region record) throws Exception {
        try {
            return regionMapper.listRegions(record);
        } catch (Exception e){
            throw new MyException("获取所有行政区域出现异常");
        }
    }

    /**
     * 编辑区域
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(Region record) throws Exception {
        if (record.getRegionCode() == null || record.getRegionName() == null || record.getRegionName().isEmpty()) {
            throw new MyException("行政区域代码或名称不能为空");
        }
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return regionMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e){
            throw new MyException("编辑行政区域出现异常");
        }
    }
}
