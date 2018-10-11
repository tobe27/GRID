package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.CustomerInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.CustomerInfoService;
import util.ValidUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping
public class CustomerInfoController {
    @Autowired
    CustomerInfoService customerInfoService;

    /**
     * 调用此接口获取客户信息
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseData getCustomer(@PathVariable Long customerId) throws Exception {

        CustomerInfo info = customerInfoService.getCustomerByPrimaryKey(customerId);
        if (info == null) {
            return new ResponseData().blank("该客户不存在");
        }
        return new ResponseData().success().data(info);

    }

    /**
     * 调用此接口获取客户信息
     * @param idNumber 身份证
     * @return
     */
    @RequestMapping(value = "/customer/idnumber/{idNumber}", method = RequestMethod.GET)
    public ResponseData getCustomer(@PathVariable String idNumber) throws Exception {
        if (!ValidUtil.length(idNumber,18)) {
            return new ResponseData().fail("身份证号码长度错误");
        }
        CustomerInfo info = new CustomerInfo();
        info.setIdNumber(idNumber);
        List<CustomerInfo> list = customerInfoService.listCustomers(info);
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("该客户不存在");
        }
        return new ResponseData().success().data(list);
    }

    /**
     * 调用此接口条件-分页获取客户列表
     * @param info
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/customer/list", method = RequestMethod.GET)
    public ResponseData listCustomers(CustomerInfo info, Integer pageNum, Integer pageSize) throws Exception {
        if (pageNum == null || pageSize == null) {
            return new ResponseData().fail("页码和页大小不能为空");
        }

        PageHelper.startPage(pageNum,pageSize);
        List<CustomerInfo> list = customerInfoService.listCustomers(info);
        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("客户列表不存在");
        }
        PageInfo<CustomerInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().result("count", pageInfo.getTotal()).data(pageInfo.getList());

    }

    /**
     * 调用此接口新增客户信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseData insertCustomer(CustomerInfo info) throws Exception {

        customerInfoService.insertSelective(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口编辑客户信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.PUT)
    public ResponseData updateCustomer(CustomerInfo info) throws Exception {

        customerInfoService.updateByPrimaryKeySelective(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口删除客户信息
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.DELETE)
    public ResponseData deleteCustomer(@PathVariable Long customerId) throws Exception {

        customerInfoService.deleteByPrimaryKey(customerId);
        return new ResponseData().success();

    }


}
