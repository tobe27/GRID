package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import model.GridDictionary;

import model.ResponseData;
import service.GridDictionaryService;

@RestController
@RequestMapping("super")
public class GridDictionaryController {
    @Autowired
    private GridDictionaryService gridDictionaryService;
    /**
     * 调用此接口删除字典项
     * @param code 字典code
     * @return
     * @throws Exception
     */
    
   
  @RequestMapping(value = "/dictionary/{code}", method = RequestMethod.DELETE)
  public ResponseData deleteDicrionary(@PathVariable("code") String code){
	  if(code==null || "".equals(code)) {
	    	return new ResponseData().code(400).message("参数缺失");
	    }
        try {
        	gridDictionaryService.deleteByCode(code);
        	  return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().code(400).message(e.getMessage());
        }
		
       
    }
  /**
   * 调用此接口新增字典组
   * @param List<Dictionary>
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/dictionary",method = RequestMethod.POST)
	 public ResponseData addDirctionaryList(  @RequestBody List<GridDictionary> list) {
	      //判断传入的key是否重复
		  Set<Long> set=new HashSet<>();
		 if(list==null || list.isEmpty()) {
			 return new ResponseData().code(400).message("参数缺失");
		 }
		 for(GridDictionary gr:list) {
			 if(gr.getName()==null || "".equals(gr.getName())) {
				 return new ResponseData().code(400).message("字典组名不能为空");
			 }
			 if(gr.getDictionaryKey()==null ) {
				 return new ResponseData().code(400).message("字典项key不能为空");
			 }
			 if(gr.getDictionaryValue()==null || "".equals(gr.getDictionaryValue())) {
				 return new ResponseData().code(400).message("字典项value不能为空");
			 }
			 set.add(gr.getDictionaryKey());
		 }
		 if(set.size()!=list.size()) {
			 return new ResponseData().code(400).message("字典项Key不能重复");
		 }
		
		 
		 
	        try {
	        	gridDictionaryService.addGridDictionaryList(list);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
  /**
   * 调用此接口修改字典组
   * @param List<Dictionary>
   * @return
   * @throws Exception
   */
  
  @RequestMapping(value = "/dictionary",method = RequestMethod.PUT)
	 public ResponseData updateDirctionaryList( @RequestBody List<GridDictionary> list) {
	      //判断传入的key是否重复
		  Set<Long> set=new HashSet<>();
		 if(list==null || list.isEmpty()) {
			 return new ResponseData().code(400).message("参数缺失");
		 }
		 for(GridDictionary gr:list) {
			 if(gr.getName()==null || "".equals(gr.getName())) {
				 return new ResponseData().code(400).message("字典组名不能为空");
			 }
			 if(gr.getDictionaryKey()==null ) {
				 return new ResponseData().code(400).message("字典项key不能为空");
			 }
			 if(gr.getCode()==null ||"".equals(gr.getCode())) {
				 return new ResponseData().code(400).message("字典组code不能为空");
			 }
			 if(gr.getDictionaryValue()==null || "".equals(gr.getDictionaryValue())) {
				 return new ResponseData().code(400).message("字典项value不能为空");
			 }
			 set.add(gr.getDictionaryKey());
		 }
		 if(set.size()!=list.size()) {
			 return new ResponseData().code(400).message("字典项Key不能重复");
		 }
		
		 
	        try {
	        	gridDictionaryService.updateGridDictionaryList(list);
	            return new ResponseData().success();
	        } catch (Exception e) {
	            return new ResponseData().code(400).message(e.getMessage());
	        }
	    }
     
  
  /**
   * 分页和条件显示字典列表
   * @param pageNo,PageSize
   * @return
   * @throws Exception
   */
@RequestMapping(value = "/dictionary/list", method = RequestMethod.GET)
public ResponseData getDictionaryList(int pageNo,int pageSize,String name) {
	  
	  PageInfo<GridDictionary> pageInfo;
	  try {
      	 pageInfo = gridDictionaryService.getGridDictionaryListByPage(pageNo, pageSize, name);
      } catch (Exception e) {
          return new ResponseData().code(400).message("查询字典信息出错");
      }
    
		return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
	
}
/**
 * 根据code查询显示字典组详情
 * @param code
 * @return
 * @throws Exception
 */
 @RequestMapping(value = "/dictionary/{code}", method = RequestMethod.GET)
 public ResponseData getDictionaryByCode(@PathVariable("code") String code) {
	 try {
		 List<GridDictionary> list=gridDictionaryService.selectByCode(code);
         return new ResponseData().success().data(list);
     } catch (Exception e) {
         return new ResponseData().code(400).message(e.getMessage());
     }
 }
  
    
}
