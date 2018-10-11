package service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import model.CustomerImage;

public interface CustomerImageService {

	    boolean insertSelective(MultipartFile file,CustomerImage customerImage) throws Exception;

	    CustomerImage selectByPrimaryKey(Long id) throws Exception;

	    boolean updateByPrimaryKeySelective(CustomerImage record) throws Exception;
       List<Map<String,Object>> getListByIdNumberAndType(CustomerImage record) throws Exception;
       boolean delete(Long id) throws Exception;

}
