package controller;

import model.Organization;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.OrganizationService;
import util.TreeUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping("/super")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    /**
     * 调用此接口新增机构
     * @param organization
     * @return
     */
    @RequestMapping(value = "/org", method = RequestMethod.POST)
    public ResponseData insertOrg(Organization organization) throws Exception {

        organizationService.insertSelective(organization);
        return new ResponseData().success();

    }

    /**
     * 调用此接口编辑机构
     * @param organization
     * @return
     */
    @RequestMapping(value = "/org/{orgId}", method = RequestMethod.PUT)
    public ResponseData updateOrg(Organization organization) throws Exception {

        organizationService.updateByOrgCodeSelective(organization);
        return new ResponseData().success();

    }

    /**
     * 调用此接口删除机构
     * @param orgId 机构代码
     * @return
     */
    @RequestMapping(value = "/org/{orgId}", method = RequestMethod.DELETE)
    public ResponseData deleteOrg(@PathVariable Long orgId) throws Exception {

        organizationService.deleteByOrgId(orgId);
        return new ResponseData().success();

    }

    /**
     * 调用此接口获取机构列表
     * @param orgId
     * @return
     */
    @RequestMapping(value = "/org/{orgId}", method = RequestMethod.GET)
    public ResponseData getOrg(@PathVariable Long orgId) throws Exception {

        List<Organization> organizationList = organizationService.listOrganization(new Organization());
        List list = new TreeUtil().getOrgList(organizationList, orgId);
        return new ResponseData().success().data(list);

    }

}
