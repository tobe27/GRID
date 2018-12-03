package service.impl;

import dao.CustomerInfoMapper;
import dao.FamilyInfoMapper;
import dao.FamilyMemberMapper;
import dao.ResidentInfoMapper;
import exception.MyException;
import model.CustomerInfo;
import model.FamilyInfo;
import model.FamilyMember;
import model.ResidentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CustomerInfoService;
import util.ValidUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    CustomerInfoMapper infoMapper;
    @Autowired
    ResidentInfoMapper residentInfoMapper;
    @Autowired
    FamilyMemberMapper familyMemberMapper;
    @Autowired
    FamilyInfoMapper familyInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除客户信息
     * 非暂存客户不可删除
     * @param customerId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean deleteByPrimaryKey(Long customerId) throws Exception {
        CustomerInfo info = infoMapper.getCustomerByPrimaryKey(customerId);
        if (info != null && info.getStatus() != 0) {
           throw new MyException("非暂存客户不可删除！");
        }
        try {
            return infoMapper.deleteByPrimaryKey(customerId) == 1;
        } catch (Exception e) {
            logger.info("删除客户异常："+e.getMessage());
            throw new MyException("删除客户信息出现异常!");
        }
    }

    /**
     * 添加客户信息
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(CustomerInfo record) throws Exception {
        if (ValidUtil.isNotLength(record.getCustomerName(), 2, 20)) {
            throw new MyException("请输入2-20位姓名");
        }
        if (ValidUtil.isNotLength(record.getIdNumber(), 18)) {
            throw new MyException("请输入18位身份证号");
        }
        if (infoMapper.getCustomerByIdNumber(record.getIdNumber()) != null) {
            throw new MyException("身份证号已存在");
        }
        if (ValidUtil.isNotLength(record.getHouseholdId(), 2, 20)) {
            throw new MyException("请输入户号,最长20位");
        }
        if (ValidUtil.isEmpty(record.getGridCode())) {
            throw new MyException("请先选择网格");
        }
        // 性别
        if (ValidUtil.isEmpty(record.getSex())) {
            throw new MyException("请选择性别");
        }
        // 出生年月
        if (ValidUtil.isNotLength(record.getBirthday(), 8, 10)) {
            throw new MyException("请选择出生日期");
        }
        // 手机号码
        if (ValidUtil.isNotPhone(record.getPhoneNumber())) {
            throw new MyException("请输入1开头的11位手机号");
        }
        // 身体状况
        if (ValidUtil.isEmpty(record.getPhysicalCondition())) {
            throw new MyException("请选择身体状况");
        }
        // 最高学历
        if (ValidUtil.isEmpty(record.getEducationLevel())) {
            throw new MyException("请选择最高学历");
        }
        // 国籍
        if (ValidUtil.isEmpty(record.getNationality())) {
            throw new MyException("请选择国籍");
        }
        // 政治面貌
        if (ValidUtil.isEmpty(record.getPoliticsStatus())) {
            throw new MyException("请选择政治面貌");
        }
        // 婚姻状况
        if (ValidUtil.isEmpty(record.getMaritalStatus())) {
            throw new MyException("请选择婚姻状况");
        }
        // 职业
        if (ValidUtil.isEmpty(record.getCareer())) {
            throw new MyException("请选择职业");
        }
        // 是否农户
        if (ValidUtil.isEmpty(record.getIsFarmer())) {
            throw new MyException("请选择是否农户");
        }
        // 户籍地址
        if (ValidUtil.isNotLength(record.getNativeAddress(), 2, 100)) {
            throw new MyException("请输入户籍地址");
        }
        // 居住状况
        if (ValidUtil.isEmpty(record.getLivingSituation())) {
            throw new MyException("请选择居住状况");
        }
        // 居住地址
        if (ValidUtil.isNotLength(record.getResidenceAddress(), 2, 100)) {
            throw new MyException("请输入居住地址");
        }
        // 居住地邮编
        if (ValidUtil.isNotLength(record.getPostcode(), 6)) {
            throw new MyException("请输入6位邮编");
        }
        // 是否企业主
        if (ValidUtil.isEmpty(record.getIsOwner())) {
            throw new MyException("请选择是否企业主");
        }
        // 是否本行员工
        if (ValidUtil.isEmpty(record.getIsStaff())) {
            throw new MyException("请选择是否本行员工");
        }
        // 是否本行股东
        if (ValidUtil.isEmpty(record.getIsStockholder())) {
            throw new MyException("请选择是否本行股东");
        }
        // 登记人
        if (ValidUtil.isNotLength(record.getRegistrant(),2, 20)) {
            throw new MyException("登记人不能为空");
        }
        // 登记机构
        if (ValidUtil.isEmpty(record.getRegisterOrg())) {
            throw new MyException("登记机构不能为空");
        }
        // 调查人A
        if (ValidUtil.isNotLength(record.getInvestigator1(), 2, 20)) {
            throw new MyException("请输入调查人(A角)");
        }
        // 调查人B
        if (ValidUtil.isNotLength(record.getInvestigator2(), 2, 20)) {
            throw new MyException("请输入调查人(B角)");
        }

        // 保存时直接为正常客户
        record.setStatus(5);
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        int count;
        try {
            // 新建客户
            count = infoMapper.insertSelective(record);
        } catch (Exception e) {
            logger.info("新建客户异常：" + e.getMessage());
            throw new MyException("新建客户信息出现异常");
        }
        // 通过户号导入家庭成员
        //insertFamilyMember(record);
        return count == 1;
    }

    /**
     * 新建客户信息-草稿，不做参数校验
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertDraft(CustomerInfo record) throws Exception {
        if (ValidUtil.isNotLength(record.getCustomerName(), 2, 20)) {
            throw new MyException("请输入2-20位姓名");
        }
        if (ValidUtil.isNotLength(record.getIdNumber(), 18)) {
            throw new MyException("请输入18位身份证号");
        }
        if (infoMapper.getCustomerByIdNumber(record.getIdNumber()) != null) {
            throw new MyException("身份证号已存在");
        }
        if (ValidUtil.isEmpty(record.getGridCode())) {
            throw new MyException("请先选择网格");
        }
        // 登记人
        if (ValidUtil.isNotLength(record.getRegistrant(),2, 20)) {
            throw new MyException("登记人不能为空");
        }
        // 登记机构
        if (ValidUtil.isEmpty(record.getRegisterOrg())) {
            throw new MyException("登记机构不能为空");
        }

        // 以上为必填项,以下对输入项进行参数校验****************************************
        // 户号
        if (ValidUtil.isNotEmpty(record.getHouseholdId()) && ValidUtil.isNotLength(record.getHouseholdId(), 2, 20)) {
                throw new MyException("请输入户号,最长20位");
        }
        // 出生年月
        if (ValidUtil.isNotEmpty(record.getBirthday()) && ValidUtil.isNotLength(record.getBirthday(), 8, 10)) {
                throw new MyException("请选择出生日期");
        }
        // 手机号码
        if (ValidUtil.isNotEmpty(record.getPhoneNumber()) && ValidUtil.isNotPhone(record.getPhoneNumber())) {
                throw new MyException("请输入1开头的11位手机号");
        }
        // 户籍地址
        if (ValidUtil.isNotEmpty(record.getNativeAddress()) && ValidUtil.isNotLength(record.getNativeAddress(), 2, 100)) {
                throw new MyException("请输入户籍地址");
        }
        // 居住地址
        if (ValidUtil.isNotEmpty(record.getResidenceAddress()) && ValidUtil.isNotLength(record.getResidenceAddress(), 2, 100)) {
                throw new MyException("请输入居住地址");
        }
        // 居住地邮编
        if (ValidUtil.isNotEmpty(record.getPostcode()) && ValidUtil.isNotLength(record.getPostcode(), 6)) {
                throw new MyException("请输入6位邮编");
        }
        // 调查人A
        if (ValidUtil.isNotEmpty(record.getInvestigator1()) && ValidUtil.isNotLength(record.getInvestigator1(), 2, 20)) {
                throw new MyException("请输入调查人(A角)");
        }
        // 调查人B
        if (ValidUtil.isNotEmpty(record.getInvestigator2()) && ValidUtil.isNotLength(record.getInvestigator2(), 2, 20)) {
                throw new MyException("请输入调查人(B角)");
        }

        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        record.setStatus(0); // 草稿状态
        int count;
        try {
            count = infoMapper.insertSelective(record);
        } catch (Exception e) {
            logger.info("新建暂存客户异常："+e.getMessage());
            throw new MyException("新建暂存客户出现异常");
        }
        return  count == 1;
    }

    /**
     * 通过Excel导入
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertByExcel(CustomerInfo record) throws Exception {
        return infoMapper.insertSelective(record) == 1;
    }

    /**
     * 把户籍信息转换成正常客户
     * 同时导入家庭成员信息
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean insertByResidentInfo(CustomerInfo record) throws Exception {
        // 身份证号
        if (ValidUtil.isNotLength(record.getIdNumber(), 18)) {
            throw new MyException("请输入正确的身份证号");
        }
        // 登记人 登记机构
        if (ValidUtil.isNotLength(record.getRegistrant(),2, 20)
                || ValidUtil.isNotLength(record.getRegisterOrg(), 2, 30)) {
            throw new MyException("登记人及登记机构不能为空");
        }

        if (infoMapper.getCustomerByIdNumber(record.getIdNumber()) != null) {
            throw new MyException("该客户已存在客户列表中,请勿重复添加！");
        }
        int insertCustomerCount;
        ResidentInfo residentInfo;
        try {
            residentInfo = residentInfoMapper.getByIdNumber(record.getIdNumber());
        } catch (Exception e) {
            logger.info("户籍转换成客户异常:" + e.getMessage());
            throw new MyException("户籍转换成客户出现异常");
        }

        if (ValidUtil.isEmpty(residentInfo)) {
            throw new MyException("户籍中不存在该客户");
        }
        if ("否".equals(residentInfo.getIsInList())) {
            throw new MyException("不在名单库中，不可添加为客户");
        }

        // 设置为正常客户
        record.setStatus(5);
        // 姓名
        record.setCustomerName(residentInfo.getResidentName());
        // 性别
        record.setSex(residentInfo.getSex());
        // 证件类型-默认-中国居民身份证
        record.setIdType("中国居民身份证");
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
        // 网格号
        record.setGridCode(residentInfo.getGridCode());
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
        // 调查人A-默认-登记人
        record.setInvestigator1(record.getRegistrant());
        // 创建时间 修改时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
             insertCustomerCount = infoMapper.insertSelective(record);
        } catch (Exception e) {
            logger.info("户籍导入客户异常：" + e.getMessage());
            throw new MyException("户籍导入客户出现异常");
        }
        // 通过户号导入家庭成员
     //   insertFamilyMember(record);
        return insertCustomerCount == 1;
    }


    /**
     * 批量导入客户信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean batchSave(List<CustomerInfo> record) throws Exception {
        try {
            return infoMapper.batchSave(record) == 1;
        } catch (Exception e) {
            logger.info("批量导入客户异常："+e.getMessage());
            throw new MyException("批量导入客户信息出现异常");
        }
    }

    /**
     * 获取客户信息详情
     * @param customerId
     * @return
     * @throws Exception
     */
    @Override
    public CustomerInfo getCustomerByPrimaryKey(Long customerId) throws Exception {
        try {
            return infoMapper.getCustomerByPrimaryKey(customerId);
        } catch (Exception e) {
            logger.info("查询客户异常："+e.getMessage());
            throw new MyException("查询客户信息出现异常");
        }
    }

    /**
     * 获取客户信息详情BY身份证
     *
     * @param idNumber
     * @return
     * @throws Exception
     */
    @Override
    public CustomerInfo getCustomerByIdNumber(String idNumber) throws Exception {
        try {
            return infoMapper.getCustomerByIdNumber(idNumber);
        } catch (Exception e) {
            logger.info("通过身份证号查询客户异常："+e.getMessage());
            throw new MyException("通过身份证号查询客户信息出现异常");
        }
    }

    /**
     * 申请表信息接口
     *
     * @param customerId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Map<String, Object> getCustomerForm(Long customerId) throws Exception {
        Map<String, Object> formMap = new HashMap<>();
        CustomerInfo customerInfo; // 客户信息
        List<FamilyMember> familyMemberList; // 家庭成员
        FamilyInfo familyInfo;  // 家庭信息
        try {
            customerInfo = infoMapper.getCustomerByPrimaryKey(customerId);
            familyMemberList = familyMemberMapper.listByCustomerId(customerId);
            familyInfo = familyInfoMapper.getByHouseholdId(customerInfo.getHouseholdId());
            logger.info("member:" + familyMemberList + "********familyInfo:" + familyInfo);
        } catch (Exception e) {
            logger.info("查询客户申请表信息异常："+e.getMessage());
            throw new MyException("查询客户申请表信息出现异常");
        }

        // 家庭信息不存在时直接存空家庭人数
        if (familyInfo == null) {
            formMap.put("familyPopulation", null);
        } else {
            formMap.put("familyPopulation", familyInfo.getPopulation());
        }

        formMap.put("name", customerInfo.getCustomerName()); // 姓名
        formMap.put("idNumber", customerInfo.getIdNumber()); // 身份证号
        formMap.put("sex", customerInfo.getSex()); // 性别
        formMap.put("maritalStatus", customerInfo.getMaritalStatus()); // 婚姻状况
        formMap.put("educationLevel", customerInfo.getEducationLevel()); // 文化水平
        formMap.put("phoneNumber", customerInfo.getPhoneNumber()); // 手机号
        formMap.put("companyName", customerInfo.getCompanyName()); // 工作单位
        formMap.put("companyAddress", customerInfo.getCompanyAddress()); // 工作地址
        formMap.put("nativeAddress", customerInfo.getNativeAddress()); // 户籍地址
        formMap.put("residenceAddress", customerInfo.getResidenceAddress()); // 居住地址

        // 如果家庭成员为空，配偶信息直接置空
        if (familyMemberList.isEmpty()) {
            logger.info("成员为空");
            formMap.put("spouseName", null); // 配偶姓名
            formMap.put("spouseIdNumber", null); // 配偶身份证号
        } else {
            for (FamilyMember familyMember : familyMemberList) {
                if ("配偶".equals(familyMember.getRelationship())) {
                    logger.info("配偶不为空");
                    formMap.put("spouseName", familyMember.getMemberName()); // 配偶姓名
                    formMap.put("spouseIdNumber", familyMember.getIdNumber()); // 配偶身份证号
                    return formMap;
                } else {
                    logger.info("配偶为空");
                    formMap.put("spouseName", null); // 配偶姓名
                    formMap.put("spouseIdNumber", null); // 配偶身份证号

                }
            }
        }
        return formMap;
    }

    /**
     * 条件获取客户信息列表
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerInfo> listCustomers(CustomerInfo record) throws Exception {
        try {
            return infoMapper.listCustomers(record);
        } catch (Exception e) {
            logger.info("查询客户列表异常："+e.getMessage());
            throw new MyException("查询客户信息列表出现异常");
        }
    }

    /**
     * 通过用户ID获取客户列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerInfo> listCustomersByAccountId(CustomerInfo record) throws Exception {
        try {
            return infoMapper.listCustomersByAccountId(record);
        } catch (Exception e) {
            logger.info("用户查询客户异常："+e.getMessage());
            throw new MyException("通过用户查询客户信息出现异常");
        }
    }

    /**
     * 通过机构号获取客户列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<CustomerInfo> listCustomersByOrgCode(CustomerInfo record) throws Exception {
        try {
            return infoMapper.listCustomersByOrgCode(record);
        } catch (Exception e) {
            logger.info("通过机构查询客户异常："+e.getMessage());
            throw new MyException("通过机构查询客户信息出现异常");
        }
    }


    /**
     * 修改客户信息
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean updateByPrimaryKeySelective(CustomerInfo record) throws Exception {
        if (ValidUtil.isNotLength(record.getCustomerName(), 2, 20)) {
            throw new MyException("请输入2-20位姓名");
        }
        if (ValidUtil.isNotLength(record.getIdNumber(), 18)) {
            throw new MyException("请输入18位身份证号");
        }
        if (ValidUtil.isNotLength(record.getHouseholdId(), 2, 20)) {
            throw new MyException("请输入户号,最长20位");
        }
        if (ValidUtil.isEmpty(record.getGridCode())) {
            throw new MyException("请先选择网格");
        }
        // 性别
        if (ValidUtil.isEmpty(record.getSex())) {
            throw new MyException("请选择性别");
        }
        // 出生年月
        if (ValidUtil.isNotLength(record.getBirthday(), 8, 10)) {
            throw new MyException("请选择出生日期");
        }
        // 手机号码
        if (ValidUtil.isNotPhone(record.getPhoneNumber())) {
            throw new MyException("请输入1开头的11位手机号");
        }
        // 身体状况
        if (ValidUtil.isEmpty(record.getPhysicalCondition())) {
            throw new MyException("请选择身体状况");
        }
        // 最高学历
        if (ValidUtil.isEmpty(record.getEducationLevel())) {
            throw new MyException("请选择最高学历");
        }
        // 国籍
        if (ValidUtil.isEmpty(record.getNationality())) {
            throw new MyException("请选择国籍");
        }
        // 政治面貌
        if (ValidUtil.isEmpty(record.getPoliticsStatus())) {
            throw new MyException("请选择政治面貌");
        }
        // 婚姻状况
        if (ValidUtil.isEmpty(record.getMaritalStatus())) {
            throw new MyException("请选择婚姻状况");
        }
        // 职业
        if (ValidUtil.isEmpty(record.getCareer())) {
            throw new MyException("请选择职业");
        }
        // 是否农户
        if (ValidUtil.isEmpty(record.getIsFarmer())) {
            throw new MyException("请选择是否农户");
        }
        // 户籍地址
        if (ValidUtil.isNotLength(record.getNativeAddress(), 2, 100)) {
            throw new MyException("请输入户籍地址");
        }
        // 居住状况
        if (ValidUtil.isEmpty(record.getLivingSituation())) {
            throw new MyException("请选择居住状况");
        }
        // 居住地址
        if (ValidUtil.isNotLength(record.getResidenceAddress(), 2, 100)) {
            throw new MyException("请输入居住地址");
        }
        // 居住地邮编
        if (ValidUtil.isNotLength(record.getPostcode(), 6)) {
            throw new MyException("请输入6位邮编");
        }
        // 是否企业主
        if (ValidUtil.isEmpty(record.getIsOwner())) {
            throw new MyException("请选择是否企业主");
        }
        // 是否本行员工
        if (ValidUtil.isEmpty(record.getIsStaff())) {
            throw new MyException("请选择是否本行员工");
        }
        // 是否本行股东
        if (ValidUtil.isEmpty(record.getIsStockholder())) {
            throw new MyException("请选择是否本行股东");
        }
        // 登记人
        if (ValidUtil.isNotLength(record.getRegistrant(),2, 20)) {
            throw new MyException("登记人不能为空");
        }
        // 登记机构
        if (ValidUtil.isEmpty(record.getRegisterOrg())) {
            throw new MyException("登记机构不能为空");
        }
        // 调查人A
        if (ValidUtil.isNotLength(record.getInvestigator1(), 2, 20)) {
            throw new MyException("请输入调查人(A角)");
        }
        // 调查人B
        if (ValidUtil.isNotLength(record.getInvestigator2(), 2, 20)) {
            throw new MyException("请输入调查人(B角)");
        }
        if (ValidUtil.isEmpty(record.getStatus())) {
            throw new MyException("状态码不能为空");
        }

        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);

        //if (familyMemberMapper.listByCustomerId(record.getCustomerId()) == null) {
        //    // 通过户号导入家庭成员
        //    //insertFamilyMember(record);
        //}
        // 如果编辑时状态为草稿-0，则转为可用状态-5
        if (record.getStatus() == 0) {
            record.setStatus(5);
        }
        try {
            return infoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑客户异常："+e.getMessage());
            if (infoMapper.getCustomerByIdNumber(record.getIdNumber()) != null) {
                throw new MyException("身份证号已存在");
            }
            throw new MyException("编辑客户信息出现异常");
        }
    }

    /**
     * 修改客户信息-草稿，不做参数校验
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateDraft(CustomerInfo record) throws Exception {
        if (ValidUtil.isNotLength(record.getCustomerName(), 2, 20)) {
            throw new MyException("请输入2-20位姓名");
        }
        if (ValidUtil.isNotLength(record.getIdNumber(), 18)) {
            throw new MyException("请输入18位身份证号");
        }
        if (ValidUtil.isEmpty(record.getGridCode())) {
            throw new MyException("请先选择网格");
        }
        // 登记人
        if (ValidUtil.isNotLength(record.getRegistrant(),2, 20)) {
            throw new MyException("登记人不能为空");
        }
        // 登记机构
        if (ValidUtil.isEmpty(record.getRegisterOrg())) {
            throw new MyException("登记机构不能为空");
        }

        // 以上为必填项,以下对输入项进行参数校验****************************************
        // 户号
        if (ValidUtil.isNotEmpty(record.getHouseholdId()) && ValidUtil.isNotLength(record.getHouseholdId(), 2, 20)) {
                throw new MyException("请输入户号,最长20位");
        }
        // 出生年月
        if (ValidUtil.isNotEmpty(record.getBirthday()) && ValidUtil.isNotLength(record.getBirthday(), 8, 10)) {
            throw new MyException("请选择出生日期");
        }
        // 手机号码
        if (ValidUtil.isNotEmpty(record.getPhoneNumber()) && ValidUtil.isNotPhone(record.getPhoneNumber())) {
            throw new MyException("请输入1开头的11位手机号");
        }
        // 户籍地址
        if (ValidUtil.isNotEmpty(record.getNativeAddress()) && ValidUtil.isNotLength(record.getNativeAddress(), 2, 100)) {
            throw new MyException("请输入户籍地址");
        }
        // 居住地址
        if (ValidUtil.isNotEmpty(record.getResidenceAddress()) && ValidUtil.isNotLength(record.getResidenceAddress(), 2, 100)) {
            throw new MyException("请输入居住地址");
        }
        // 居住地邮编
        if (ValidUtil.isNotEmpty(record.getPostcode()) && ValidUtil.isNotLength(record.getPostcode(), 6)) {
            throw new MyException("请输入6位邮编");
        }
        // 调查人A
        if (ValidUtil.isNotEmpty(record.getInvestigator1()) && ValidUtil.isNotLength(record.getInvestigator1(), 2, 20)) {
            throw new MyException("请输入调查人(A角)");
        }
        // 调查人B
        if (ValidUtil.isNotEmpty(record.getInvestigator2()) && ValidUtil.isNotLength(record.getInvestigator2(), 2, 20)) {
            throw new MyException("请输入调查人(B角)");
        }

        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        record.setStatus(0); // 草稿状态
        try {
            return infoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑暂存客户异常："+e.getMessage());
            if (infoMapper.getCustomerByIdNumber(record.getIdNumber()) != null) {
                throw new MyException("身份证号已存在");
            }
            throw new MyException("编辑暂存客户信息出现异常");
        }
    }

    /**
     * 修改客户信息可用状态
     *0-冻结，1-解冻
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateCustomerStatus(CustomerInfo record) throws Exception {

        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return infoMapper.updateCustomerStatus(record) == 1;
        } catch (Exception e) {
            logger.info("编辑客户状态异常："+e.getMessage());
            throw new MyException("编辑客户信息状态出现异常");
        }
    }

    /**
     * 批量修改客户信息可用状态
     *
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public boolean batchUpdateStatus(List<CustomerInfo> list) throws Exception {
        if (list.isEmpty()) {
            throw new MyException("批量修改不能为空");
        }
        // 修改时间
        long now = System.currentTimeMillis();
        for (CustomerInfo info : list) {
            info.setUpdatedAt(now);
        }
        try {
            return infoMapper.batchUpdateStatus(list) == 1;
        } catch (Exception e) {
            logger.info("批量编辑客户异常："+e.getMessage());
            throw new MyException("批量编辑客户信息状态出现异常");
        }
    }
}
