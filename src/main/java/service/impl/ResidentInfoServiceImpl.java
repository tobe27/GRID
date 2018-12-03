package service.impl;

import dao.CustomerInfoMapper;
import dao.FamilyMemberMapper;
import dao.GridUserMapper;
import dao.ResidentInfoMapper;
import exception.MyException;
import model.CustomerInfo;
import model.FamilyMember;
import model.GridUser;
import model.ResidentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ResidentInfoService;
import util.JwtUtil;
import util.ValidUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class ResidentInfoServiceImpl implements ResidentInfoService {
    @Autowired
    ResidentInfoMapper infoMapper;
    @Autowired
    CustomerInfoMapper customerInfoMapper;
    @Autowired
    GridUserMapper userMapper;
    @Autowired
    FamilyMemberMapper familyMemberMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除居民信息
     *
     * @param residentId
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long residentId) throws Exception {
        try {
            return infoMapper.deleteByPrimaryKey(residentId) == 1;
        } catch (Exception e) {
            logger.info("删除户籍异常：" + e.getMessage());
            throw new MyException("删除户籍信息出现异常");
        }
    }

    /**
     * 新增居民信息
     * 同时导入到客户，以及生成相应的家庭成员
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean insertSelective(ResidentInfo record, HttpServletRequest request) throws Exception {
        if (record.getResidentName() == null || record.getResidentName().isEmpty()) {
            throw new MyException("姓名不能为空");
        }
        if (record.getIdNumber() == null || record.getIdNumber().isEmpty()) {
            throw new MyException("居民身份证号码不能为空");
        }
        // 查询身份证号码是否已存在
        if (infoMapper.getByIdNumber(record.getIdNumber()) != null) {
            throw new MyException("此身份证号码已存在");
        }
        if (record.getAddress() == null || record.getAddress().isEmpty()) {
            throw new MyException("地址不能为空");
        }
        if (record.getHouseholdType() == null || record.getHouseholdType().isEmpty()) {
            throw new MyException("户别不能为空");
        }
        if (record.getSex() == null || record.getSex().isEmpty()) {
            throw new MyException("性别不能为空");
        }
        if (record.getNation() == null || record.getNation().isEmpty()) {
            throw new MyException("民族不能为空");
        }
        if (record.getHouseholdId() == null) {
            throw new MyException("户号不能为空");
        }
        if (ValidUtil.isEmpty(record.getGridCode())) {
            throw new MyException("网格代码不能为空");
        }

        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);

        int count;
        try {
            count = infoMapper.insertSelective(record);
        } catch (Exception e) {
            logger.info("新建户籍异常：" + e.getMessage());
            throw new MyException("新建户籍信息出现异常");
        }
        // 将户籍转化为客户,如果客户中不存在该客户
        if (customerInfoMapper.getCustomerByIdNumber(record.getIdNumber()) == null) {
            convertResident2Customer(record, request);
        }
        return count == 1;

    }

    /**
     * 将户籍转化为客户，同时生成家庭成员
     *
     * @param residentInfo 户籍信息
     */
    private void convertResident2Customer(ResidentInfo residentInfo, HttpServletRequest request) {
        CustomerInfo record = new CustomerInfo();
        if ("是".equals(residentInfo.getIsInList())) {
            // 设置为正常客户
            record.setStatus(5);
            // 姓名
            record.setCustomerName(residentInfo.getResidentName());
            // 性别
            record.setSex(residentInfo.getSex());
            // 证件类型-默认-中国居民身份证
            record.setIdType("中国居民身份证");
            // 身份证号
            record.setIdNumber(residentInfo.getIdNumber());
            // 民族
            record.setNation(residentInfo.getNation());
            // 出生日期
            record.setBirthday(residentInfo.getBirthdate());
            // 户籍地址
            record.setNativeAddress(residentInfo.getAddress());
            // 常驻地址暂存户籍地址
            record.setResidenceAddress(residentInfo.getAddress());
            // 户号
            record.setHouseholdId(residentInfo.getHouseholdId());
            // 手机号
            record.setPhoneNumber(residentInfo.getContact());
            // 身体状况-默认-健康
            record.setPhysicalCondition("健康");
            // 国籍-默认-中国
            record.setNationality("中华人民共和国");
            // 职业-默认-农、林、牧、渔、水利业生产人员
            record.setCareer("农、林、牧、渔、水利业生产人员");
            // 是否农户-默认-是
            record.setIsFarmer("是");
            // 有无县城购房-默认-否
            record.setIsBuyHouse("否");
            // 居住状况-默认-贷款购房或贷款建房
            record.setLivingSituation("贷款建房");
            // 邮编-默认-长丰231100
            //record.setPostcode("231100");
            // 是否企业主、员工、股东-默认-否
            record.setIsOwner("否");
            record.setIsStaff("否");
            record.setIsStockholder("否");
            // 网格代码
            record.setGridCode(residentInfo.getGridCode());
            // 解析token获取登录人姓名和所属机构
            Long accountId = Long.valueOf(JwtUtil.parseToken(request.getHeader("Authorization")).get("accountId").toString());
            GridUser user = userMapper.getUserByPrimaryKey(accountId);
            // 调查人A-默认-登记人
            record.setRegistrant(user.getRealName());
            record.setInvestigator1(user.getRealName());
            record.setRegisterOrg(user.getOrgName());
            // 创建时间 修改时间
            long now = System.currentTimeMillis();
            record.setCreatedAt(now);
            record.setUpdatedAt(now);
            try {
                customerInfoMapper.insertSelective(record);
            } catch (Exception e) {
                logger.info("户籍导入客户异常：" + e.getMessage());
                throw new MyException("户籍导入客户出现异常");
            }

            // 当前户籍导入到客户后添加相同户号的人为家庭成员
            List<ResidentInfo> residentInfoList = infoMapper.listResidents(new ResidentInfo()
                    .setHouseholdId(residentInfo.getHouseholdId()).setAccountId(accountId));
            if (residentInfoList != null && residentInfoList.size() != 0) {
                for (ResidentInfo resident : residentInfoList) {
                    insertFamilyMember(record, resident);
                }
            }

            // 所有该户号下客户都添加该户籍人员为家庭成员
            List<CustomerInfo> customerInfoList = customerInfoMapper.listCustomers(new CustomerInfo().setHouseholdId(residentInfo.getHouseholdId()));
            if (customerInfoList != null && customerInfoList.size() != 0) {
                for (CustomerInfo customerInfo : customerInfoList) {
                    // 导入家庭成员
                    insertFamilyMember(customerInfo, residentInfo);
                }
            }

        }
    }

    /**
     * 新建户籍时导入到家庭成员
     * @param record    客户信息
     */
    private void insertFamilyMember(CustomerInfo record, ResidentInfo info) {
        // 家庭成员导入数量
        int insertMemberCount = 0;

        // 如果身份证号与当前客户相同则跳过，否则导入到家庭成员中
        if (record.getIdNumber().equals(info.getIdNumber())) {
            return;
        }
        // 如果已存在客户家庭成员中，则跳过
        if (familyMemberMapper.getByCustomerIdAndIdNumber(
                new FamilyMember().setCustomerId(record.getCustomerId())
                        .setIdNumber(info.getIdNumber())) != null) {
            return;
        }

        FamilyMember member = new FamilyMember();
        long now = System.currentTimeMillis();
        member.setMemberName("".equals(info.getResidentName()) ? null : info.getResidentName())
                .setBirthday("".equals(info.getBirthdate()) ? null : info.getBirthdate())
                .setIdType("中国居民身份证")
                .setIdNumber("".equals(info.getIdNumber()) ? null : info.getIdNumber())
                .setNation("".equals(info.getNation()) ? null : info.getNation())
                .setIsHouseholdHead("户主".equals(info.getRelationship()) ? "是" : "否")
                .setContact("".equals(info.getContact()) ? null : info.getContact())
                .setAddress("".equals(info.getAddress()) ? null : info.getAddress())
                .setRemark("".equals(info.getRemark()) ? null : info.getRemark())
                .setCreatedAt(now)
                .setUpdatedAt(now)
                .setCustomerId(record.getCustomerId());
        // 将成员信息导入数据库
        try {
            familyMemberMapper.insertSelective(member);
            insertMemberCount++;
        } catch (Exception e) {
            logger.info("户籍导入成员异常：" + e.getMessage());
            throw new MyException("从户籍导入家庭成员信息出现异常！");
        }

        logger.info("共导入到家庭成员人数:"+insertMemberCount);
}


    /**
     * 获取居民详情
     *
     * @param residentId
     * @return
     * @throws Exception
     */
    @Override
    public ResidentInfo getResidentByPrimaryKey(Long residentId) throws Exception {
        try {
            return infoMapper.getResidentByPrimaryKey(residentId);
        } catch (Exception e) {
            logger.info("查询户籍异常："+e.getMessage());
            throw new MyException("查询户籍信息出现异常");
        }
    }

    /**
     * 通过身份证获取户籍信息
     *
     * @param idNumber
     * @return
     * @throws Exception
     */
    @Override
    public ResidentInfo getByIdNumber(String idNumber) throws Exception {
        try {
            return infoMapper.getByIdNumber(idNumber);
        } catch (Exception e) {
            logger.info("身份证号查询户籍异常："+e.getMessage());
            throw new MyException("通过身份证号查询户籍信息出现异常");
        }
    }

    /**
     * 获取居民列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<ResidentInfo> listResidents(ResidentInfo record) throws Exception {
        try {
            return infoMapper.listResidents(record);
        } catch (Exception e) {
            logger.info("查询户籍列表异常："+e.getMessage());
            throw new MyException("查询户籍信息列表出现异常:" + e);
        }
    }

    /**
     * 获取居民列表-通过登录用户机构获取
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<ResidentInfo> listByOrg(ResidentInfo record) throws Exception {
        try {
            return infoMapper.listByOrg(record);
        } catch (Exception e) {
            logger.info("查询户籍列表异常："+e.getMessage());
            throw new MyException("查询户籍信息列表出现异常:" + e);
        }
    }

    /**
     * 编辑居民信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(ResidentInfo record) throws Exception {
        if (record.getResidentName() == null || record.getResidentName().isEmpty()) {
            throw new MyException("姓名不能为空");
        }
        if (record.getIdNumber() == null || record.getIdNumber().isEmpty()) {
            throw new MyException("居民身份证号码不能为空");
        }
        if (record.getAddress() == null || record.getAddress().isEmpty()) {
            throw new MyException("地址不能为空");
        }
        if (record.getHouseholdType() == null || record.getHouseholdType().isEmpty()) {
            throw new MyException("户别不能为空");
        }
        if (record.getSex() == null || record.getSex().isEmpty()) {
            throw new MyException("性别不能为空");
        }
        if (record.getNation() == null || record.getNation().isEmpty()) {
            throw new MyException("民族不能为空");
        }
        if (record.getHouseholdId() == null) {
            throw new MyException("户号不能为空");
        }
        if (ValidUtil.isEmpty(record.getGridCode())) {
            throw new MyException("网格代码不能为空");
        }
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return infoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑户籍异常："+e.getMessage());
            // 查询身份证号码是否已存在
            if (infoMapper.getByIdNumber(record.getIdNumber()) != null) {
                throw new MyException("此身份证号码已存在");
            }
            throw new MyException("编辑户籍信息出现异常");
        }
    }

    /**
     * 编辑居民信息状态
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateResidentStatus(ResidentInfo record) throws Exception {
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return infoMapper.updateResidentStatus(record) == 1;
        } catch (Exception e) {
            logger.info("修改户籍状态异常："+e.getMessage());
            throw new MyException("修改户籍信息状态出现异常");
        }
    }

    /**
     * 批量插入居民户籍信息
     *
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public boolean batchSave(List<ResidentInfo> list) throws Exception {
        try {
            return infoMapper.batchSave(list)==1;
        }catch(Exception e) {
            logger.info("批量新增户籍异常："+e.getMessage());
            throw new MyException("批量新增户籍信息出现异常");
        }
    }
}
