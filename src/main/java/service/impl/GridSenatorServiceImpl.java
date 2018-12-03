package service.impl;

import dao.GridSenatorMapper;
import dao.GridSenatorRelationMapper;
import exception.MyException;
import model.GridSenator;
import model.GridSenatorRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.GridSenatorService;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-11-23
 */
@Service
public class GridSenatorServiceImpl implements GridSenatorService {
    @Autowired
    GridSenatorMapper gridSenatorMapper;
    @Autowired
    GridSenatorRelationMapper gridSenatorRelationMapper;

    private static Logger logger = LoggerFactory.getLogger(GridSenatorServiceImpl.class);

    /**
     * 删除
     *
     * @param senatorId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean deleteByPrimaryKey(Long senatorId) throws Exception {
        if (gridSenatorRelationMapper.getBySenatorId(senatorId) != null) {
            throw new MyException("该评议员在网格中，不能删除！");
        }
        try {
            return gridSenatorMapper.deleteByPrimaryKey(senatorId) == 1;
        } catch (Exception e) {
            logger.info("删除评议员异常：" + e.getMessage());
            throw new MyException("删除评议员异常");
        }
    }

    /**
     * 新建
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(GridSenator record) throws Exception {
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return gridSenatorMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            logger.info("新建评议员异常：" + e.getMessage());
            throw new MyException("新建评议员异常");
        }
    }

    /**
     * 插入关联表
     * 插入之前先删除
     * 新建之前查询评议员有无被使用
     * @param gridCode   网格号
     * @param senatorIds 评议员编号，多个以“,”隔开
     * @return 插入个数
     * @throws Exception
     */
    @Override
    @Transactional
    public int insertRelation(String gridCode, String senatorIds) throws Exception {
        int count = 0;
        gridSenatorRelationMapper.deleteByGridCode(gridCode);
        if (!senatorIds.isEmpty()) {
            String senatorIdArr[] = senatorIds.split(",");
            for (String senatorIdStr : senatorIdArr) {
                long senatorId = Long.valueOf(senatorIdStr);
                if (gridSenatorRelationMapper.getBySenatorId(senatorId) != null) {
                    throw new MyException("该评议员已在其他网格中：" + senatorId);
                }
                GridSenatorRelation relation = new GridSenatorRelation();
                relation.setGridCode(gridCode).setSenatorId(senatorId);
                try {
                    gridSenatorRelationMapper.insertSelective(relation);
                } catch (Exception e) {
                    logger.info("网格评议员插入异常:" + e.getMessage());
                    throw new MyException("网格评议员插入异常");
                }
                count ++;
            }
        }
        return count;
    }

    /**
     * 查看
     *
     * @param senatorId
     * @return
     * @throws Exception
     */
    @Override
    public GridSenator selectByPrimaryKey(Long senatorId) throws Exception {
        try {
            return gridSenatorMapper.selectByPrimaryKey(senatorId);
        } catch (Exception e) {
            logger.info("查看评议员异常：" + e.getMessage());
            throw new MyException("查看评议员异常");
        }
    }

    /**
     * 列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<GridSenator> listByType(GridSenator record) throws Exception {
        try {
            return gridSenatorMapper.listByType(record);
        } catch (Exception e) {
            logger.info("查看评议员列表异常：" + e.getMessage());
            throw new MyException("查看评议员列表异常");
        }
    }

    /**
     * 通过网格号查询列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<GridSenator> listByGridCode(GridSenator record) throws Exception {
        try {
            return gridSenatorMapper.listByGridCode(record);
        } catch (Exception e) {
            logger.info("网格编号查看评议员列表异常：" + e.getMessage());
            throw new MyException("网格编号查看评议员列表异常");
        }
    }

    /**
     * 编辑
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(GridSenator record) throws Exception {
        // 编辑时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return gridSenatorMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑评议员异常：" + e.getMessage());
            throw new MyException("编辑评议员异常");
        }
    }
}
