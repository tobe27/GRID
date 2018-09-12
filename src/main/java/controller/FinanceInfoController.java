package controller;

import model.FinanceInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.FinanceInfoService;

@RestController
@RequestMapping
public class FinanceInfoController {
    @Autowired
    FinanceInfoService infoService;

    /**
     * 调用此接口获取客户财务信息
     * @param idNumber
     * @return
     */
    @RequestMapping(value = "/customer/finance/{idNumber}", method = RequestMethod.GET)
    public ResponseData getFinanceInfo(@PathVariable String idNumber) {
        try {
            FinanceInfo info = infoService.getFinanceInfoByIdNumber(idNumber);
            if (info == null) {
                return new ResponseData().fail("客户财务信息不存在");
            }
            return new ResponseData().success().data(info);
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口删除客户财务信息
     * @param idNumber
     * @return
     */
    @RequestMapping(value = "/customer/finance/{idNumber}", method = RequestMethod.DELETE)
    public ResponseData deleteFinanceInfo(@PathVariable String idNumber) {
        try {
            infoService.deleteByIdNumber(idNumber);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口编辑客户财务信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/finance/{idNumber}", method = RequestMethod.PUT)
    public ResponseData updateFinanceInfo(FinanceInfo info) {
        try {
            infoService.updateByIdNumberSelective(info);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口添加客户财务信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/finance", method = RequestMethod.POST)
    public ResponseData insertFinanceInfo(FinanceInfo info) {
        try {
            infoService.insertSelective(info);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

}