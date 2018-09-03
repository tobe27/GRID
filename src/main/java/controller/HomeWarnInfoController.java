package controller;

import model.HomeWarnInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.HomeWarnInfoService;

@RestController
public class HomeWarnInfoController {
    @Autowired
    HomeWarnInfoService infoService;

    /**
     * 调用此接口获取董事长看到的存贷款异动信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/president/home/warn", method = RequestMethod.GET)
    public ResponseData listWarnInfoForPresident(HomeWarnInfo info) {
        try {
            return new ResponseData().success().data(infoService.listInfo(info));
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }


    /**
     * 调用此接口获取中层领导看到的存贷款异动信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/middle/home/warn", method = RequestMethod.GET)
    public ResponseData listWarnInfoForMid(HomeWarnInfo info) {
        try {
            return new ResponseData().success().data(infoService.listInfo(info));
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口获取基层领导看到的存贷款异动信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/basic/home/warn", method = RequestMethod.GET)
    public ResponseData listWarnInfoForBasic(HomeWarnInfo info) {
        if (info.getOrgCode() == null || info.getOrgLevel() == null) {
            return new ResponseData().fail("机构代码或者机构级别不能为空");
        }
        try {
            return new ResponseData().success().data(infoService.listInfo(info));
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

}
