package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import dao.GridDictionaryMapper;
import exception.MyException;
import model.GridDictionary;
import model.GridPermission;
import service.GridDictionaryService;
@Service
public class GridDictionaryServiceImpl implements GridDictionaryService {
	@Autowired
	private GridDictionaryMapper  gridDictionaryMapper;
 
	
	
	/**
     * 根据字典在数据库的自增序号删除
     *
     * @param id
     * @return
     * @throws Exception
     */
	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		try {
			return gridDictionaryMapper.deleteByPrimaryKey(id)==1;
			}catch(Exception e) {
				  throw new MyException("删除字典异常");
			}
		
	}
	
	
	/**
     * 新增字典（全参数）
     *
     * @param GridDictionary
     * @return
     * @throws Exception
     */

	@Override
	public boolean insert(model.GridDictionary record) throws Exception {
		try {
			return gridDictionaryMapper.insert(record)==1;
			}catch(Exception e) {
				  throw new MyException("新增字典异常");
			}
		
	}
     
	
	/**
     * 新增字典（参数可为空）
     *
     * @param GridDictionary
     * @return
     * @throws Exception
     */

	@Override
	public boolean insertSelective(model.GridDictionary record) throws Exception {
		
		try {
			return gridDictionaryMapper.insertSelective(record)==1;
			}catch(Exception e) {
				  throw new MyException("新增字典异常");
			}
	}
	/**
     * 根据自增id查询字典
     *
     * @param GridDictionary
     * @return GridDictionary
     * @throws Exception
     */

	@Override
	public model.GridDictionary selectByPrimaryKey(Long id) throws Exception {
		try {
			return gridDictionaryMapper.selectByPrimaryKey(id);
			}catch(Exception e) {
				  throw new MyException("查询字典异常");
			}
		
	}
   
	
	
	
	/**
     * 修改字典（全参数）
     *
     * @param GridDictionary
     * @return 
     * @throws Exception
     */
	@Override
	public boolean updateByPrimaryKeySelective(model.GridDictionary record) throws Exception {
	
		try {
			return gridDictionaryMapper.updateByPrimaryKeySelective(record)==1;
			}catch(Exception e) {
				  throw new MyException("修改字典异常");
			}
	}

	
	/**
     * 修改字典（参数可为空）
     *
     * @param GridDictionary
     * @return 
     * @throws Exception
     */
	
	@Override
	public boolean updateByPrimaryKey(model.GridDictionary record) throws Exception {
		
		try {
			return gridDictionaryMapper.updateByPrimaryKey(record)==1;
			}catch(Exception e) {
				  throw new MyException("修改字典异常");
			}
	}

	
	
	/**
     * 根据字典组code 删除字典组
     *
     * @param code
     * @return 
     * @throws Exception
     */
	@Override
	public boolean deleteByCode(String code) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("code", code);
		try {
			return gridDictionaryMapper.deleteByCode(map)==1;
			}catch(Exception e) {
				  throw new MyException("删除字典异常");
			}
	}

	
	
	
	/**
     * 新增字典组
     *
     * @param List<GridDictionary>
     * @return 
     * @throws Exception
     */
	@Override
	public boolean addGridDictionaryList(List<GridDictionary> list) throws Exception {
		
		//判断系统中有没有相同name的
		Map<String,Object> map=new HashMap<>();
		map.put("name", list.get(0).getName());
		List<GridDictionary> grList=gridDictionaryMapper.getListByName(map);
		if(grList!=null && grList.size()>0) {
			throw new MyException("系统中已存在相同字典组名称");
		}
		try {
		int i=0;
		long now=System.currentTimeMillis();
		String code="Grid"+System.currentTimeMillis();
		for(GridDictionary gr:list){
			gr.setCode(code);
			gr.setCreatAt(now);
			gridDictionaryMapper.insertSelective(gr);
			i++;
		}
		
		
		return i==list.size();
		}catch(Exception e) {
			throw new MyException("新增字典异常");
		}
	}
	
	
	/**
     * 修改字典组
     *
     * @param List<GridDictionary>
     * @return 
     * @throws Exception
     */
	
 public boolean updateGridDictionaryList(List<GridDictionary> list) throws Exception {
		
		//判断系统中有没有相同name的
		Map<String,Object> map=new HashMap<>();
		map.put("name", list.get(0).getName());
		map.put("code",list.get(0).getCode());
		List<GridDictionary> grList=gridDictionaryMapper.getListByName(map);
		if(grList!=null && grList.size()>0) {
			throw new MyException("系统中已存在相同字典组名称");
		}else {
			
			gridDictionaryMapper.deleteByCode(map);
		}
		try {
		int i=0;
		for(GridDictionary gr:list){
			gr.setUpdateAt(System.currentTimeMillis());
			gridDictionaryMapper.insertSelective(gr);
			i++;
		}
		
		
		return i==list.size();
		}catch(Exception e) {
			throw new MyException("修改字典异常");
		}
	}

 
 
 /**
  * 分页和条件查询字典组
  *
  * @param pageNo,pageSize,name
  * @return 
  * @throws Exception
  */
@Override
public PageInfo<GridDictionary> getGridDictionaryListByPage(int pageNo, int pageSize, String name) throws Exception {
	Map<String,Object> map=new HashMap<>();
	PageHelper.startPage(pageNo, pageSize);
	
	map.put("name", name);
	List<GridDictionary> list=null;
	try {
		list = gridDictionaryMapper.getList(map);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	PageInfo<GridDictionary> pageInfo=new PageInfo<GridDictionary>(list);
	
	return pageInfo;
	
	
}


/**
 * 根据code获取字典组
 *
 * @param  code
 * @return  List<GridDictionary> 
 * @throws Exception
 */
@Override
public List<GridDictionary> selectByCode(String code) throws Exception {
	
	Map<String, Object> map=new HashMap<>();
	map.put("code", code);
	List<GridDictionary> list=gridDictionaryMapper.getListByCode(map);
	
	
	return list;
}

}
