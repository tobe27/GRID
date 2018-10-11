package dao;

import java.util.List;

import model.CustomerImage;

public interface CustomerImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerImage record);

    int insertSelective(CustomerImage record);

    CustomerImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerImage record);

    int updateByPrimaryKey(CustomerImage record);
    List<CustomerImage> getImageListByIdNumberAndType(CustomerImage record);
}