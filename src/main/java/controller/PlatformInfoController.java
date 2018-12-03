package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.PlatformInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PlatformInfoService;
import util.DateConvertUtil;
import util.DatePatternEnum;
import util.ValidUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-11-9
 */
@RestController
@RequestMapping("/platform/info")
public class PlatformInfoController {
    @Autowired
    PlatformInfoService platformInfoService;

    @RequestMapping("/list")
    public ResponseData list(Long accountId, String startTime, String endTime, Integer type, Integer status, Integer pageNum, Integer pageSize) throws Exception {
        if (!ValidUtil.isNumber(accountId) || !ValidUtil.isNumber(pageNum) || !ValidUtil.isNumber(pageSize)) {
            return new ResponseData().fail("请求参数不全");
        }
        PlatformInfo info = new PlatformInfo();
        Long longStartTime = null;
        if (ValidUtil.isNotEmpty(startTime)) {
            longStartTime = DateConvertUtil.convertDate2Long(startTime);
        }
        Long longEndTime = null;
        if (ValidUtil.isNotEmpty(endTime)) {
            longEndTime = DateConvertUtil.convertDate2Long(endTime);
        }
        info.setType(type).setStatus(status).setHandlerId(accountId).setStartTime(longStartTime).setEndTime(longEndTime);
        PageHelper.startPage(pageNum, pageSize);
        List<PlatformInfo> platformInfoList = platformInfoService.listByHandlerId(info);
        PageInfo<PlatformInfo> pageInfo = new PageInfo<>(platformInfoList);

        if (pageInfo.getTotal() == 0) {
            return new ResponseData().blank();
        }
        return new ResponseData().success().result("count", pageInfo.getTotal()).data(pageInfo.getList());
    }
}
