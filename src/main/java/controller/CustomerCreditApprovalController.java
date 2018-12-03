
package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import exception.MyException;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CustomerCreditApprovalService;
import service.CustomerInfoService;
import service.FamilyInfoService;
import service.FinanceInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("customer")
public class CustomerCreditApprovalController {
    @Autowired
    private CustomerCreditApprovalService customerCreditApprovalService;
    @Autowired
    CustomerInfoService customerInfoService;
    @Autowired
    FinanceInfoService financeInfoService;
    @Autowired
    FamilyInfoService familyInfoService;


    /**
     * 调用此接口插入批量审批记录
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/creditapproval",method = RequestMethod.POST)
    public ResponseData addCustomerCreditApproval( @RequestBody Map<String,Object> map) throws Exception {
        if(!map.containsKey("list") || !map.containsKey("roleId") ) {
            throw new MyException("参数缺失");
        }

        ArrayList<CustomerCreditApproval>  list=(ArrayList<CustomerCreditApproval>) map.get("list");
       String roleId= map.get("roleId").toString();
       
        //将接收的数据转换成model
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<CustomerCreditApproval>  customerCreditApprovalList=new ArrayList<>();
        for(int i=0;i<list.size();i++) {
            String json=mapper.writeValueAsString(list.get(i));
            CustomerCreditApproval customerCreditApproval=new CustomerCreditApproval();
            customerCreditApproval=mapper.readValue(json,CustomerCreditApproval.class);
            customerCreditApprovalList.add(customerCreditApproval);
        }
        ArrayList<CustomerCreditDetail>  creditDetailList=new ArrayList<>();
        

        if("".equals(roleId)) {
            throw new MyException("参数缺失");
        }
        return new ResponseData().success().data(customerCreditApprovalService.batchSave(customerCreditApprovalList,roleId));

    }


    /**
     * 调用此接口获取某条面谈面签的审批记录
     * @param  creditDetailId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/creditapproval/{creditDetailId}",method = RequestMethod.GET)
    public ResponseData getListByIdNumber(@PathVariable long creditDetailId) throws Exception {
        CustomerCreditApproval customerCreditApproval=new CustomerCreditApproval();
        customerCreditApproval.setCreditDetailId(creditDetailId);
        return new ResponseData().success().data(customerCreditApprovalService.getListcreditDetailId(customerCreditApproval));

    }

    /**
     * 信贷系统信息
     * @param idNumber
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/creditapproval/report/{idNumber}", method = RequestMethod.GET)
    public ResponseData getApprovalReport(@PathVariable String idNumber) throws Exception {
        CustomerInfo customerInfo = customerInfoService.getCustomerByIdNumber(idNumber);
        FinanceInfo financeInfo = financeInfoService.getFinanceInfoByIdNumber(idNumber);
        if (customerInfo == null) {
            return new ResponseData().blank("客户信息为空!");
        }
        FamilyInfo familyInfo = familyInfoService.getByHouseholdId(customerInfo.getHouseholdId());

        return new ResponseData().success()
                .result("CustomerInfo", customerInfo)
                .result("FinanceInfo", financeInfo)
                .result("FamilyInfo", familyInfo);
    }


}

