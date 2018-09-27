package dao;

import model.CustomerInfo;
import model.TagCustomer;

import java.util.List;
import java.util.Set;

public interface TagCustomerMapper {

    int deleteTagByIdNumber(String idNumber);

    int deleteTagByIdNumberAndTagId(TagCustomer record);

    int deleteTagByTagId(Long tagId);

    int insertSelective(TagCustomer record);

    Set<TagCustomer> listTagsByIdNumber(String idNumber);

    int countCustomersByTagId(Long tagId);

    List<CustomerInfo> listCustomersByTagId(Long tagId);

    List<TagCustomer> getListByTagIdAndIdNumber(TagCustomer record);

    int updateByPrimaryKeySelective(TagCustomer record);
}