package service;

import model.ResidentInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface ResidentInfoService {
    /**
     * 删除居民信息
     * @param residentId
     * @return
     * @throws Exception
     */
    boolean deleteByPrimaryKey(Long residentId) throws Exception;

    /**
     * 新增居民信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean insertSelective(ResidentInfo record, HttpServletRequest request) throws Exception;

    /**
     * 获取居民详情
     * @param residentId
     * @return
     * @throws Exception
     */
    ResidentInfo getResidentByPrimaryKey(Long residentId) throws Exception;

    /**
     * 通过身份证获取户籍信息
     * @param idNumber
     * @return
     * @throws Exception
     */
    ResidentInfo getByIdNumber(String idNumber) throws Exception;

    /**
     * 获取居民列表-通过登录用户ID获取
     * @param record
     * @return
     * @throws Exception
     */
    List<ResidentInfo> listResidents(ResidentInfo record) throws Exception;

    /**
     * 获取居民列表-通过登录用户机构获取
     * @param record
     * @return
     * @throws Exception
     */
    List<ResidentInfo> listByOrg(ResidentInfo record) throws Exception;

    /**
     * 编辑居民信息
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateByPrimaryKeySelective(ResidentInfo record) throws Exception;

    /**
     * 编辑居民信息状态
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateResidentStatus(ResidentInfo record) throws Exception;

    /**
     * 批量新增居民信息
     * @param list
     * @return
     * @throws Exception
     */
    boolean batchSave(List<ResidentInfo> list) throws Exception;
}
