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
        try {
            regionService.insertSelective(region);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口编辑行政区域
     * @param region
     * @return
     */
    @RequestMapping(value = "/region/{regionCode}", method = RequestMethod.PUT)
    public ResponseData updateRegion(Region region) {
        try {
            regionService.updateByPrimaryKeySelective(region);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
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
            return new ResponseData().fail("行政区域代码不能为空");
        }
        try {
            regionService.deleteByPrimaryKey(regionCode);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
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
            return new ResponseData().fail("行政区域代码不能为空");
        }

        try {
            Region region = regionService.getRegionByPrimaryKey(regionCode);
            List<Region> nextRegion = regionService.getRegionsByPrimaryKey(regionCode);
            return new ResponseData().success().data(region).result("nextRegion", nextRegion);
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

}
