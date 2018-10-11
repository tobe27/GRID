package controller;

import model.FamilyInfo;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.FamilyInfoService;


/**
 * @author Created by L.C.Y on 2018-10-10
 */
@RestController
@RequestMapping
public class FamilyInfoController {
    @Autowired
    FamilyInfoService familyInfoService;

    /**
     * 调用此接口添加家庭信息
     * @param info
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/family", method = RequestMethod.POST)
    public ResponseData insert(FamilyInfo info) throws Exception {
        familyInfoService.insertSelective(info);
        return new ResponseData().success();
    }

    /**
     * 调用此接口编辑家庭信息
     * @param info
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/family/{familyId}", method = RequestMethod.PUT)
    public ResponseData update(FamilyInfo info) throws Exception {
        familyInfoService.updateByPrimaryKeySelective(info);
        return new ResponseData().success();
    }

    /**
     * 调用此接口获取家庭信息
     * @param householdId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/family/{householdId}", method = RequestMethod.GET)
    public ResponseData update(@PathVariable Long householdId) throws Exception {
        FamilyInfo info = familyInfoService.getByHouseholdId(householdId);
        if (info == null) {
            return new ResponseData().blank();
        }
        return new ResponseData().success().data(info);
    }


}
