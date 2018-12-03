package service.impl;

import dao.FamilyMemberMapper;
import exception.MyException;
import model.FamilyMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FamilyMemberService;
import util.ValidUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-11-5
 */
@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {
    @Autowired
    FamilyMemberMapper familyMemberMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除家庭成员
     *
     * @param memberId
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long memberId) throws Exception {
        try {
            return familyMemberMapper.deleteByPrimaryKey(memberId) == 1;
        } catch (Exception e) {
            logger.info("删除成员异常："+e.getMessage());
            throw new Exception("删除家庭成员出现异常！");
        }
    }

    /**
     * 新建家庭成员
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(FamilyMember record) throws Exception {
        if (!ValidUtil.isLength(record.getMemberName(), 2, 20)) {
            throw new MyException("请输入2-20字姓名");
        }
        if (ValidUtil.isEmpty(record.getRelationship())) {
            throw new MyException("请选择与该客户的关系");
        }
        if (ValidUtil.isNotLength(record.getBirthday(), 8, 10)) {
            throw new MyException("请选择出生日期");
        }
        if (ValidUtil.isEmpty(record.getIdType())) {
            throw new MyException("请选择证件类型");
        }
        if (!ValidUtil.isLength(record.getIdNumber(),18)) {
            throw new MyException("请输入18位有效证件号");
        }
        if (ValidUtil.isNotLength(record.getNation(), 1, 10)) {
            throw new MyException("请选择民族");
        }
        if (ValidUtil.isEmpty(record.getIsHouseholdHead())) {
            throw new MyException("请选择是否户主");
        }
        if (!ValidUtil.isPhone(record.getContact())) {
            throw new MyException("请输入1开头的11位手机号");
        }
        if (!ValidUtil.isLength(record.getAddress(), 1, 100)) {
            throw new MyException("请输入地址，最长100字");
        }
        if (!ValidUtil.isLength(record.getRemark(), 1, 100)) {
            throw new MyException("请填写备注，最长100字");
        }
        if (ValidUtil.isEmpty(record.getCustomerId())) {
            throw new MyException("客户号不能为空！");
        }


        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return familyMemberMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            logger.info("新建成员异常："+e.getMessage());
            throw new Exception("新建家庭成员出现异常！");
        }
    }

    /**
     * 查询家庭成员详情
     *
     * @param memberId
     * @return
     * @throws Exception
     */
    @Override
    public FamilyMember getByPrimaryKey(Long memberId) throws Exception {
        try {
            return familyMemberMapper.getByPrimaryKey(memberId);
        } catch (Exception e) {
            logger.info("查询成员异常："+e.getMessage());
            throw new Exception("查询家庭成员出现异常！");
        }
    }

    /**
     * 查询家庭成员列表
     *
     * @param customerId
     * @return
     * @throws Exception
     */
    @Override
    public List<FamilyMember> listByCustomerId(Long customerId) throws Exception {
        try {
            return familyMemberMapper.listByCustomerId(customerId);
        } catch (Exception e) {
            logger.info("查询成员列表异常："+e.getMessage());
            throw new Exception("查询家庭成员列表出现异常！");
        }
    }

    /**
     * 编辑家庭成员
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(FamilyMember record) throws Exception {
        if (!ValidUtil.isLength(record.getMemberName(), 2, 20)) {
            throw new MyException("请输入2-20字姓名");
        }
        if (ValidUtil.isEmpty(record.getRelationship())) {
            throw new MyException("请选择与该客户的关系");
        }
        if (ValidUtil.isNotLength(record.getBirthday(), 8, 10)) {
            throw new MyException("请选择出生日期");
        }
        if (ValidUtil.isEmpty(record.getIdType())) {
            throw new MyException("请选择证件类型");
        }
        if (!ValidUtil.isLength(record.getIdNumber(),18)) {
            throw new MyException("请输入18位有效证件号");
        }
        if (ValidUtil.isNotLength(record.getNation(), 1, 10)) {
            throw new MyException("请选择民族");
        }
        if (ValidUtil.isEmpty(record.getIsHouseholdHead())) {
            throw new MyException("请选择是否户主");
        }
        if (!ValidUtil.isPhone(record.getContact())) {
            throw new MyException("请输入1开头的11位手机号");
        }
        if (!ValidUtil.isLength(record.getAddress(), 1, 100)) {
            throw new MyException("请输入地址，最长100字");
        }
        if (!ValidUtil.isLength(record.getRemark(), 1, 100)) {
            throw new MyException("请填写备注，最长100字");
        }
        if (ValidUtil.isEmpty(record.getCustomerId())) {
            throw new MyException("客户号不能为空！");
        }

        // 创建时间
        record.setUpdatedAt(System.currentTimeMillis());
        try {
            return familyMemberMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑成员异常："+e.getMessage());
            throw new Exception("编辑家庭成员出现异常！");
        }
    }
}
