package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CustomerInfoService;
import service.CustomerPoorInfoService;
import service.FamilyMemberService;
import service.ResidentInfoService;
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
    @Autowired
    CustomerPoorInfoService customerPoorInfoService;

    /**
     * 调用此接口获取客户信息
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/customer/info/{customerId}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/customer/info/idnumber/{idNumber}", method = RequestMethod.GET)
    public ResponseData getCustomer(@PathVariable String idNumber) throws Exception {
        if (!ValidUtil.isLength(idNumber,18)) {
            return new ResponseData().fail("身份证号码长度错误");
        }
        CustomerInfo customerInfo = customerInfoService.getCustomerByIdNumber(idNumber);
        if (ValidUtil.isEmpty(customerInfo)) {
            return new ResponseData().blank("该客户不存在");
        }
        return new ResponseData().success().data(customerInfo);
    }

    /**
     * 获取申请表信息
     * @param customerId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/info/form/{customerId}", method = RequestMethod.GET)
    public ResponseData getForm(@PathVariable Long customerId) throws Exception {
        return new ResponseData().success().data(customerInfoService.getCustomerForm(customerId));
    }


    /**
     * 调用此接口条件-分页获取客户列表
     * @param info
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/customer/info/list", method = RequestMethod.GET)
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
     * 调用此接口根据用户获取客户列表
     * @param roleId
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/info/list/{accountId}", method = RequestMethod.GET)
    public ResponseData listCustomerByAccount(CustomerInfo info, Long roleId, Integer pageNum, Integer pageSize) throws Exception {
        if (pageNum == null || pageSize == null) {
            return new ResponseData().fail("页码和页大小不能为空");
        }

        if (ValidUtil.isEmpty(info.getAccountId()) || ValidUtil.isEmpty(info.getOrgCode()) || ValidUtil.isEmpty(roleId)) {
            return new ResponseData().fail("请求参数不全");
        }

        PageHelper.startPage(pageNum,pageSize);
        List<CustomerInfo> list;
        if (roleId == 1) { // 如果是客户经理A，则只显示相应网格下的客户
            list = customerInfoService.listCustomersByAccountId(info);
        } else { // 如果是其他用户，则显示机构下所有的客户
            list = customerInfoService.listCustomersByOrgCode(info);
        }

        if (list == null || list.isEmpty()) {
            return new ResponseData().blank("客户列表不存在!");
        }
        PageInfo<CustomerInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().result("count", pageInfo.getTotal()).data(pageInfo.getList());

    }

    /**
     * 调用此接口新增客户信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/info", method = RequestMethod.POST)
    public ResponseData insertCustomer(CustomerInfo info) throws Exception {

        customerInfoService.insertSelective(info);
        return new ResponseData().success().data(info.getCustomerId());
    }


    /**
     * 调用此接口新增客户信息-草稿
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/info/draft", method = RequestMethod.POST)
    public ResponseData insertDraft(CustomerInfo info) throws Exception {

        customerInfoService.insertDraft(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口导入户籍信息到客户信息
     * @param info
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/info/resident/{idNumber}", method = RequestMethod.POST)
    public ResponseData insertByResident(CustomerInfo info) throws Exception {
        customerInfoService.insertByResidentInfo(info);
        return new ResponseData().success();
    }

    /**
     * 调用此接口编辑客户信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/info/{customerId}", method = RequestMethod.PUT)
    public ResponseData updateCustomer(CustomerInfo info) throws Exception {

        customerInfoService.updateByPrimaryKeySelective(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口编辑客户信息-草稿
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/info/draft/{customerId}", method = RequestMethod.PUT)
    public ResponseData updateDraft(CustomerInfo info) throws Exception {

        customerInfoService.updateDraft(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口编辑客户信息状态
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/info/{customerId}/status/{status}", method = RequestMethod.PUT)
    public ResponseData updateCustomerStatus(CustomerInfo info) throws Exception {

        customerInfoService.updateCustomerStatus(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口批量编辑客户信息状态
     * @param list
     * @return
     */
    @RequestMapping(value = "/customer/info/status/batch", method = RequestMethod.PUT)
    public ResponseData batchUpdateCustomerStatus(@RequestBody List<CustomerInfo> list) throws Exception {

        customerInfoService.batchUpdateStatus(list);
        return new ResponseData().success();

    }

    /**
     * 调用此接口删除客户信息
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/customer/info/{customerId}", method = RequestMethod.DELETE)
    public ResponseData deleteCustomer(@PathVariable Long customerId) throws Exception {

        customerInfoService.deleteByPrimaryKey(customerId);
        return new ResponseData().success();

    }
    
    
    /**
     * 调用此接口查询客户贫困信息
     * @param idNumber
     * @return
     */
   @RequestMapping(value = "/customer/poorinfo/{idNumber}", method = RequestMethod.GET)
   public ResponseData getCustomerPoorInfo(@PathVariable String idNumber) throws Exception {
       CustomerPoorInfo customerPoorInfo=new CustomerPoorInfo();
       customerPoorInfo.setIdNumber(idNumber);
       List<CustomerPoorInfo> list = customerPoorInfoService.getListByIdNumber(customerPoorInfo);
       if (list == null||list.isEmpty()) {
           return new ResponseData().blank("该客户不存在!");
       }
       return new ResponseData().success().data(list);

   }




}
