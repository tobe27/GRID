package service;

import model.Organization;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface OrganizationService {
    /**
     * 删除机构
     * @param orgId
     * @return
     * @throws Exception
     */
    boolean deleteByOrgId(Long orgId) throws Exception;

    /**
     * 新增机构
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(Organization record) throws Exception;

    /**
     * 获取机构详情
     * @param orgId
     * @return
     * @throws Exception
     */
    Organization getOrganizationByOrgId(Long orgId) throws Exception;

    /**
     * 通过机构码获取详情
     * @param orgCode
     * @return
     */
    Organization getOrganizationByOrgCode(Long orgCode) throws Exception;

    /**
     * 通过机构名获取详情
     * @param orgName
     * @return
     */
    Organization getOrganizationByOrgName(String orgName) throws Exception;

    /**
     * 获取机构列表
     * @param preOrgId 上一级机构代码
     * @return
     * @throws Exception
     */
    List<Organization> getOrganizationsByPreOrgId(Long preOrgId) throws Exception;

    /**
     * 获取所有机构列表
     * @param record
     * @return
     * @throws Exception
     */
    List<Organization> listOrganization(Organization record) throws Exception;

    /**
     * 编辑机构
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByOrgCodeSelective(Organization record) throws Exception;
}
