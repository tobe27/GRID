package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import concurrent.DishonestQueryThread;
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
import service.ResidentInfoService;
import util.ValidUtil;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping
public class DishonestCustomerInfoController {
    @Autowired
    DishonestCustomerInfoService dishonestCustomerInfoService;
    @Autowired
    ResidentInfoService residentInfoService;

    // 创建线程池，核心线程数是5
    private static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

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
            return new ResponseData().fail("页码和页大小不能为空!");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<DishonestCustomerInfo> list = dishonestCustomerInfoService.listByPerformedNameOrCardNumber(info);
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("数据库中暂无失信人记录信息!");
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
        DishonestCustomerInfo info = dishonestCustomerInfoService.getByPrimaryKey(id);
        if (ValidUtil.isEmpty(info)) {
            return new ResponseData().blank("没有该客户失信信息!");
        }
        return new ResponseData().success().data(info);
    }

    /**
     * 调用此接口爬取户籍失信人信息并导入数据库
     * 通过线程执行，异步
     * @return
     */
    @RequestMapping(value = "/customer/dishonesty", method = RequestMethod.POST)
    public ResponseData queryInfo() throws Exception {
        // 开启线程执行导入
        executor.execute(new DishonestQueryThread(residentInfoService, dishonestCustomerInfoService));
        return new ResponseData().success();
    }

}
