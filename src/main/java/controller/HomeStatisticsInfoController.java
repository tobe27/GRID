package controller;

import model.HomeStatisticsInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.HomeStatisticsInfoService;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping("/home/statistics")
public class HomeStatisticsInfoController {
    @Autowired
    HomeStatisticsInfoService infoService;

    /**
     * 调用此接口获取董事长首页金额和人数统计信息列表
     * @param info
     * @return
     */
    @RequestMapping(value = "/president",method = RequestMethod.GET)
    public ResponseData listInfoForPresident(HomeStatisticsInfo info) throws Exception {

        return new ResponseData().success().data(infoService.listInfo(info));

    }

    /**
     * 调用此接口获取中层首页金额和人数统计信息列表
     * @param info
     * @return
     */
    @RequestMapping(value = "/middle",method = RequestMethod.GET)
    public ResponseData listInfoForMid(HomeStatisticsInfo info) throws Exception {

        return new ResponseData().success().data(infoService.listInfo(info));

    }

    /**
     * 调用此接口获取基层首页金额和人数统计信息列表
     * @param info
     * @return
     */
    @RequestMapping(value = "/basic",method = RequestMethod.GET)
    public ResponseData listInfoForBasic(HomeStatisticsInfo info) throws Exception {

        return new ResponseData().success().data(infoService.listInfo(info));

    }
}
