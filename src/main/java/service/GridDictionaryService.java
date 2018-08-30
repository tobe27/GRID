package service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import model.GridDictionary;

public interface GridDictionaryService {
	 boolean deleteByPrimaryKey(Long id) throws Exception;

	    boolean insert(GridDictionary record) throws Exception;

	    boolean insertSelective(GridDictionary record) throws Exception;

	    GridDictionary selectByPrimaryKey(Long id) throws Exception;

	    boolean updateByPrimaryKeySelective(GridDictionary record) throws Exception;

	    boolean updateByPrimaryKey(GridDictionary record) throws Exception;
	    boolean deleteByCode(String  code) throws Exception;
	    boolean addGridDictionaryList(List<GridDictionary> list) throws Exception;
	    boolean updateGridDictionaryList(List<GridDictionary> list) throws Exception;
	   PageInfo<GridDictionary> getGridDictionaryListByPage(int pageNo,int pageSize,String name)throws Exception;
	    List<GridDictionary> selectByCode(String code) throws Exception;

}
