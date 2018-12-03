package controller;

import model.GridSenator;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.GridSenatorService;

/**
 * 网格评议员
 * @author Created by L.C.Y on 2018-11-23
 */
@RestController
@RequestMapping("/gridinfo")
public class GridSenatorController {
    @Autowired
    GridSenatorService gridSenatorService;

    /**
     * 删除
     * @param senatorId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/senator/{senatorId}", method = RequestMethod.DELETE)
    public ResponseData delete(@PathVariable Long senatorId) throws Exception {
        gridSenatorService.deleteByPrimaryKey(senatorId);
        return new ResponseData().success();
    }

    /**
     * 新建
     * @param senator
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/senator", method = RequestMethod.POST)
    public ResponseData insert(GridSenator senator) throws Exception {
        gridSenatorService.insertSelective(senator);
        return new ResponseData().success().data(senator.getSenatorId());
    }

    /**
     * 编辑
     * @param senator
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/senator/{senatorId}", method = RequestMethod.PUT)
    public ResponseData update(GridSenator senator) throws Exception {
        gridSenatorService.updateByPrimaryKeySelective(senator);
        return new ResponseData().success();
    }

    /**
     * 获取
     * @param senatorId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/senator/{senatorId}", method = RequestMethod.GET)
    public ResponseData get(@PathVariable Long senatorId) throws Exception {
        return new ResponseData().success().data(gridSenatorService.selectByPrimaryKey(senatorId));
    }

    /**
     * 列表
     * @param senator
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/senator/list", method = RequestMethod.GET)
    public ResponseData list(GridSenator senator) throws Exception {
        return new ResponseData().success().data(gridSenatorService.listByType(senator));
    }

    /**
     * 网格号查询列表
     * @param senator
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/senator/list/{gridCode}", method = RequestMethod.GET)
    public ResponseData listByGridCode(GridSenator senator) throws Exception {
        return new ResponseData().success().data(gridSenatorService.listByGridCode(senator));
    }


}
