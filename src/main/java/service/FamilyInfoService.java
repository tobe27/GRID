package service;

import model.FamilyInfo;

/**
 * @author Created by L.C.Y on 2018-10-10
 */
public interface FamilyInfoService {
    /**
     * 删除家庭信息
     * @param familyId
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long familyId) throws Exception;

    /**
     * 新添加家庭信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(FamilyInfo record) throws Exception;

    /**
     * 获取家庭信息
     * @param householdId
     * @return
     * @throws Exception
     */
    FamilyInfo getByHouseholdId(Long householdId) throws Exception;

    /**
     * 编辑家庭信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(FamilyInfo record) throws Exception;
}
