package service.impl;

import dao.CustomerImageMapper;
import exception.MyException;
import model.CustomerImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.CustomerImageService;
import util.Base64Util;
import util.PoiUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CustomerImageServiceImpl implements CustomerImageService {
	@Autowired
	private CustomerImageMapper customerImageMapper;

	@Override
	public boolean insertSelective(MultipartFile file,CustomerImage customerImage) throws Exception {
		
		
		
		if(customerImage.getCustomerName()==null ||"".equals(customerImage.getCustomerName())) {
			throw new MyException("客户名不能为空");
		}
		if(customerImage.getIdNumber()==null ||"".equals(customerImage.getIdNumber())) {
			throw new MyException("客户身份证号不能为空");
		}
		if(customerImage.getUploadAccountId()==null ) {
			throw new MyException("客户经理编号不能为空");
		}
		if(customerImage.getType()==null || "".equals(customerImage.getType())) {
			throw new MyException("请选择图片的分类");
		}
		if(file==null) {
			throw new MyException("请选择您要上传的图片");
		}
		if(PoiUtil.isImage(file)==false) {
			throw new MyException("您上传的不是图片");
		}
	    long now =System.currentTimeMillis();
	    String fileName="";
	    if("1.1".equals(customerImage.getType())) {
	    	fileName=customerImage.getIdNumber()+now+"身份证正面";
	    }
	    if("1.2".equals(customerImage.getType())) {
	    	fileName=customerImage.getIdNumber()+now+"身份证背面";
	    }
	    if("4.1".equals(customerImage.getType())) {
	    	fileName=customerImage.getIdNumber()+now+"合同";
	    }
	    if("4.2".equals(customerImage.getType())) {
	    	fileName=customerImage.getIdNumber()+now+"面签表";
	    }
	   Map<String,Object> map= PoiUtil.saveImage(file, customerImage.getType(), fileName, customerImage.getIdNumber());
	   if(map==null) {
		   throw new MyException("保存图片出现异常"); 
	   }
	   customerImage.setCreatedAt(now);
	   customerImage.setUpdatedAt(now);
	   customerImage.setSystemName(fileName+file.getOriginalFilename());
	   customerImage.setPath(map.get("path").toString());
	   try {
		if(customerImage.getId() !=null) {
			movieImage(customerImage.getId());
			}
		   customerImage.setId(null);
		   
			return  customerImageMapper.insertSelective(customerImage)==1;
	} catch (Exception e) {
		 throw new MyException("保存数据出现异常"); 
	}
	}
	
	 public void movieImage(long id) {
		 CustomerImage image= customerImageMapper.selectByPrimaryKey(id);
		  Map<String,Object> mapDelete=PoiUtil.moveFileToRecycle(image.getPath(), "image");
		  image.setDeleteFlag("1");
		  image.setDeleteTime(System.currentTimeMillis());
		  image.setDeletePath(mapDelete.get("path").toString());
		  customerImageMapper.updateByPrimaryKeySelective(image);
		 
	 }

	@Override
	public CustomerImage selectByPrimaryKey(Long id) throws Exception {
		
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(CustomerImage record) throws Exception {
		
		return false;
	}

	@Override
	public List<Map<String,Object>> getListByIdNumberAndType(CustomerImage record) throws Exception {
		if(record.getIdNumber()==null || "".equals(record.getIdNumber()) || record.getType()==null ||"".equals(record.getType())) {
			throw new MyException("查询参数异常异常"); 
		}
		List<CustomerImage> list = null;
		try {
			list = customerImageMapper.getImageListByIdNumberAndType(record);
		} catch (Exception e) {
			throw new MyException("查询数据异常"); 
			
		}
		List<Map<String,Object>> returnList=new ArrayList<>();
		//后期加入判断权限
		if(list !=null &&list.size()>0) {
			for(CustomerImage customerImage:list) {
				Map<String,Object> map=new HashMap<>();
				File file=new File(customerImage.getPath());
				String baseCode="data:image/jpg;base64,";
				baseCode=baseCode+ Base64Util.encodeBase64(file);
				map.put("id",customerImage.getId() );
				map.put("baseCode", baseCode);
				returnList.add(map);
			}
			
		}
		return returnList;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		try {
			if(id !=null) {
				 movieImage( id);
			}
		} catch (Exception e) {
			 throw new MyException("修改数据出现异常"); 
			
		}
		return true;
	}

}
