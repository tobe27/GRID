package controller;

import model.Organization;
import model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.OrganizationService;

import java.util.List;

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
    public ResponseData insertOrg(Organization organization) {
        try {
            organizationService.insertSelective(organization);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口编辑机构
     * @param organization
     * @return
     */
    @RequestMapping(value = "/org/{orgCode}", method = RequestMethod.PUT)
    public ResponseData updateOrg(Organization organization) {
        try {
            organizationService.updateByOrgCodeSelective(organization);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口删除机构
     * @param orgCode 机构代码
     * @return
     */
    @RequestMapping(value = "/org/{orgCode}", method = RequestMethod.DELETE)
    public ResponseData deleteOrg(@PathVariable Long orgCode) {
        try {
            organizationService.deleteByOrgCode(orgCode);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    /**
     * 调用此接口获取机构列表
     * @param orgCode
     * @return
     */
    @RequestMapping(value = "/org/{orgCode}", method = RequestMethod.GET)
    public ResponseData getOrg(@PathVariable Long orgCode) {
        try {
            Organization organization = organizationService.getOrganizationByOrgCode(orgCode);
            List<Organization> nextOrganization = organizationService.getOrganizationsByPreOrgCode(orgCode);
            return new ResponseData().success().data(organization).result("nextOrganization",nextOrganization);
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

}
