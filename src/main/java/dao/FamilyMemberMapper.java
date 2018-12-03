package dao;

import model.FamilyMember;

import java.util.List;

public interface FamilyMemberMapper {
    int deleteByPrimaryKey(Long memberId);

    int insertSelective(FamilyMember record);

    FamilyMember getByPrimaryKey(Long memberId);

    FamilyMember getByCustomerIdAndIdNumber(FamilyMember record);

    List<FamilyMember> listByCustomerId(Long customerId);

    int updateByPrimaryKeySelective(FamilyMember record);

}