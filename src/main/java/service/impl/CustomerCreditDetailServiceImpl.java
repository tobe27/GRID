package service.impl;

import com.github.pagehelper.PageHelper;
import dao.*;
import exception.MyException;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CustomerCreditDetailService;
import util.ValidUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CustomerCreditDetailServiceImpl implements CustomerCreditDetailService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CustomerCreditDetailMapper customerCreditDetailMapper;
    @Autowired
    private GridInfoMapper gridInfoMapper;
    @Autowired
    private CustomerWhitelistMapper customerWhitelistMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private CustomerGreylistMapper customerGreylistMapper;
    @Autowired
    private CustomerBlackListMapper customerBlackListMapper;
    @Autowired
    private TagCustomerMapper tagCustomerMapper;
    @Autowired
    private FamilyMemberMapper familyMemberMapper;
    @Override
    public Map<String,Object> insertByExcel(List<Map<String,Object>> list,Map<String,Object> paramsMap) {
        int successCount=0;
        int failCount=0;
        long now=System.currentTimeMillis();
        for(Map<String,Object> map:list) {

            CustomerCreditDetail customerCreditDetail=new CustomerCreditDetail();
            //如果身份证号为空则跳过
            if(map.get("2")==null||"".equals(map.get("2").toString())) {
            	failCount++;
            	continue;
            }
            if("合计".equals(map.get("0").toString())) {
            	failCount++;
            	continue;
            }
            CustomerInfo customer=customerInfoMapper.getCustomerByIdNumber(map.get("2").toString());
            if(customer==null||customer.getCustomerId()==null) {
            	failCount++;
            	continue;
            }
            try {
                customerCreditDetail.setIdNumber(map.get("2").toString()).setCustomerName(map.get("1").toString())
                        .setAccountId(paramsMap.get("accountId").toString()).setSex(customer.getSex()).setEducationLevel(customer.getEducationLevel())
                        .setPhoneNumber(customer.getPhoneNumber()).setCompanyName(customer.getCompanyName()).setCompanyAddress(customer.getCompanyAddress())
                        .setNativeAddress(customer.getNativeAddress()).setResidenceAddress(customer.getResidenceAddress()).setJnydName(map.get("7").toString())
                        .setJnydId(map.get("8").toString()).setRental(map.get("3").toString()).setDeadline(map.get("4").toString()).setBeginAt((map.get("5").toString())).setEndAt(map.get("6").toString())
                        .setRateType(map.get("9").toString()).setYearRate(map.get("10").toString()).setRateFloat(map.get("11").toString()).setRateUpdateDate(map.get("12").toString()).setDisbursement(map.get("13").toString())
                        .setRefundType(map.get("14").toString()).setCreditUseType(map.get("15").toString()).setIncome(map.get("16").toString()).setFamilyIncome(map.get("17").toString()).setAdvisersFlag(map.get("22").toString())
                        .setAdvisersIncome(map.get("23").toString()).setAdvisersExpense(map.get("24").toString()).setAdvisersRental(map.get("25").toString()).setAdvisersName(map.get("26").toString())
                        .setAdvisersComment(map.get("27").toString()).setTrueCustomerInfo(map.get("18").toString()).setTrueCustomerTalk(map.get("19").toString()).setStaffName(map.get("20").toString())
                        .setTalkDate(map.get("21").toString()).setGridCode(paramsMap.get("gridCode").toString()).setGridName(paramsMap.get("gridName").toString()).setOrgCode(paramsMap.get("orgCode").toString())
                        .setAccountName(paramsMap.get("accountName").toString()).setCreatedAt(now).setUpdatedAt(now).setOrgName(paramsMap.get("orgName").toString()).setCustomerId(customer.getCustomerId().toString());
                //进行参数校验
               // checkParams(customerCreditDetail);
                //进行逻辑校验
                checkCustomerCreditDetailLogic(customerCreditDetail);
            } catch (Exception e) {
            	failCount++;
            	continue;
            }
          //查询客户的配偶信息，有的话则插入
            List<FamilyMember> familyList=familyMemberMapper.listByCustomerId(Long.parseLong(customerCreditDetail.getCustomerId()));
            if(list.size()>0) {
            	for(FamilyMember fm:familyList) {
            		if("配偶".equals(fm.getRelationship())) {
            			customerCreditDetail.setSpouseIdNumber(fm.getIdNumber());
            			customerCreditDetail.setSpouseName(fm.getMemberName());
            			customerCreditDetail.setMemberCount(familyList.size()+1+"");
            			break;
            		}
            	}
            }
            //插入之前先把之前同一身份证下的临时数据删除
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("idNumber", customerCreditDetail.getIdNumber());
            customerCreditDetailMapper.deleteByIdNumber(paramMap);
            customerCreditDetailMapper.insertSelective(customerCreditDetail);
            TagCustomer tagCustomer=new TagCustomer();
            tagCustomer.setTagId((long)2);
            tagCustomer.setIdNumber(customerCreditDetail.getIdNumber());
            tagCustomer.setTagName("授信客户");
            tagCustomer.setCreatedAt(now);
            tagCustomer.setUpdatedAt(now);
            tagCustomerMapper.insertSelective(tagCustomer);
            successCount++;

        }

        Map<String,Object> map=new HashMap<>();
		map.put("failCount", failCount);
		map.put("successCount", successCount);
		return map;
    }
    /**
     * 删除某条面谈面签信息记录
     *
     * */

    @Override
    public boolean deleteByPrimaryKey(Long id) throws Exception {

        if(id==null) {
            throw new MyException("参数有误");
        }

        CustomerCreditDetail record=customerCreditDetailMapper.selectByPrimaryKey(id);
        if(!"0".equals(record.getApprovalStatus())) {
            throw new MyException("该记录已存在审查记录不允许删除");
        }
        try {	record.setStatus("1");
            //将标签关联删除
            TagCustomer tagCustomer=new TagCustomer();
            tagCustomer.setIdNumber(record.getIdNumber());
            tagCustomer.setTagId((long)2);
            tagCustomerMapper.deleteTagByIdNumberAndTagId(tagCustomer);
            return customerCreditDetailMapper.updateByPrimaryKeySelective(record)==1;
        } catch (Exception e) {
            logger.info("删除面谈面签异常：" + e.getMessage());
            throw new MyException("删除面谈面签异常");
        }
    }

    /**
     *新增面谈面签信息记录
     *
     * */

    @Override
    public boolean insertSelective(CustomerCreditDetail record) throws Exception {
    	record.setId(null);
        //checkParams(record);
        checkCustomerCreditDetailLogic(record);
        long now=System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        record.setStatus("2");
        //新增完的数据先检查下客户模块是否已经有相关标签
       
        TagCustomer tagCustomer=new TagCustomer();
        tagCustomer.setTagId((long)2);
        tagCustomer.setIdNumber(record.getIdNumber());
        List<TagCustomer> tagList=tagCustomerMapper.getListByTagIdAndIdNumber(tagCustomer);
        if(tagList.isEmpty()) {
        	tagCustomer.setTagName("授信客户");
            tagCustomer.setCreatedAt(now);
            tagCustomer.setUpdatedAt(now);
            tagCustomerMapper.insertSelective(tagCustomer);
        }
        //插入之前先把之前同一身份证下的临时数据删除
        Map<String,Object> map=new HashMap<>();
    	map.put("idNumber", record.getIdNumber());
        customerCreditDetailMapper.deleteByIdNumber(map);
        
        //查询客户的配偶信息，有的话则插入
        List<FamilyMember> list=familyMemberMapper.listByCustomerId(Long.parseLong(record.getCustomerId()));
        if(list.size()>0) {
        	for(FamilyMember fm:list) {
        		if("配偶".equals(fm.getRelationship())) {
        			record.setSpouseIdNumber(fm.getIdNumber());
        			record.setSpouseName(fm.getMemberName());
        			record.setMemberCount(list.size()+1+"");
        			break;
        		}
        	}
        }
        try {
            return customerCreditDetailMapper.insertSelective(record)==1;
        } catch (Exception e) {
            logger.info("新建面谈面签异常：" + e.getMessage());
            throw new MyException(e.getMessage());
        }
    }
    /**
     * 查询某条面谈面签记录信息
     *
     * */
    @Override
    public CustomerCreditDetail selectByPrimaryKey(Long id) throws Exception {
        try {
            if(id==null) {
                throw new MyException("参数错误");
            }
            return customerCreditDetailMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.info("查询谈面签异常：" + e.getMessage());
            throw new MyException("查询面谈面签信息异常");
        }
    }

    /**
     *修改面谈面签信息记录
     *
     * */
    @Override
    public boolean updateByPrimaryKeySelective(CustomerCreditDetail record) throws Exception {
        try {
            CustomerCreditDetail customerCreditDetail=customerCreditDetailMapper.selectByPrimaryKey(record.getId());
            if(customerCreditDetail==null || customerCreditDetail.getId()==null) {
                throw new MyException("面谈面签库中不存在此客户信息");
            }
            if("1".equals(customerCreditDetail.getApprovalStatus()) ) {
                throw new MyException("该面谈面签客户信息正在审查中不允许修改");
            }
            if("3".equals(customerCreditDetail.getApprovalStatus()) ) {
                throw new MyException("该面谈面签客户信息已通过审查不允许修改");
            }
            checkParams(record);
            record.setUpdatedAt(System.currentTimeMillis());
            record.setStatus("0");
            return customerCreditDetailMapper.updateByPrimaryKeySelective(record)==1;
        } catch (Exception e) {
            logger.info("修改面谈面签异常：" + e.getMessage());
            throw new MyException( e.getMessage());
        }
    }

    /**
     * 查询面谈面签列表
     * **/
    @Override
    public List<CustomerCreditDetail> getListByPage(Map<String, Object> map) throws Exception {
        if(!map.containsKey("roleId") || map.get("roleId")==null) {
            throw new MyException("查询参数异常");
        }

        if(!map.containsKey("accountId") || map.get("accountId")==null) {
            throw new MyException("查询参数异常");
        }
        if(!map.containsKey("approvalStatus") || map.get("approvalStatus")==null) {
            throw new MyException("查询参数异常");
        }
        if(!map.containsKey("orgCode")|| map.get("orgCode")==null) {
            throw new MyException("查询参数异常");
        }
        if(!map.containsKey("attachFlag")|| map.get("attachFlag")==null) {
            throw new MyException("查询参数异常");
        }
        if(!map.containsKey("pageNum")) {
            throw new MyException("查询参数异常");
        }
        if(!map.containsKey("pageSize")) {
            throw new MyException("查询参数异常");
        }
        //如果gridCode参数存在 则查当前网格  不存在  则查所有网格
        List<String> gridCodesList=new ArrayList<>();
        if(map.containsKey("gridCode")) {
            gridCodesList.add(map.get("gridCode").toString());
        }else {
            gridCodesList=findGridCodes(map.get("orgCode").toString(),map.get("roleId").toString(), map.get("accountId").toString());
        }

        if (gridCodesList.isEmpty()) {
            return new ArrayList<>();
        }
        map.put("gridCodes", gridCodesList);

        List<String> approvalStatusList=new ArrayList<>();

        //5为查询未面谈面前的全部状态
        if("5".equals(map.get("approvalStatus").toString())) {
            if("1".equals(map.get("roleId").toString())) {
                approvalStatusList.add("0");
                approvalStatusList.add("4");
               // approvalStatusList.add("1");
            }
            if("2".equals(map.get("roleId").toString())) {
                approvalStatusList.add("1");
                approvalStatusList.add("4");
            }

        }else {
            //其余的传什么状态就查什么状态
            approvalStatusList.add(map.get("approvalStatus").toString());
        }

        map.put("approvalStatusList", approvalStatusList);
        try {
            PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
            return	customerCreditDetailMapper.getList(map);
        }catch(Exception e) {
            logger.info("查询授信列表异常：" + e.getMessage());
            throw new MyException("查询参数异常");
        }

    }

    /**
     * 查询出登陆人能看到的所有网格信息
     * @param orgCode
     * @param roleId
     * @param accountId
     * @return
     */
    public List<String> findGridCodes(String orgCode,String roleId,String accountId){
        GridInfo gridInfo=new GridInfo();
        //如果是客户经理，用管理员查
        if("1".equals(roleId)) {
            gridInfo.setAccountId(Long.parseLong(accountId));
        }
        //如果是会计，用机构号查
        if("2".equals(roleId)) {
            gridInfo.setOrgCode(Long.parseLong(orgCode));
        }
        //gridInfo.setOrgCode(Long.parseLong(orgCode));
        return gridInfoMapper.getGridCodesByAccountIdOrOrgCode(gridInfo);

    }

    /**
     * 验证参数是否为空
     * @param record
     * @return
     * @throws Exception
     */
    public static  boolean checkParams(CustomerCreditDetail record) throws Exception{
        if(ValidUtil.isEmpty(record.getIdNumber())) {
            throw new MyException("客户身份证号不能为空");
        }
        if(ValidUtil.isEmpty(record.getCustomerName())) {
            throw new MyException("客户姓名不能为空");
        }
        if(ValidUtil.isEmpty(record.getCustomerId())) {
            throw new MyException("客户编号不能为空");
        }
        if(ValidUtil.isEmpty(record.getPhoneNumber())) {
            throw new MyException("客户手机号码不能为空");
        }
        /*if(ValidUtil.isEmpty(record.getJnydId())) {
            throw new MyException("客户金农易贷账号不能为空");
        }
        if(ValidUtil.isEmpty(record.getJnydName())) {
            throw new MyException("客户金农易贷户名不能为空");
        }*/
        if(ValidUtil.isEmpty(record.getRental())) {
            throw new MyException("客户授信金额不能为空");
        }
        if(ValidUtil.isEmpty(record.getDeadline())) {
            throw new MyException("授信期限不能为空");
        }
        if(ValidUtil.isEmpty(record.getRateType())) {
            throw new MyException("贷款利率类型不能为空");
        }
        if(ValidUtil.isEmpty(record.getDisbursement())) {
            throw new MyException("贷款发放方式不能为空");
        }
        if(ValidUtil.isEmpty(record.getRefundType())) {
            throw new MyException("还款方式不能为空");
        }
        if(ValidUtil.isEmpty(record.getCreditUseType())) {
            throw new MyException("借款用途不能为空");
        }
        if(ValidUtil.isEmpty(record.getIncome())) {
            throw new MyException("个人年收入不能为空");
        }
        if(ValidUtil.isEmpty(record.getFamilyIncome())) {
            throw new MyException("家庭年收入不能为空");
        }

        if(ValidUtil.isEmpty(record.getTalkDate())) {
            throw new MyException("面谈面签日期不能为空");
        }

        if(ValidUtil.isEmpty(record.getGridCode())) {
            throw new MyException("客户所属网格编号不能为空");
        }
        if(ValidUtil.isEmpty(record.getGridName())) {
            throw new MyException("客户所属网格名称不能为空");
        }
        if(ValidUtil.isEmpty(record.getAccountId())) {
            throw new MyException("登记人编号不能为空");
        }
        if(ValidUtil.isEmpty(record.getAccountName())) {
            throw new MyException("登记人姓名不能为空");
        }
        if(ValidUtil.isEmpty(record.getBeginAt())) {
            throw new MyException("授信起始日期不能为空");
        }
        if(ValidUtil.isEmpty(record.getEndAt())) {
            throw new MyException("授信结束日期不能为空");
        }
        return true;
    }


    /**
     * 验证新增的面谈面签资料是否符合业务逻辑
     * @param record
     * @return
     * @throws Exception
     */
    public  boolean checkCustomerCreditDetailLogic(CustomerCreditDetail record) throws Exception{
        CustomerInfo customerInfo=customerInfoMapper.getCustomerByIdNumber(record.getIdNumber());
        //检查客户信息是否可用
        if((customerInfo==null) || (ValidUtil.isEmpty(customerInfo.getIdNumber()))||(customerInfo.getStatus()!=5)) {
            throw new MyException("客户库中没有检索到可用的客户信息");
        }
        //验证信息是否已存在于白名单中
        CustomerWhitelist customerWhitelist=new CustomerWhitelist();
        customerWhitelist.setIdNumber(record.getIdNumber());
       if( customerWhitelistMapper.getByIdOrIdnumber(customerWhitelist).isEmpty()) {
    	   throw new MyException("白名单库中未匹配到相关信息");
       }
        
        //验证客户勾选的网格和客户库中的网格是否一致

        if(!customerInfo.getGridCode().equals(record.getGridCode())) {
            throw new MyException("您勾选的网格和客户库中客户所属的网格信息不匹配");
        }
        //验证客户信息是否在黑名单
        CustomerBlackList customerBlackList=new CustomerBlackList();
        customerBlackList.setIdNumber(record.getIdNumber());
        if(customerBlackListMapper.getByIdOrIdnumber(customerBlackList).size()>0) {
            throw new MyException("该客户已存在黑名单记录");
        }
        //验证客户信息是否在灰名单
        CustomerGreylist customerGreylist=new CustomerGreylist();
        customerGreylist.setIdNumber(record.getIdNumber());
        if(customerGreylistMapper.getByIdOrIdnumber(customerGreylist).size()>0) {
            throw new MyException("该客户已存在灰名单记录");
        }
		/*//验证提交的客户信息是否存在还未授信截至的记录
		Map<String,Object> map =new HashMap<>();
		map.put("idNumber",record.getIdNumber());
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        map.put("endAt",dateFormat.format( now ));
		List<CustomerCreditDetail> list=customerCreditDetailMapper.getByIdNumber(map);
		if(list.size()>0) {
			throw new MyException("面谈面签库中已存在授信还未到期的客户资料");
		}*/
        return true;
    }

    /**
     * 根据creditDetailId查询征信报告
     * @param creditDetailId
     * @return
     * @throws Exception
     */
    public String getCreditReportHtmlCode(long creditDetailId) throws Exception {
        CustomerCreditDetail customerCreditDetail=customerCreditDetailMapper.selectByPrimaryKey(creditDetailId);
        if(customerCreditDetail !=null &&customerCreditDetail.getCreditReport()!=null && !"".equals(customerCreditDetail.getCreditReport())) {
            File file=new File(customerCreditDetail.getCreditReport());
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file)); // 建立一个输入流对象reader
            BufferedReader bufferedReader = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            String code = "";
            try {
                line = bufferedReader.readLine();
                while (line != null) {
                    line =bufferedReader.readLine(); // 一次读入一行数据
                    code=code+line;
                }
                reader.close();
                bufferedReader.close();
            }catch (Exception e) {
                return "读取文件异常";
            }finally {
                if(reader !=null) {
                    reader.close();
                }
                if(bufferedReader !=null) {
                    bufferedReader.close();
                }
            }
            return code;
        }
        return "未查询到此客户的征信报告详情";
    }
    /**
     * 
     * **/
	@Override
	public Map<String, Object> batchSave(List<CustomerCreditDetail> list,Map<String,Object> parapMap) throws Exception {
		Map<String, Object> returMap =new HashMap<>();
		int success=0;
	   List<String> successList=new ArrayList<>();
	   List<String> faileList=new ArrayList<>();
		int faile=0;
		
		if(list.isEmpty()) {
			throw new MyException("请勾选有效数据");
		}
		long now=System.currentTimeMillis();
		Map<String,Object> map=new HashMap<>();
		map.put("gridCode", list.get(0).getGridCode());
		List<Map<String,Object>> gridList=gridInfoMapper.getGridInfoList(map);
		for(CustomerCreditDetail customerCreditDetail:list){
			if(customerCreditDetail.getIdNumber()==null ||"".equals(customerCreditDetail.getIdNumber())) {
				faile++;
				faileList.add(customerCreditDetail.getCustomerName());
				continue;
			}
			try {
				
				CustomerInfo customerInfo =customerInfoMapper.getCustomerByIdNumber(customerCreditDetail.getIdNumber());
				 customerCreditDetail.setAccountId(parapMap.get("accountId").toString()).setSex(customerInfo.getSex()).setEducationLevel(customerInfo.getEducationLevel())
				 .setPhoneNumber(customerInfo.getPhoneNumber()).setCompanyName(customerInfo.getCompanyName()).setCompanyAddress(customerInfo.getCompanyAddress())
				 .setNativeAddress(customerInfo.getNativeAddress()).setResidenceAddress(customerInfo.getResidenceAddress()).
				setGridName(gridList.get(0).get("gridName").toString()).setOrgCode(parapMap.get("orgCode").toString())
				 .setAccountName(parapMap.get("accountName").toString()).setCreatedAt(now).setUpdatedAt(now).setOrgName(parapMap.get("orgName").toString()).setCustomerId(customerInfo.getCustomerId().toString());
				 //进行逻辑校验
				 checkCustomerCreditDetailLogic(customerCreditDetail);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				faile++;
				faileList.add(customerCreditDetail.getCustomerName());
				continue;
			}
			 //查询客户的配偶信息，有的话则插入
            List<FamilyMember> familyList=familyMemberMapper.listByCustomerId(Long.parseLong(customerCreditDetail.getCustomerId()));
            if(list.size()>0) {
            	for(FamilyMember fm:familyList) {
            		if("配偶".equals(fm.getRelationship())) {
            			customerCreditDetail.setSpouseIdNumber(fm.getIdNumber());
            			customerCreditDetail.setSpouseName(fm.getMemberName());
            			customerCreditDetail.setMemberCount(familyList.size()+1+"");
            			break;
            		}
            	}
            }
            //插入之前先把之前同一身份证下的临时数据删除
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("idNumber", customerCreditDetail.getIdNumber());
            customerCreditDetailMapper.deleteByIdNumber(paramMap);
            customerCreditDetailMapper.insertSelective(customerCreditDetail);
            TagCustomer tagCustomer=new TagCustomer();
            tagCustomer.setTagId((long)2);
            tagCustomer.setIdNumber(customerCreditDetail.getIdNumber());
            List<TagCustomer> tagList=tagCustomerMapper.getListByTagIdAndIdNumber(tagCustomer);
            if(tagList.isEmpty()) {
            	tagCustomer.setTagName("授信客户");
                tagCustomer.setCreatedAt(now);
                tagCustomer.setUpdatedAt(now);
                tagCustomerMapper.insertSelective(tagCustomer);
            }
            successList.add(customerCreditDetail.getCustomerName());
            success++;
		}
		
		returMap.put("success", success);
		returMap.put("faile", faile);
		returMap.put("successList", successList);
		returMap.put("faileList", faileList);
		return returMap;
	}

}
