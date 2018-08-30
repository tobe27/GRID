package service.impl;

import dao.OrganizationMapper;
import exception.MyException;
import model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrganizationService;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationMapper organizationMapper;

    /**
     * 删除机构
     *
     * @param orgCode
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByOrgCode(Long orgCode) throws Exception {
        try {
            return organizationMapper.deleteByOrgCode(orgCode) == 1;
        } catch (Exception e) {
            throw new MyException("删除机构出现异常");
        }
    }

    /**
     * 新增机构
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(Organization record) throws Exception {
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return organizationMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("新增机构出现异常");
        }
    }

    /**
     * 获取机构详情
     *
     * @param orgCode
     * @return
     * @throws Exception
     */
    @Override
    public Organization getOrganizationByOrgCode(Long orgCode) throws Exception {
        try {
            return organizationMapper.getOrganizationByOrgCode(orgCode);
        } catch (Exception e) {
            throw new MyException("获取机构详情出现异常");
        }
    }

    /**
     * 获取机构列表
     *
     * @param preOrgCode 上一级机构代码
     * @return
     * @throws Exception
     */
    @Override
    public List<Organization> getOrganizationsByPreOrgCode(Long preOrgCode) throws Exception {
        try {
            return organizationMapper.getOrganizationsByPreOrgCode(preOrgCode);
        } catch (Exception e) {
            throw new MyException("获取机构列表出现异常");
        }
    }

    /**
     * 编辑机构
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByOrgCodeSelective(Organization record) throws Exception {
        record.setUpdatedAt(System.currentTimeMillis());
        try {
            return organizationMapper.updateByOrgCodeSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("编辑机构出现异常");
        }
    }
}
