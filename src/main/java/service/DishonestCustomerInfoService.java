package service;

import model.DishonestCustomerInfo;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface DishonestCustomerInfoService {
    /**
     * 删除失信人
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long id) throws Exception;

    /**
     * 添加失信人
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(DishonestCustomerInfo record) throws Exception;

    /**
     * 获取失信人
     * @param id
     * @return
     * @throws Exception
     */
    DishonestCustomerInfo getByPrimaryKey(Long id) throws Exception;

    /**
     * 获取失信人名单列表
     * @param record
     * @return
     * @throws Exception
     */
    List<DishonestCustomerInfo> listByPerformedNameOrCardNumber(DishonestCustomerInfo record) throws Exception;

    /**
     * 精确获取失信人名单
     * @param cardNumber
     * @return
     * @throws Exception
     */
    List<DishonestCustomerInfo> listByCardNumber(String cardNumber) throws Exception;

    /**
     * 编辑失信人
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(DishonestCustomerInfo record) throws Exception;
}
