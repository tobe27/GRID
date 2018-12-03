package controller;


import com.github.pagehelper.PageInfo;

import model.CustomerImage;
import model.GridInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import service.GridInfoService;
import util.ValidUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class GridInfoController {

    @Autowired
    private GridInfoService gridInfoService;

    /**
     * 调用此接口新增网格信息
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gridinfo",method = RequestMethod.POST)
    public ResponseData addGridInfo(@RequestBody GridInfo gridInfo) {
        try {
            gridInfoService.insertSelective(gridInfo);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口修改网格信息
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gridinfo/{gridCode}",method = RequestMethod.PUT)
    public ResponseData updateGridInfo(@RequestBody GridInfo gridInfo) {
        try {
            gridInfoService.updateByPrimaryKeySelective(gridInfo);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口查询网格信息
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gridinfo/{id}",method = RequestMethod.GET)
    public ResponseData updateGridInfo(@PathVariable long id) throws Exception {
        return new ResponseData().success().data(gridInfoService.selectByPrimaryKey(id));

    }


    /**
     * 调用此接口删除网格信息
     * @param
     * @return
     */
    @RequestMapping(value = "/gridinfo/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteGridInfo(@PathVariable long id) {
        try {
            gridInfoService.deleteByPrimaryKey(id);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }
    /**
     * 分页和条件显示网格列表
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gridinfo/list", method = RequestMethod.GET)
    public ResponseData getListByPage(@RequestParam Map<String,Object>  map, Long roleId) throws Exception {
        if (ValidUtil.isEmpty(roleId)) {
            return new ResponseData().fail("角色不能为空！");
        }
        if (roleId == 7) {  // 如果是管理员可以查看所有网格
            map.remove("orgCode");
        }
        if (roleId != 1) {  // 如果不是客户经理只能查看相应机构的网格
            map.remove("accountId");
        }
        PageInfo<Map<String, Object>> pageInfo ;

        List<Map<String,Object>> list=  gridInfoService.getGridInfoList(map);
        pageInfo=new PageInfo<>(list);

        return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());

    }

    /**
     * 调用此接口获取登陆人能看到的所有网格信息
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gridinfo/list/account", method = RequestMethod.GET)
    public ResponseData getGridInfoListByCondition(@RequestParam Map<String,Object>  map) throws Exception {
        return new ResponseData().success().data( gridInfoService.getGridInfoListByAccount(map));

    }
    /**
     * 调用此接口上传网格的图片
     * @param file id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gridinfo/image", method = RequestMethod.POST)
    public ResponseData gridMapImage(@RequestParam MultipartFile file,long id) throws Exception {
    	gridInfoService.saveGridImage(file, id);
        return new ResponseData().success();

    }
    /**
     * 调用此接口获取网格的图片
     * @param file id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gridinfo/image/{id}", method = RequestMethod.GET)
    public ResponseData getGridMapImage(@PathVariable long id) throws Exception {
        return new ResponseData().success().data(gridInfoService.getGridMapImage(id));
    }

}
