package service.impl;

import dao.DishonestCustomerInfoMapper;
import dao.ResidentInfoMapper;
import exception.MyException;
import model.DishonestCustomerInfo;
import model.ResidentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DishonestCustomerInfoService;
import util.QueryDishonestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class DishonestCustomerInfoServiceImpl implements DishonestCustomerInfoService {
    @Autowired
    DishonestCustomerInfoMapper dishonestCustomerInfoMapper;
    @Autowired
    ResidentInfoMapper residentInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 删除失信人
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long id) throws Exception {
        try {
            return dishonestCustomerInfoMapper.deleteByPrimaryKey(id) == 1;
        } catch (Exception e) {
            logger.info("删除失信人异常："+e.getMessage());
            throw new MyException("删除失信人出现异常!");
        }
    }

    /**
     * 添加失信人
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean insertSelective(DishonestCustomerInfo record) throws Exception {
        // 创建时间
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        int count = 0;
        List<Long> longs = new ArrayList<>();
        try {
            List<DishonestCustomerInfo> list = dishonestCustomerInfoMapper.listByCardNumber(record.getCardNumber());
            if (list == null || list.isEmpty()) {
                count = dishonestCustomerInfoMapper.insertSelective(record);
            } else {
                for (DishonestCustomerInfo info : list) {
                    longs.add(info.getPublishedAt());
                }
                if (!longs.contains(record.getPublishedAt())){
                    count = dishonestCustomerInfoMapper.insertSelective(record);
                }
            }
            return count == 1;
        } catch (Exception e) {
            logger.info("新建失信人异常："+e.getMessage());
            throw new MyException("新建失信人出现异常!");
        }
    }


    /**
     * 获取失信人
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public DishonestCustomerInfo getByPrimaryKey(Long id) throws Exception {
        try {
            return dishonestCustomerInfoMapper.getByPrimaryKey(id);
        } catch (Exception e) {
            logger.info("查询失信人异常："+e.getMessage());
            throw new MyException("查询失信人出现异常!");
        }
    }

    /**
     * 获取失信人名单列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<DishonestCustomerInfo> listByPerformedNameOrCardNumber(DishonestCustomerInfo record) throws Exception {
        try {
            return dishonestCustomerInfoMapper.listByPerformedNameOrCardNumber(record);
        } catch (Exception e) {
            logger.info("查询失信人异常："+e.getMessage());
            throw new MyException("查询失信人名单列表出现异常!");
        }
    }

    /**
     * 精确获取失信人名单
     *
     * @param cardNumber
     * @return
     * @throws Exception
     */
    @Override
    public List<DishonestCustomerInfo> listByCardNumber(String cardNumber) throws Exception {
        try {
            return dishonestCustomerInfoMapper.listByCardNumber(cardNumber);
        } catch (Exception e) {
            logger.info("精确查询失信人异常："+e.getMessage());
            throw new MyException("精确查询失信人名单出现异常!");
        }
    }

    /**
     * 编辑失信人
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(DishonestCustomerInfo record) throws Exception {
        // 修改时间
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return dishonestCustomerInfoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            logger.info("编辑失信人异常："+e.getMessage());
            throw new MyException("编辑失信人出现异常!");
        }
    }
}
