package service.impl;

import dao.FamilyInfoMapper;
import exception.MyException;
import model.FamilyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FamilyInfoService;
import util.ValidUtil;

/**
 * @author Created by L.C.Y on 2018-10-10
 */
@Service
public class FamilyInfoServiceImpl implements FamilyInfoService {
    @Autowired
    FamilyInfoMapper familyInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除家庭信息
     *
     * @param familyId
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long familyId) throws Exception {
        try {
            return familyInfoMapper.deleteByPrimaryKey(familyId) == 1;
        } catch (Exception e) {
            logger.info("删除家庭异常："+e.getMessage());
            throw new MyException("删除家庭信息出现异常!");
        }
    }

    /**
     * 新添加家庭信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(FamilyInfo record) throws Exception {
        if (ValidUtil.isEmpty(record.getHouseholdId())) {
            throw new MyException("请输入户号");
        }
        if (!ValidUtil.isNumber(record.getPopulation())) {
            throw new MyException("请输入家庭人口");
        }
        if (!ValidUtil.isLength(record.getPostcode(), 6)) {
            throw new MyException("请输入6位邮政编码");
        }
        if (ValidUtil.isEmpty(record.getAddress())) {
            throw new MyException("请输入家庭详细地址");
        }
        if (!ValidUtil.isLength(record.getSocialEvaluation(), 1, 100)) {
            throw new MyException("请输入村委会评价, 限长100");
        }

        if (familyInfoMapper.getByHouseholdId(record.getHouseholdId()) != null) {
            throw new MyException("户号已存在，请勿重复创建");
        }


        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return familyInfoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            logger.info("新建家庭异常："+e.getMessage());
            throw new MyException("新建家庭信息出现异常!");
        }
    }

    /**
     * 获取家庭信息
     *
     * @param householdId
     * @return
     * @throws Exception
     */
    @Override
    public FamilyInfo getByHouseholdId(String householdId) throws Exception {
        try {
            return familyInfoMapper.getByHouseholdId(householdId);
        } catch (Exception e) {
            logger.info("查询家庭异常："+e.getMessage());
            throw new MyException("查询家庭信息出现异常!");
        }
    }

    /**
     * 编辑家庭信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(FamilyInfo record) throws Exception {
        if (ValidUtil.isEmpty(record.getHouseholdId())) {
            throw new MyException("请输入户号");
        }
        if (!ValidUtil.isNumber(record.getPopulation())) {
            throw new MyException("请输入家庭人口");
        }
        if (!ValidUtil.isLength(record.getPostcode(), 6)) {
            throw new MyException("请输入6位邮政编码");
        }
        if (ValidUtil.isEmpty(record.getAddress())) {
            throw new MyException("请输入家庭详细地址");
        }
        if (!ValidUtil.isLength(record.getSocialEvaluation(), 1, 100)) {
            throw new MyException("请输入村委会评价, 限长100");
        }

        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return familyInfoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑家庭异常："+e.getMessage());
            if (familyInfoMapper.getByHouseholdId(record.getHouseholdId()) != null) {
                throw new MyException("户号已存在，请勿重复创建");
            }
            throw new MyException("编辑家庭信息出现异常!");
        }
    }
}
