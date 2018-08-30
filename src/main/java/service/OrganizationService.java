package service;

import model.Organization;

import java.util.List;

public interface OrganizationService {
    /**
     * 删除机构
     * @param orgCode
     * @return
     * @throws Exception
     */
    boolean deleteByOrgCode(Long orgCode) throws Exception;

    /**
     * 新增机构
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(Organization record) throws Exception;

    /**
     * 获取机构详情
     * @param orgCode
     * @return
     * @throws Exception
     */
    Organization getOrganizationByOrgCode(Long orgCode) throws Exception;

    /**
     * 获取机构列表
     * @param preOrgCode 上一级机构代码
     * @return
     * @throws Exception
     */
    List<Organization> getOrganizationsByPreOrgCode(Long preOrgCode) throws Exception;

    /**
     * 编辑机构
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByOrgCodeSelective(Organization record) throws Exception;
}
