package dao;

import model.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationMapper {
    int deleteByOrgCode(Long orgCode);

    int insertSelective(Organization record);

    Organization getOrganizationByOrgCode(Long orgCode);

    List<Organization> getOrganizationsByPreOrgCode(Long preOrgCode);

    int updateByOrgCodeSelective(Organization record);
}