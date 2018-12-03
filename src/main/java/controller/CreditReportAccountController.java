package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import model.CreditReportAccount;
import model.ResponseData;
import service.CreditReportAccountService;

@RestController
@RequestMapping("grid")
public class CreditReportAccountController {
	@Autowired
	private CreditReportAccountService creditReportAccountService;
	
	/**
     * 调用此接口新增账户
     * @param CreditReportAccount 
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/CreditReportAccount",method = RequestMethod.POST)
	 public ResponseData addCreditReportAccount( @RequestBody CreditReportAccount creditReportAccount) {
		try {
			creditReportAccountService.insertSelective(creditReportAccount);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	 /**
	   * 调用此接口修改账户信息
	   * @param CreditReportAccount
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/CreditReportAccount/{id}",method = RequestMethod.PUT)
	 public ResponseData updateCreditReportAccount(@RequestBody CreditReportAccount creditReportAccount) {
		try {
			creditReportAccountService.updateByPrimaryKeySelective(creditReportAccount);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	/**
     * 调用此接口删除账户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/CreditReportAccount/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteCreditReportAccount(@PathVariable long id) {
        try {
        	creditReportAccountService.deleteByPrimaryKey(id);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }

    
    /**
     * 分页和条件显示账户信息列表
     * @param pageNo,PageSize
     * @return
     * @throws Exception
     */
  @RequestMapping(value = "/CreditReportAccount/list", method = RequestMethod.GET)
  public ResponseData getListByPage(@RequestParam Map<String,Object>  map) {
	 
	  PageInfo<CreditReportAccount> pageInfo ;
	 
	  try {
		  List<CreditReportAccount> list=  creditReportAccountService.getList(map);
		  pageInfo=new PageInfo<>(list);
        } catch (Exception e) {
            return new ResponseData().code(400).message("查询账户信息出错");
        }
       return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }
  /**
   * 调用此接口查看账户信息
   * @param
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/CreditReportAccount/{id}",method = RequestMethod.GET)
  public ResponseData selectCreditReportAccount(@PathVariable long id) throws Exception {
          return new ResponseData().success().data(creditReportAccountService.selectByPrimaryKey(id));
   }

    
}
