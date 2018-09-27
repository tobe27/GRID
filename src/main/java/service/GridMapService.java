package service;

import java.util.List;
import java.util.Map;



import model.GridMap;


public interface GridMapService {
	
	 boolean  batchSave(List<GridMap> list) throws Exception;
     List<GridMap> getGridMapList(Map<String,Object> map) throws Exception;
     boolean insertSelective(GridMap record) throws Exception;

}
