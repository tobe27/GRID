package controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import model.CustomerGreylist;
import model.CustomerImage;
import model.ResponseData;
import service.CustomerGreyListService;
import service.CustomerImageService;

@RestController
@RequestMapping("customer")
public class CustomerImageController {
	@Autowired
	private  CustomerImageService  customerImageService;
	
	/**
     * 调用此接口新增或者修改图片
     * @param MultipartFile file,CustomerImage customerImage
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/image",method = RequestMethod.POST)
	 public ResponseData addImage(@RequestParam MultipartFile file,CustomerImage customerImage) throws Exception {
			 customerImageService.insertSelective(file, customerImage);
			 return new ResponseData().success();
	}
	/**
     * 调用此接口删除图片
     * @param long id
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/image/{id}",method = RequestMethod.DELETE)
	 public ResponseData deleteImage(@PathVariable long  id) throws Exception {
			 customerImageService.delete(id);
			 return new ResponseData().success();
	}
	
	/**
     * 调用此接口获取某客户的某类型所有图片
     * @param long id
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/image/list/{idNumber}",method = RequestMethod.GET)
	 public ResponseData getImageBaseCodeList(@PathVariable String  idNumber,String type) throws Exception {
		CustomerImage customerImage=new CustomerImage();
		customerImage.setIdNumber(idNumber);
		customerImage.setType(type);
			 return new ResponseData().success().data( customerImageService.getListByIdNumberAndType(customerImage));
	}
	
	
	
}
