package controller;

import model.FamilyMember;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.FamilyMemberService;

/**
 * @author Created by L.C.Y on 2018-11-5
 */

@RestController
@RequestMapping("/customer/family/member")
public class FamilyMemberController {
    @Autowired
    FamilyMemberService familyMemberService;

    /**
     * 调用此接口新建成员
     * @param record
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseData insert(FamilyMember record) throws Exception {
        familyMemberService.insertSelective(record);
        return new ResponseData().success();
    }

    /**
     * 调用此接口删除成员
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.DELETE)
    public ResponseData delete(@PathVariable Long memberId) throws Exception {
        familyMemberService.deleteByPrimaryKey(memberId);
        return new ResponseData().success();
    }

    /**
     * 调用此接口编辑成员
     * @param record
     * @return
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.PUT)
    public ResponseData update(FamilyMember record) throws Exception {
        familyMemberService.updateByPrimaryKeySelective(record);
        return new ResponseData().success();
    }


    /**
     * 调用此接口查询成员详情
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    public ResponseData get(@PathVariable Long memberId) throws Exception {
        return new ResponseData().success().data(familyMemberService.getByPrimaryKey(memberId));
    }


    /**
     * 调用此接口查询成员列表
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData list(Long customerId) throws Exception {
        return new ResponseData().success().data(familyMemberService.listByCustomerId(customerId));
    }


}
