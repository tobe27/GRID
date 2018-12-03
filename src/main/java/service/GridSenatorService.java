package service;

import model.GridSenator;
import model.GridSenatorRelation;

import java.util.List;

/**
 * 网格评议员
 * @author Created by L.C.Y on 2018-11-23
 */
public interface GridSenatorService {
    /**
     * 删除
     * @param senatorId
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long senatorId) throws Exception;

    /**
     * 新建
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(GridSenator record) throws Exception;

    /**
     * 插入关联表
     * @param gridCode 网格号
     * @param senatorIds 评议员编号，多个以“,”隔开
     * @return 插入个数
     * @throws Exception
     */
    int insertRelation(String gridCode, String senatorIds) throws Exception;

    /**
     * 查看
     * @param senatorId
     * @return
     * @throws Exception
     */
    GridSenator selectByPrimaryKey(Long senatorId) throws Exception;

    /**
     * 列表
     * @param record
     * @return
     * @throws Exception
     */
    List<GridSenator> listByType(GridSenator record) throws Exception;

    /**
     * 通过网格号查询列表
     * @param record
     * @return
     * @throws Exception
     */
    List<GridSenator> listByGridCode(GridSenator record) throws Exception;

    /**
     * 编辑
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(GridSenator record) throws Exception;
}
