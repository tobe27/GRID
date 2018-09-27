package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.HomeWarnInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.HomeWarnInfoService;

import java.util.List;

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
    public ResponseData listWarnInfoForPresident(HomeWarnInfo info,Integer pageNum, Integer pageSize) throws Exception {
        if (pageNum == null || pageSize == null) {
            return new ResponseData().fail("页码或者页大小不能为空");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<HomeWarnInfo> list = infoService.listInfo(info);
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("董事长存贷异动列表为空");
        }
        PageInfo<HomeWarnInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().data(pageInfo.getList());

    }

    /**
     * 调用此接口获取中层领导看到的存贷款异动信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/middle/home/warn", method = RequestMethod.GET)
    public ResponseData listWarnInfoForMid(HomeWarnInfo info, Integer pageNum, Integer pageSize) throws Exception {
        if (pageNum == null || pageSize == null) {
            return new ResponseData().fail("页码或者页大小不能为空");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<HomeWarnInfo> list = infoService.listInfo(info);
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("中层存贷异动列表为空");
        }
        PageInfo<HomeWarnInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().data(pageInfo.getList());

    }

    /**
     * 调用此接口获取基层领导看到的存贷款异动信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/basic/home/warn", method = RequestMethod.GET)
    public ResponseData listWarnInfoForBasic(HomeWarnInfo info, Integer pageNum, Integer pageSize) throws Exception {
        if (pageNum == null || pageSize == null) {
            return new ResponseData().fail("页码或者页大小不能为空");
        }
        if (info.getOrgCode() == null || info.getOrgLevel() == null) {
            return new ResponseData().fail("机构代码或者机构级别不能为空");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<HomeWarnInfo> list = infoService.listInfo(info);
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("基层存贷异动列表为空");
        }
        PageInfo<HomeWarnInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().data(pageInfo.getList());

    }

}
