package util;

import model.Region;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by L.C.Y on 2018-10-11
 */
public class TreeUtil {
    public List<Region> parentList;
    public List<Object> list = new ArrayList<>();

    public List<Object> getRegionList(List<Region> parentList, Long parentCode) {
        this.parentList = parentList;
        for (Region x : parentList) {
            Map<String, Object> parentMap = new LinkedHashMap<>();
            if (x.getRegionCode().equals(parentCode)) {
                parentMap.put("regionCode", x.getRegionCode());
                parentMap.put("regionName", x.getRegionName());
                parentMap.put("preRegionCode", x.getPreRegionCode());
                parentMap.put("regionLevel", x.getRegionLevel());
                parentMap.put("description", x.getDescription());
                parentMap.put("childList", getChildList(x.getRegionCode()));
                list.add(parentMap);
            }
        }
        return list;
    }

    public List<Object> getChildList(Long regionCode) {
        List<Object> childList = new ArrayList<>();
        for (Region x : parentList) {
            Map<String, Object> childMap = new LinkedHashMap<>();
            if (x.getPreRegionCode().equals(regionCode)) {
                childMap.put("regionCode", x.getRegionCode());
                childMap.put("regionName", x.getRegionName());
                childMap.put("preRegionCode", x.getPreRegionCode());
                childMap.put("regionLevel", x.getRegionLevel());
                childMap.put("description", x.getDescription());
                childMap.put("childList", getChildList(x.getRegionCode()));
                childList.add(childMap);
            }
        }
        return childList;
    }
}
