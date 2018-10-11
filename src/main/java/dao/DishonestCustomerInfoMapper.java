package dao;

import model.DishonestCustomerInfo;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface DishonestCustomerInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(DishonestCustomerInfo record);

    DishonestCustomerInfo getByPrimaryKey(Long id);

    List<DishonestCustomerInfo> listByPerformedNameOrCardNumber(DishonestCustomerInfo record);

    List<DishonestCustomerInfo> listByCardNumber(String cardNumber);

    int updateByPrimaryKeySelective(DishonestCustomerInfo record);
}