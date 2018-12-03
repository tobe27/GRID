package dao;

import model.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Repository
public interface OrganizationMapper {
    int deleteByOrgId(Long orgId);

    int insertSelective(Organization record);

    Organization getOrganizationByOrgId(Long orgId);

    Organization getOrganizationByOrgCode(Long orgCode);

    Organization getOrganizationByOrgName(String orgName);

    List<Organization> getOrganizationsByPreOrgId(Long preOrgId);

    List<Organization> listOrganization(Organization record);

    int updateByOrgCodeSelective(Organization record);
}