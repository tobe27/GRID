package service.impl;

import dao.FamilyInfoMapper;
import exception.MyException;
import model.FamilyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FamilyInfoService;

/**
 * @author Created by L.C.Y on 2018-10-10
 */
@Service
public class FamilyInfoServiceImpl implements FamilyInfoService {
    @Autowired
    FamilyInfoMapper familyInfoMapper;

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
            throw new MyException("删除家庭信息出现异常");
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
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return familyInfoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("添加家庭信息出现异常");
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
    public FamilyInfo getByHouseholdId(Long householdId) throws Exception {
        try {
            return familyInfoMapper.getByHouseholdId(householdId);
        } catch (Exception e) {
            throw new MyException("获取家庭信息出现异常");
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
        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return familyInfoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("编辑家庭信息出现异常");
        }
    }
}
