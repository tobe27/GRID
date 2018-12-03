package controller;

import model.Region;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.RegionService;
import util.TreeUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping("/super")
public class RegionController {
    @Autowired
    RegionService regionService;

    /**
     * 调用此接口新增行政区域
     * @param region
     * @return
     */
    @RequestMapping(value = "/region", method = RequestMethod.POST)
    public ResponseData insertRegion(Region region) throws Exception {

        regionService.insertSelective(region);
        return new ResponseData().success();

    }

    /**
     * 调用此接口编辑行政区域
     * @param region
     * @return
     */
    @RequestMapping(value = "/region/{regionCode}", method = RequestMethod.PUT)
    public ResponseData updateRegion(Region region) throws Exception {

        regionService.updateByPrimaryKeySelective(region);
        return new ResponseData().success();

    }

    /**
     * 调用此接口删除行政区域
     * @param regionCode
     * @return
     */
    @RequestMapping(value = "/region/{regionCode}", method = RequestMethod.DELETE)
    public ResponseData deleteRegion(@PathVariable Long regionCode) throws Exception {
        if (regionCode == null) {
            return new ResponseData().fail("行政区域代码不能为空!");
        }

        regionService.deleteByPrimaryKey(regionCode);
        return new ResponseData().success();

    }

    /**
     * 调用此接口获取行政区域列表
     * @param regionCode
     * @return
     */
    @RequestMapping(value = "/region/{regionCode}", method = RequestMethod.GET)
    public ResponseData getRegion(@PathVariable Long regionCode) throws Exception {
        List<Region> regionList = regionService.listRegions(new Region());
        return new ResponseData().success().data(new TreeUtil().getRegionList(regionList, regionCode));
    }


}
