package service.impl;

import dao.DishonestCustomerInfoMapper;
import exception.MyException;
import model.DishonestCustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DishonestCustomerInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class DishonestCustomerInfoServiceImpl implements DishonestCustomerInfoService {
    @Autowired
    DishonestCustomerInfoMapper dishonestCustomerInfoMapper;

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
            throw new MyException("删除失信人出现异常");
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
            e.printStackTrace();
            throw new MyException("添加失信人出现异常");
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
            throw new MyException("获取失信人出现异常");
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
            throw new MyException("获取失信人名单列表出现异常");
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
            throw new MyException("精确获取失信人名单出现异常");
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
            throw new MyException("编辑失信人出现异常");
        }
    }
}
