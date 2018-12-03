package service.impl;

import dao.RegionMapper;
import exception.MyException;
import model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RegionService;
import util.ValidUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除区域
     *
     * @param regionCode
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long regionCode) throws Exception {
        if (regionCode == 1) {
            throw new MyException("总行政区域不可删除，请使用编辑！");
        }
        try {
            return regionMapper.deleteByPrimaryKey(regionCode) == 1;
        } catch (Exception e){
            logger.info("删除区域异常："+e.getMessage());
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
        if (ValidUtil.isNotLength(record.getRegionName(), 1, 100)) {
            throw new MyException("行政区域名称最长100位");
        }
        if (ValidUtil.isNotNumber(record.getPreRegionCode())) {
            throw new MyException("行政区域的上级代码不能为空");
        }
        if (ValidUtil.isEmpty(record.getRegionLevel())) {
            throw new MyException("行政区域等级不能为空");
        }
        if (ValidUtil.isNotEmpty(record.getDescription()) && ValidUtil.isNotLength(record.getDescription(), 1, 100)) {
            throw new MyException("备注最长100字");
        }
        //插入前判断区域名称是否存在
        if (regionMapper.getRegionByRegionName(record.getRegionName()) != null) {
            throw new MyException("行政区域名称已存在！");
        }

        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {

            return regionMapper.insertSelective(record) == 1;
        } catch (Exception e){
            logger.info("新建区域异常："+e.getMessage());
            throw new MyException("新建行政区域出现异常");
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
            logger.info("查询区域异常："+e.getMessage());
            throw new MyException("查询行政区域详情出现异常");
        }
    }

    /**
     * 根据名称获取详情
     *
     * @param regionName
     * @return
     * @throws Exception
     */
    @Override
    public Region getRegionByRegionName(String regionName) throws Exception {
        try {
            return regionMapper.getRegionByRegionName(regionName);
        } catch (Exception e){
            logger.info("通过区域名称查询异常："+e.getMessage());
            throw new MyException("通过区域名称获取行政区域详情出现异常");
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
            logger.info("查询区域列表异常："+e.getMessage());
            throw new MyException("查询行政区域列表出现异常");
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
            logger.info("查询所有区域异常："+e.getMessage());
            throw new MyException("查询所有行政区域出现异常");
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
        if (ValidUtil.isEmpty(record.getRegionName())) {
            throw new MyException("行政区域名称不能为空");
        }
        if (ValidUtil.isEmpty(record.getPreRegionCode())) {
            throw new MyException("行政区域的上级代码不能为空");
        }
        if (ValidUtil.isEmpty(record.getRegionLevel())) {
            throw new MyException("行政区域等级不能为空");
        }

        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return regionMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e){
            logger.info("编辑区域异常："+e.getMessage());
            //插入前判断区域名称是否存在
            if (regionMapper.getRegionByRegionName(record.getRegionName()) != null) {
                throw new MyException("行政区域名称已存在！");
            }
            throw new MyException("编辑行政区域出现异常");
        }
    }
}
