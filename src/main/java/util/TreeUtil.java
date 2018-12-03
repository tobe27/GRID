package util;

import model.Organization;
import model.Region;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by L.C.Y on 2018-10-11
 */
public class TreeUtil {
    private List<Region> parentRegionList;
    private List<Organization> parentOrgList;


    /**
     * 获取区域树形结构列表
     * @param parentList
     * @param parentCode
     * @return
     */
    public List<Object> getRegionList(List<Region> parentList, Long parentCode) {
        this.parentRegionList = parentList;
        List<Object> childList = new ArrayList<>();
        for (Region x : parentList) {
            Map<String, Object> parentMap = new LinkedHashMap<>();
            if (parentCode.equals(x.getRegionCode())) {
                parentMap.put("regionCode", x.getRegionCode());
                parentMap.put("regionName", x.getRegionName());
                parentMap.put("preRegionCode", x.getPreRegionCode());
                parentMap.put("regionLevel", x.getRegionLevel());
                parentMap.put("description", x.getDescription());
                parentMap.put("childList", getChildRegionList(x.getRegionCode()));
                childList.add(parentMap);
            }
        }
        return childList;
    }

    /**
     * 获取机构管理树形结构
     * @param parentOrgList
     * @param parentId
     * @return
     */
    public List<Object> getOrgList(List<Organization> parentOrgList, Long parentId) {
        this.parentOrgList = parentOrgList;
        List<Object> childList = new ArrayList<>();
        for (Organization x : parentOrgList) {
            Map<String, Object> childMap = new LinkedHashMap<>();
            if (parentId.equals(x.getOrgId())) {
                childMap.put("orgId", x.getOrgId());
                childMap.put("orgCode", x.getOrgCode());
                childMap.put("orgName", x.getOrgName());
                childMap.put("corpCode", x.getCorpCode());
                childMap.put("preOrgId", x.getPreOrgId());
                childMap.put("relationType", x.getRelationType());
                childMap.put("status", x.getStatus());
                childMap.put("virtualOrgFlag", x.getVirtualOrgFlag());
                childMap.put("description", x.getDescription());
                childMap.put("type", x.getType());
                childMap.put("orgLevel", x.getOrgLevel());
                childMap.put("childList", getChildOrgList(x.getOrgId()));
                childList.add(childMap);
            }
        }
        return childList;
    }

    /**
     * 区域树形结构
     * @param regionCode
     * @return
     */
    private List<Object> getChildRegionList(Long regionCode) {
        List<Object> childList = new ArrayList<>();
        for (Region x : parentRegionList) {
            Map<String, Object> childMap = new LinkedHashMap<>();
            if (regionCode.equals(x.getPreRegionCode())) {
                childMap.put("regionCode", x.getRegionCode());
                childMap.put("regionName", x.getRegionName());
                childMap.put("preRegionCode", x.getPreRegionCode());
                childMap.put("regionLevel", x.getRegionLevel());
                childMap.put("description", x.getDescription());
                childMap.put("childList", getChildRegionList(x.getRegionCode()));
                childList.add(childMap);
            }
        }
        return childList;
    }



    /**
     * 机构树形结构
     * @param orgId
     * @return
     */
    private List<Object> getChildOrgList(Long orgId) {
        List<Object> childList = new ArrayList<>();
        for (Organization x : parentOrgList) {
            Map<String, Object> childMap = new LinkedHashMap<>();
            if (orgId.equals(x.getPreOrgId())) {
                childMap.put("orgId", x.getOrgId());
                childMap.put("orgCode", x.getOrgCode());
                childMap.put("orgName", x.getOrgName());
                childMap.put("corpCode", x.getCorpCode());
                childMap.put("preOrgId", x.getPreOrgId());
                childMap.put("relationType", x.getRelationType());
                childMap.put("status", x.getStatus());
                childMap.put("virtualOrgFlag", x.getVirtualOrgFlag());
                childMap.put("description", x.getDescription());
                childMap.put("type", x.getType());
                childMap.put("orgLevel", x.getOrgLevel());
                childMap.put("childList", getChildOrgList(x.getOrgId()));
                childList.add(childMap);
            }
        }
        return childList;
    }
}
