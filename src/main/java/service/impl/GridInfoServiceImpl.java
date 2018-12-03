package service.impl;

import com.github.pagehelper.PageHelper;
import dao.CustomerInfoMapper;
import dao.GridInfoMapper;
import dao.GridReviewMapper;
import exception.MyException;
import model.CustomerInfo;
import model.GridInfo;
import model.GridReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import service.GridInfoService;
import util.Base64Util;
import util.PoiUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class GridInfoServiceImpl implements GridInfoService {
    @Autowired
    private GridInfoMapper gridInfoMapper;
    @Autowired
    private GridReviewMapper gridReviewMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 调用此方法删除网格信息
     * @param id
     * @return  boolean
     * @Exception
     * */
    @Override
    public boolean deleteByPrimaryKey(Long id) throws Exception {
        if(id==null ) {
            throw new MyException("参数有误");
        }
      
            GridInfo gridInfo=gridInfoMapper.selectByPrimaryKey(id);
            //应该加入看看客户信息有没有关联这个网格
            gridInfo.setDeleteFlag("1");
            CustomerInfo customerInfo=new CustomerInfo();
            customerInfo.setGridCode(gridInfo.getGridCode());
            if(customerInfoMapper.listCustomers(customerInfo).size()>0) {
                throw new MyException("该网格已经关联了客户信息，请先解除关联再删除");
            }
       try {
            gridInfoMapper.updateByPrimaryKeySelective(gridInfo);
            Map<String,Object> map=new HashMap<>();
            map.put("gridCode", gridInfo.getGridCode());
            gridReviewMapper.deleteByGridCode(map);
        }catch(Exception e) {
            logger.info("删除网格异常：" + e.getMessage());
            throw new MyException("删除网格出现异常");
        }
        return true;
    }

    /**
     * 调用此方法插入网格信息
     * @param record
     * @return  boolean
     * @Exception
     *
     * */

    @Override
    public boolean insertSelective(GridInfo record) throws Exception {
        if(record.getGridCode()==null || "".equals(record.getGridCode())) {
            throw new MyException("网格编号不能为空");
        }
        if(record.getGridName()==null || "".equals(record.getGridName())) {
            throw new MyException("网格名称不能为空");
        }
        if(record.getOrgCode()==null ) {
            throw new MyException("机构编号不能为空");
        }
        if(record.getGridType()==null ) {
            throw new MyException("网格类型不能为空");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("gridCode", record.getGridCode());

        if((gridInfoMapper.checkSameGridCode(map)).size()>0) {
            throw new MyException("系统中已存在相同的网格编号");
        }
        long now =System.currentTimeMillis();
        record.setCreatedAt(now);
        try {
            gridInfoMapper.insertSelective(record);
            //将系统中原来的评议小组成员的信息设为删除状态
            GridReview gridReview=new GridReview();
            gridReview.setGridCode(record.getGridCode());
            gridReview.setUpdatedAt(now);
            gridReviewMapper.updataByGridCode(gridReview);
            if(record.getListReview().size()>0) {
                //再重新插入关联表的数据
                for(int i=0;i<record.getListReview().size();i++) {
                    record.getListReview().get(i).setId(null);
                    record.getListReview().get(i).setCreatedAt(now);
                    record.getListReview().get(i).setUpdatedAt(now);
                    gridReviewMapper.insertSelective(record.getListReview().get(i));
                }
            }
        }catch(Exception e) {
            logger.info("新增网格信息异常：" + e.getMessage());
            throw new MyException("新增网格信息出现异常");
        }
        return true;
    }

    /**
     * 调用此方法修改网格信息
     * @param record
     * @return  boolean
     * @Exception
     *
     * */
    public boolean updateByPrimaryKeySelective(GridInfo record) throws Exception {
        if(record.getGridCode()==null || "".equals(record.getGridCode())) {
            throw new MyException("网格编号不能为空");
        }
        if(record.getGridName()==null || "".equals(record.getGridName())) {
            throw new MyException("网格名称不能为空");
        }
        if(record.getOrgCode()==null ) {
            throw new MyException("机构编号不能为空");
        }
        if(record.getGridType()==null ) {
            throw new MyException("网格类型不能为空");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("gridCode", record.getGridCode());
        map.put("id", record.getId());
        //查询出修改前的gridCode号
        GridInfo gridInfo=gridInfoMapper.selectByPrimaryKey(record.getId());

        if((gridInfoMapper.checkSameGridCode(map)).size()>0) {
            throw new MyException("系统中已存在相同的网格编号");
        }
        long now =System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            gridInfoMapper.updateByPrimaryKey(record);
            if(record.getListReview().size()>0) {
                //将系统中原来的评议小组成员的信息设为删除状态
                GridReview gridReview=new GridReview();
                gridReview.setGridCode(gridInfo.getGridCode());
                gridReview.setUpdatedAt(now);
                gridReviewMapper.updataByGridCode(gridReview);

                //再重新插入关联表的数据
                for(int i=0;i<record.getListReview().size();i++) {
                    record.getListReview().get(i).setId(null);
                    record.getListReview().get(i).setCreatedAt(now);
                    record.getListReview().get(i).setUpdatedAt(now);
                    gridReviewMapper.insertSelective(record.getListReview().get(i));
                }
            }
        }catch(Exception e) {
            logger.info("修改网格信息异常：" + e.getMessage());
            throw new MyException("修改网格信息出现异常");
        }
        return true;
    }

    @Override
    public GridInfo getGridInfoByGridCode(Map<String, Object> map) throws Exception {

        return null;
    }


    /**
     * 调用此方法查询某个网格信息
     * @param id
     * @return  GridInfo
     * @Exception
     *
     * */
    @Override
    public GridInfo selectByPrimaryKey(Long id) throws Exception {
        if(id==null) {
            throw new MyException("查询参数异常");
        }
        GridInfo gridInfo = null;
        try {
            gridInfo=gridInfoMapper.selectByPrimaryKey(id);
        }catch(Exception e) {
            logger.info("查询网格异常:" + e.getMessage());
            throw new MyException("查询网格出错");
        }

        return gridInfo;
    }

    /**
     * 调用此方法查询网格信息列表
     * @param map
     * @return  List<Map<String, Object>>
     * @Exception
     * */
    @Override
    public List<Map<String, Object>> getGridInfoList(Map<String, Object> map) throws Exception {
        if(!map.containsKey("pageNum")) {
            throw new MyException("查询参数异常");
        }
        if(!map.containsKey("pageSize")) {
            throw new MyException("查询参数异常");
        }
        try {
            PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
            return gridInfoMapper.getGridInfoList(map);
        }catch(Exception e) {
            logger.info("查询网格列表异常：" + e.getMessage());
            throw new MyException("查询网格列表出现异常");
        }



    }

    /**
     * 调用此方法查询登陆人能看到的所有网格信息
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public List<GridInfo> getGridInfoListByAccount(Map<String, Object> map) throws Exception {
        if(!map.containsKey("roleId")||!map.containsKey("accountId") || !map.containsKey("orgCode")) {
            throw new MyException("查询参数异常");
        }
        try {
            GridInfo gridInfo=new GridInfo();
            //如果是客户经理，用管理员查
            if("1".equals(map.get("roleId").toString())) {
                gridInfo.setAccountId(Long.parseLong(map.get("accountId").toString()));
            }else if(!"1".equals(map.get("roleId").toString()) && !"7".equals(map.get("roleId").toString())) {
                gridInfo.setOrgCode(Long.parseLong(map.get("orgCode").toString()));

            }
            //gridInfo.setOrgCode(Long.parseLong(orgCode));
            return gridInfoMapper.getGridinfosByAccountIdOrOrgCode(gridInfo);

        }	catch (Exception e) {
            logger.info("根据登录人查询网格异常：" + e.getMessage());
            throw new MyException("查询数据异常");
        }

    }
    /****
     * 
     * 调用此接口来保存网格地图的图片
     * 
     * **/
	@Override
	public boolean saveGridImage(MultipartFile file, long id) throws Exception {
		GridInfo gridInfo=gridInfoMapper.selectByPrimaryKey(id);
		if(gridInfo==null) {
			throw new MyException("未查询到相关的网格信息");
		}
		if(PoiUtil.isImage(file)==false) {
			throw new MyException("您上传的不是图片");
		}
		    String path="";
	        File tempFile = null;
	        String saveFile="";
	        if(PoiUtil.isOSLinux()) {
	            path="/data/attach/grid/image";
	            tempFile =new File(path);
	            if(!tempFile.exists()) {
	                tempFile.setWritable(true, false);    //设置写权限，windows下不用此语句
	                tempFile.mkdirs();
	            }
	            saveFile=path+"/"+gridInfo.getGridName()+System.currentTimeMillis()+file.getOriginalFilename();
	        }else {
	            path="D:\\gridFileUpload\\attach\\grid\\image";
	            tempFile =new File(path);
	            if(!tempFile.exists()) {
	                tempFile.mkdirs();
	            }
	            saveFile=path+"\\"+gridInfo.getGridName()+System.currentTimeMillis()+file.getOriginalFilename();
	        }
	        try {
				File storeFile = new File(saveFile);
				file.transferTo(storeFile);
	        gridInfo.setGridMap(saveFile);
	        gridInfo.setUpdatedAt(System.currentTimeMillis()); 
			return  gridInfoMapper.updateByPrimaryKeySelective(gridInfo)==1;
		} catch (Exception e) {
			throw new MyException("保存图片出现异常");
			
		}	
	}
	/****
     * 
     * 调用此接口来查询网格地图的图片
     * 
     * **/
	@Override
	public String getGridMapImage(long id) throws Exception {
		GridInfo gridInfo=gridInfoMapper.selectByPrimaryKey(id);
		if(gridInfo==null) {
			throw new MyException("未查询到相关的网格信息");
		}
		File file=new File(gridInfo.getGridMap());
		if(file.exists()==false) {
			throw new MyException("文件未找到"); 
		}
		String baseCode="data:image/jpg;base64,";
		baseCode=baseCode+ Base64Util.encodeBase64(file);
		return baseCode;
	}

}
