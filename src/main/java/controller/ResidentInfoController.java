package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.ResidentInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.ResidentInfoService;
import util.ValidUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping("/customer")
public class ResidentInfoController {
    @Autowired
    ResidentInfoService residentInfoService;

    /**
     * 调用此接口获取居民信息
     * @param residentId
     * @return
     */
    @RequestMapping(value = "/resident/{residentId}", method = RequestMethod.GET)
    public ResponseData getResident(@PathVariable Long residentId) throws Exception {

        ResidentInfo info = residentInfoService.getResidentByPrimaryKey(residentId);
        if (info == null) {
            return new ResponseData().blank();
        }
        return new ResponseData().success().data(info);

    }

    /**
     * 调用此接口获取居民信息
     * @param idNumber
     * @return
     */
    @RequestMapping(value = "/resident/idnumber/{idNumber}", method = RequestMethod.GET)
    public ResponseData getResident(@PathVariable String idNumber) throws Exception {

        ResidentInfo info = residentInfoService.getByIdNumber(idNumber);
        if (info == null) {
            return new ResponseData().blank();
        }
        return new ResponseData().success().data(info);

    }

    /**
     * 调用此接口获取居民列表
     * @param info
     * @return
     */
    @RequestMapping(value = "/resident/list", method = RequestMethod.GET)
    public ResponseData listResidents(ResidentInfo info, Integer pageNum, Integer pageSize) throws Exception {
        if (pageNum == null || pageSize == null) {
            return new ResponseData().fail("页码或者页大小不能为空!");
        }
        if (ValidUtil.isEmpty(info.getRoleId()) || ValidUtil.isEmpty(info.getAccountId()) || ValidUtil.isEmpty(info.getOrgCode())) {
            return new ResponseData().fail("请求参数不全!");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<ResidentInfo> list = null;
        if (info.getRoleId() == 1) {
            list = residentInfoService.listResidents(info);
        } else {
            list = residentInfoService.listByOrg(info);
        }
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank();
        }
        PageInfo<ResidentInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().result("count",pageInfo.getTotal()).data(pageInfo.getList());

    }

    /**
     * 调用此接口新增居民信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/resident", method = RequestMethod.POST)
    public ResponseData insertResident(ResidentInfo info, HttpServletRequest request) throws Exception {
        residentInfoService.insertSelective(info, request);
        return new ResponseData().success();
    }

    /**
     * 调用此接口修改居民信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/resident/{residentId}", method = RequestMethod.PUT)
    public ResponseData updateResident(ResidentInfo info) throws Exception {

        residentInfoService.updateByPrimaryKeySelective(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口修改居民信息的可用状态
     * @param info
     * @return
     */
    @RequestMapping(value = "/resident/{residentId}/status/{status}", method = RequestMethod.PUT)
    public ResponseData updateResidentStatus(ResidentInfo info) throws Exception {

        residentInfoService.updateResidentStatus(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口删除居民信息
     * @param residentId
     * @return
     */
    @RequestMapping(value = "/resident/{residentId}", method = RequestMethod.DELETE)
    public ResponseData deleteResident(@PathVariable Long residentId) throws Exception {

        residentInfoService.deleteByPrimaryKey(residentId);
        return new ResponseData().success();

    }

}
