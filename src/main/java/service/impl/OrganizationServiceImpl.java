package service.impl;

import dao.GridUserMapper;
import dao.OrganizationMapper;
import exception.MyException;
import model.GridUser;
import model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrganizationService;
import util.ValidUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationMapper organizationMapper;
    @Autowired
    GridUserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除机构
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean deleteByOrgId(Long orgId) throws Exception {
        if (orgId == 1) {
            throw new MyException("总机构不能删除！");
        }

        List<GridUser> userList;
        Organization organization;
        try {
            userList = userMapper.getUsersByAccountNameOrRealNameOrOrgName(new GridUser());
            organization = organizationMapper.getOrganizationByOrgId(orgId);
        } catch (Exception e) {
            logger.info("删除机构异常："+e.getMessage());
            throw new MyException("系统出现异常");
        }

        for (GridUser user : userList) {
            if (user.getOrgCode().equals(organization.getOrgCode())) {
                throw new MyException("有用户归属于该机构，解除后方可删除");
            }
        }

        try {
            return organizationMapper.deleteByOrgId(orgId) == 1;
        } catch (Exception e) {
            logger.info("删除机构异常："+e.getMessage());
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
        if (ValidUtil.isNotNumber(record.getOrgCode(), 1, 20)) {
            throw  new MyException("机构代码请输入纯数字，最长20位");
        }
        if (ValidUtil.isNotLength(record.getOrgName(), 1, 100)) {
            throw  new MyException("机构名称最长100位");
        }
        if (ValidUtil.isEmpty(record.getPreOrgId())) {
            throw  new MyException("上级机构代码不能为空");
        }
        if (ValidUtil.isNotEmpty(record.getCorpCode()) && ValidUtil.isNotLength(record.getCorpCode(),1, 20)) {
            throw  new MyException("法人代码最长100字");
        }
        if (ValidUtil.isNotEmpty(record.getDescription()) && ValidUtil.isNotLength(record.getDescription(), 1, 100)) {
            throw  new MyException("备注最长100字");
        }
        // 判断机构代码是否已经存在
        if (organizationMapper.getOrganizationByOrgCode(record.getOrgCode()) != null) {
            throw new MyException("机构代码已存在");
        }
        // 判断机构名称是否已经存在
        if (organizationMapper.getOrganizationByOrgName(record.getOrgName()) != null) {
            throw new MyException("机构名称已存在");
        }

        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return organizationMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            logger.info("新建机构异常："+e.getMessage());
            throw new MyException("新建机构出现异常");
        }
    }

    /**
     * 获取机构详情
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    @Override
    public Organization getOrganizationByOrgId(Long orgId) throws Exception {
        try {
            return organizationMapper.getOrganizationByOrgId(orgId);
        } catch (Exception e) {
            logger.info("查询机构异常："+e.getMessage());
            throw new MyException("查询机构详情出现异常");
        }
    }

    /**
     * 通过机构码获取详情
     *
     * @param orgCode
     * @return
     */
    @Override
    public Organization getOrganizationByOrgCode(Long orgCode) throws Exception {
        try {
            return organizationMapper.getOrganizationByOrgCode(orgCode);
        } catch (Exception e) {
            logger.info("机构代码查询机构异常："+e.getMessage());
            throw new MyException("通过机构代码查询机构详情出现异常");
        }
    }

    /**
     * 通过机构名获取详情
     *
     * @param orgName
     * @return
     */
    @Override
    public Organization getOrganizationByOrgName(String orgName) throws Exception {
        try {
            return organizationMapper.getOrganizationByOrgName(orgName);
        } catch (Exception e) {
            logger.info("机构名称查询机构异常："+e.getMessage());
            throw new MyException("通过机构名称查询机构详情出现异常");
        }
    }

    /**
     * 获取机构列表
     *
     * @param preOrgId 上一级机构代码
     * @return
     * @throws Exception
     */
    @Override
    public List<Organization> getOrganizationsByPreOrgId(Long preOrgId) throws Exception {
        try {
            return organizationMapper.getOrganizationsByPreOrgId(preOrgId);
        } catch (Exception e) {
            logger.info("查询机构列表异常："+e.getMessage());
            throw new MyException("查询机构列表出现异常");
        }
    }

    /**
     * 获取所有机构列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<Organization> listOrganization(Organization record) throws Exception {
        try {
            return organizationMapper.listOrganization(record);
        } catch (Exception e) {
            logger.info("查询所有机构异常："+e.getMessage());
            throw new MyException("查询所有机构出现异常");
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
        // 机构代码不予编辑
        record.setOrgCode(null);

        if (ValidUtil.isNotLength(record.getOrgName(), 1, 20)) {
            throw  new MyException("机构名称最长20位");
        }
        if (ValidUtil.isEmpty(record.getPreOrgId())) {
            throw  new MyException("上级机构代码不能为空");
        }
        if (ValidUtil.isNotEmpty(record.getCorpCode()) && ValidUtil.isNotLength(record.getCorpCode(),1, 20)) {
            throw  new MyException("法人代码最长100字");
        }
        if (ValidUtil.isNotEmpty(record.getDescription()) && ValidUtil.isNotLength(record.getDescription(), 1, 100)) {
            throw  new MyException("备注最长100字");
        }

        record.setUpdatedAt(System.currentTimeMillis());
        try {
            return organizationMapper.updateByOrgCodeSelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑机构异常："+e.getMessage());
            // 判断机构名称是否已经存在
            if (organizationMapper.getOrganizationByOrgName(record.getOrgName()) != null) {
                throw new MyException("机构名称已存在");
            }
            throw new MyException("编辑机构出现异常");
        }
    }
}
