package controller;

import model.Region;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.RegionService;

import java.util.List;

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
    public ResponseData insertRegion(Region region) {
        if (region.getRegionCode() == null || region.getRegionName() == null || region.getRegionName().isEmpty()) {
            return new ResponseData().code(400).message("行政区域代码或名称不能为空");
        }

        try {
            //插入前判断区域代码是否存在
            if (regionService.getRegionByPrimaryKey(region.getRegionCode()) != null) {
                return new ResponseData().code(400).message("行政区域代码已存在");
            }
            regionService.insertSelective(region);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

    /**
     * 调用此接口编辑行政区域
     * @param regionCode
     * @param region
     * @return
     */
    @RequestMapping(value = "/region/{regionCode}", method = RequestMethod.PUT)
    public ResponseData updateRegion(@PathVariable Long regionCode, Region region) {
        if (regionCode == null || region.getRegionName() == null || region.getRegionName().isEmpty()) {
            return new ResponseData().code(400).message("行政区域代码或名称不能为空");
        }
        region.setRegionCode(regionCode);
        try {
            regionService.updateByPrimaryKeySelective(region);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

    /**
     * 调用此接口删除行政区域
     * @param regionCode
     * @return
     */
    @RequestMapping(value = "/region/{regionCode}", method = RequestMethod.DELETE)
    public ResponseData deleteRegion(@PathVariable Long regionCode) {
        if (regionCode == null) {
            return new ResponseData().code(400).message("行政区域代码不能为空");
        }
        try {
            regionService.deleteByPrimaryKey(regionCode);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

    /**
     * 调用此接口获取行政区域列表
     * @param regionCode
     * @return
     */
    @RequestMapping(value = "/region/{regionCode}", method = RequestMethod.GET)
    public ResponseData getRegion(@PathVariable Long regionCode) {
        if (regionCode == null) {
            return new ResponseData().code(400).message("行政区域代码不能为空");
        }

        try {
            Region region = regionService.getRegionByPrimaryKey(regionCode);
            List<Region> nextRegion = regionService.getRegionsByPrimaryKey(regionCode);
            return new ResponseData().success().data(region).result("nextRegion", nextRegion);
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
    }

}
