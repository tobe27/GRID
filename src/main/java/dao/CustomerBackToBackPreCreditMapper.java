package dao;

import java.util.List;
import java.util.Map;

import model.CustomerBackToBackPreCredit;

public interface CustomerBackToBackPreCreditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerBackToBackPreCredit record);

    int insertSelective(CustomerBackToBackPreCredit record);

    CustomerBackToBackPreCredit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerBackToBackPreCredit record);

    int updateByPrimaryKey(CustomerBackToBackPreCredit record);
    
 List<CustomerBackToBackPreCredit> getByIdOrIdnumber(CustomerBackToBackPreCredit record);
    
    List<CustomerBackToBackPreCredit> getBackToBackPreCreditList(Map<String,Object> map);
    
}