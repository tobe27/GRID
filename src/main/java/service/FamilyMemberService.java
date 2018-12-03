package service;

import model.FamilyMember;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-11-5
 */
public interface FamilyMemberService {
    /**
     * 删除家庭成员
     * @param memberId
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long memberId) throws Exception;

    /**
     * 新建家庭成员
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(FamilyMember record) throws Exception;

    /**
     * 查询家庭成员详情
     * @param memberId
     * @return
     * @throws Exception
     */
    FamilyMember getByPrimaryKey(Long memberId) throws Exception;

    /**
     * 查询家庭成员列表
     * @param customerId
     * @return
     * @throws Exception
     */
    List<FamilyMember> listByCustomerId(Long customerId) throws Exception;

    /**
     * 编辑家庭成员
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(FamilyMember record) throws Exception;
}
