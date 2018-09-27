package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.DishonestCustomerInfo;
import model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.DishonestCustomerInfoService;
import util.DishonestUtil;
import util.ValidUtil;

import java.util.List;

@RestController
@RequestMapping
public class DishonestCustomerInfoController {
    @Autowired
    DishonestCustomerInfoService infoService;

    private Logger logger = LoggerFactory.getLogger(DishonestCustomerInfoController.class);

    /**
     * 调用此接口获取失信人列表，通过证件可以精确查询
     * @param info
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/dishonesty/list",method = RequestMethod.GET)
    public ResponseData listInfo(DishonestCustomerInfo info, Integer pageNum, Integer pageSize) throws Exception {
        if (pageNum == null || pageSize == null){
            return new ResponseData().fail("页码和页大小不能为空");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<DishonestCustomerInfo> list = infoService.listByPerformedNameOrCardNumber(info);
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("数据库中暂无失信人记录信息");
        }
        PageInfo<DishonestCustomerInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().result("count",pageInfo.getTotal()).data(pageInfo.getList());
    }

    /**
     * 调用此接口获取失信人详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/dishonesty/{id}", method = RequestMethod.GET)
    public ResponseData getInfo(@PathVariable Long id) throws Exception {
        DishonestCustomerInfo info = infoService.getByPrimaryKey(id);
        if (ValidUtil.isEmpty(info)) {
            return new ResponseData().blank("没有该客户失信信息");
        }
        return new ResponseData().success().data(info);
    }

    /**
     * 调用此接口爬取官方失信人信息
     * @param cardNumber
     * @param performedName
     * @return
     */
    @RequestMapping(value = "/customer/dishonesty", method = RequestMethod.POST)
    public ResponseData queryInfo(String performedName, String cardNumber) throws Exception {
        if (ValidUtil.isEmpty(performedName) || ValidUtil.isEmpty(cardNumber)) {
            return new ResponseData().fail("被执行人姓名/名称不能为空");
        }
        List<DishonestCustomerInfo> list = DishonestUtil.listDishonest(performedName, cardNumber);
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("未查询到该客户失信记录");
        }
        logger.info("接口爬取失信信息："+list);
        for (DishonestCustomerInfo info : list) {
            infoService.insertSelective(info);
        }
        return new ResponseData().success().data(list);
    }

}
