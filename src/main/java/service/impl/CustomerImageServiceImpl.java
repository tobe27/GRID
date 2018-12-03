package service.impl;

import dao.CustomerCreditDetailMapper;
import dao.CustomerImageMapper;
import exception.MyException;
import model.CustomerCreditDetail;
import model.CustomerImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	@Autowired
	private CustomerCreditDetailMapper customerCreditDetailMapper;

	
	
	/***
	 * 上传图片接口
	 * 
	 * ***/
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
	    if("2".equals(customerImage.getType())) {
	    	fileName=customerImage.getIdNumber()+now+"资产信息";
	    }
	    if("3".equals(customerImage.getType())) {
	    	fileName=customerImage.getIdNumber()+now+"经营信息";
	    }
	    if("5".equals(customerImage.getType())) {
	    	fileName=customerImage.getIdNumber()+now+"押品信息";
	    }
	    if("6".equals(customerImage.getType())) {
	    	fileName=customerImage.getIdNumber()+now+"其他信息";
	    }
	    
	    //上传的是合同表的话需要去更改附件上传状态
	    CustomerCreditDetail customerCreditDetail=null;
	    
	    if("4.1".equals(customerImage.getType())) {
	    	if(customerImage.getCreditDetailId()==null) {
	    		throw new MyException("上传合同表时请同步上传面谈面签id");
	    	}
	    	
	    	customerCreditDetail=customerCreditDetailMapper.selectByPrimaryKey(customerImage.getCreditDetailId());
			   if(customerCreditDetail.getAttachFlag().endsWith("1")) {
				   customerCreditDetail.setAttachFlag("1.1");
			   }else {
				   customerCreditDetail.setAttachFlag("1.0");
			   }
	    	fileName=customerImage.getIdNumber()+"-"+customerImage.getCreditDetailId()+"-"+now+"合同";
	    }
	    //上传的是面签表的话需要去更改附件上传状态
	    if("4.2".equals(customerImage.getType())) {
	    	if(customerImage.getCreditDetailId()==null) {
	    		throw new MyException("上传面签表时请同步上传面谈面签id");
	    	}
	    	customerCreditDetail=customerCreditDetailMapper.selectByPrimaryKey(customerImage.getCreditDetailId());
	    	 if(customerCreditDetail.getAttachFlag().startsWith("0")) {
				   customerCreditDetail.setAttachFlag("0.1");
			   }else {
				   customerCreditDetail.setAttachFlag("1.1");
			   }
	    	
	    	fileName=customerImage.getIdNumber()+"-"+customerImage.getCreditDetailId()+"-"+now+"面签表";
	    }
	    //保存图片
	   Map<String,Object> map= PoiUtil.saveImage(file, customerImage.getType(), fileName, customerImage.getIdNumber());
	   if(map==null) {
		   throw new MyException("保存图片出现异常"); 
	   }
	   customerImage.setCreatedAt(now);
	   customerImage.setUpdatedAt(now);
	   customerImage.setSystemName(fileName+file.getOriginalFilename());
	   customerImage.setPath(map.get("path").toString());
	   try {
		   //如果传入的图片是有id的，代表是修改图片 需要把原来的图片移到回收站中
		if(customerImage.getId() !=null) {
			movieImage(customerImage.getId());
			}
		   customerImage.setId(null);
		   
		if(customerCreditDetail !=null) {
			 customerCreditDetail.setUpdatedAt(System.currentTimeMillis());
			   customerCreditDetailMapper.updateByPrimaryKeySelective(customerCreditDetail);
		}
			return  customerImageMapper.insertSelective(customerImage)==1;
	} catch (Exception e) {
		e.printStackTrace();
		 throw new MyException("保存数据出现异常"); 
	}
	}
	/***
	 * 
	 * 将废图片移入回收站
	 * **/
	  @Transactional
	 public void movieImage(long id) {
		 CustomerImage image= customerImageMapper.selectByPrimaryKey(id);
		  Map<String,Object> mapDelete=PoiUtil.moveFileToRecycle(image.getPath(), "image");
		  image.setDeleteFlag("1");
		  image.setDeleteTime(System.currentTimeMillis());
		  image.setDeletePath(mapDelete.get("path").toString());
		  customerImageMapper.updateByPrimaryKeySelective(image);
		  //如果是合同或者面谈面签，需要将待面谈面签数据的附件状态来修改回来
		  CustomerCreditDetail customerCreditDetail=null;
		  //合同表
		  if("4.1".equals(image.getType())) {
			  //先查一下是否还有未删除的合同表,如果没有了，则改回面谈面签的附件状态
			  List<CustomerImage> list=customerImageMapper.getListByCreditdetailId(image);
			  if(list.isEmpty()) {
				  customerCreditDetail=customerCreditDetailMapper.selectByPrimaryKey(image.getCreditDetailId());
				   if(customerCreditDetail.getAttachFlag().endsWith("1")) {
					   customerCreditDetail.setAttachFlag("0.1");
				   }else {
					   customerCreditDetail.setAttachFlag("0.0");
				   }
				 customerCreditDetail.setUpdatedAt(System.currentTimeMillis());
				 customerCreditDetailMapper.updateByPrimaryKeySelective(customerCreditDetail);
			  }
			 
		  }
		  //面签表
		  if("4.2".equals(image.getType())) {
			  customerCreditDetail=customerCreditDetailMapper.selectByPrimaryKey(image.getCreditDetailId());
		    	 if(customerCreditDetail.getAttachFlag().startsWith("0")) {
					   customerCreditDetail.setAttachFlag("0.0");
				   }else {
					   customerCreditDetail.setAttachFlag("1.0");
				   }
		    	 customerCreditDetail.setUpdatedAt(System.currentTimeMillis());
				  customerCreditDetailMapper.updateByPrimaryKeySelective(customerCreditDetail);
		  }
		 
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
				if(file.exists()==false) {
					throw new MyException("文件未找到"); 
				}
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
