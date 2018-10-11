package dao;

import model.FamilyInfo;

public interface FamilyInfoMapper {
    int deleteByPrimaryKey(Long familyId);

    int insertSelective(FamilyInfo record);

    FamilyInfo getByHouseholdId(Long householdId);

    int updateByPrimaryKeySelective(FamilyInfo record);
}