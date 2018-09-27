package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.GridInfoMapper;
import dao.GridReviewMapper;
import exception.MyException;
import model.GridInfo;
import service.GridInfoService;
@Service 
public class GridInfoServiceImpl implements GridInfoService {
	@Autowired
	private GridInfoMapper gridInfoMapper;
	@Autowired
	private GridReviewMapper gridReviewMapper;
	
	
	
	/**
	 * 调用此方法删除网格信息
	 * @param id
	 * @return  boolean
	 * @Exception 
	 * 
	 * */

	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		if(id==null ) {
			throw new MyException("参数有误");
		}
		try {
			
			GridInfo gridInfo=gridInfoMapper.selectByPrimaryKey(id);
			gridInfoMapper.deleteByPrimaryKey(id);
			Map<String,Object> map=new HashMap<>();
			map.put("gridCode", gridInfo.getGridCode());
			gridReviewMapper.deleteByGridCode(map);
		}catch(Exception e) {
			throw new MyException("删除网格信息异常");
		}
	
		
		return true;
	}
	
	/**
	 * 调用此方法插入网格信息
	 * @param GridInfo
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
		record.setCreatedAt(System.currentTimeMillis());
		try {
			gridInfoMapper.insertSelective(record);
			if(record.getListReview().size()>0) {
				
				
				//再重新插入关联表的数据
				for(int i=0;i<record.getListReview().size();i++) {
					record.getListReview().get(i).setCreatedAt(System.currentTimeMillis());
					gridReviewMapper.insertSelective(record.getListReview().get(i));
				}
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("新增网格信息异常");
		}
		
		
		
		return true;
	}

	/**
	 * 调用此方法修改网格信息
	 * @param GridInfo
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
		record.setUpdatedAt(System.currentTimeMillis());
		try {
			gridInfoMapper.updateByPrimaryKey(record);
			if(record.getListReview().size()>0) {
				//先删除评议员关联表中的数据
				Map<String,Object> mapInfo=new HashMap<>();
				mapInfo.put("gridCode", gridInfo.getGridCode());
				gridReviewMapper.deleteByGridCode(mapInfo);
				
				//再重新插入关联表的数据
				for(int i=0;i<record.getListReview().size();i++) {
					record.getListReview().get(i).setCreatedAt(System.currentTimeMillis());
					gridReviewMapper.insertSelective(record.getListReview().get(i));
				}
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("修改网格信息异常");
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
			throw new MyException("查询网格出错");
		}
		
		return gridInfo;
	}

	/**
	 * 调用此方法查询网格信息列表
	 * @param map
	 * @return  List<Map<String, Object>>
	 * @Exception 
	 * 
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
			throw new MyException("查询参数异常");
		}
		
	
		
	}

}
