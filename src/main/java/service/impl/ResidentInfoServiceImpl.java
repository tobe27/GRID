package service.impl;

import dao.ResidentInfoMapper;
import exception.MyException;
import model.ResidentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ResidentInfoService;

import java.sql.SQLOutput;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Service
public class ResidentInfoServiceImpl implements ResidentInfoService {
    @Autowired
    ResidentInfoMapper infoMapper;

    /**
     * 删除居民信息
     *
     * @param residentId
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByPrimaryKey(Long residentId) throws Exception {
        try {
            return infoMapper.deleteByPrimaryKey(residentId) == 1;
        } catch (Exception e) {
            throw new MyException("删除居民信息出现异常");
        }
    }

    /**
     * 新增居民信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean insertSelective(ResidentInfo record) throws Exception {
        if (record.getResidentName() == null || record.getResidentName().isEmpty()) {
            throw new MyException("姓名不能为空");
        }
        if (record.getIdNumber() == null || record.getIdNumber().isEmpty()) {
            throw new MyException("居民身份证号码不能为空");
        }
        if (record.getAddress() == null || record.getAddress().isEmpty()) {
            throw new MyException("地址不能为空");
        }
        if (record.getHouseholdType() == null || record.getHouseholdType().isEmpty()) {
            throw new MyException("户别不能为空");
        }
        if (record.getSex() == null || record.getSex().isEmpty()) {
            throw new MyException("性别不能为空");
        }
        if (record.getNation() == null || record.getNation().isEmpty()) {
            throw new MyException("民族不能为空");
        }
        if (record.getHouseholdId() == null) {
            throw new MyException("户号不能为空");
        }
        long now = System.currentTimeMillis();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        try {
            return infoMapper.insertSelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("新增居民信息出现异常");
        }
    }

    /**
     * 获取居民详情
     *
     * @param residentId
     * @return
     * @throws Exception
     */
    @Override
    public ResidentInfo getResidentByPrimaryKey(Long residentId) throws Exception {
        try {
            return infoMapper.getResidentByPrimaryKey(residentId);
        } catch (Exception e) {
            throw new MyException("获取居民信息出现异常");
        }
    }

    /**
     * 获取居民列表
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public List<ResidentInfo> listResidents(ResidentInfo record) throws Exception {
        try {
            return infoMapper.listResidents(record);
        } catch (Exception e) {
            throw new MyException("获取居民信息列表出现异常");
        }
    }

    /**
     * 编辑居民信息
     *
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateByPrimaryKeySelective(ResidentInfo record) throws Exception {
        if (record.getResidentName() == null || record.getResidentName().isEmpty()) {
            throw new MyException("姓名不能为空");
        }
        if (record.getIdNumber() == null || record.getIdNumber().isEmpty()) {
            throw new MyException("居民身份证号码不能为空");
        }
        if (record.getAddress() == null || record.getAddress().isEmpty()) {
            throw new MyException("地址不能为空");
        }
        if (record.getHouseholdType() == null || record.getHouseholdType().isEmpty()) {
            throw new MyException("户别不能为空");
        }
        if (record.getSex() == null || record.getSex().isEmpty()) {
            throw new MyException("性别不能为空");
        }
        if (record.getNation() == null || record.getNation().isEmpty()) {
            throw new MyException("民族不能为空");
        }
        if (record.getHouseholdId() == null) {
            throw new MyException("户号不能为空");
        }
        long now = System.currentTimeMillis();
        record.setUpdatedAt(now);
        try {
            return infoMapper.updateByPrimaryKeySelective(record) == 1;
        } catch (Exception e) {
            throw new MyException("修改居民信息出现异常");
        }
    }
    
    /**
     * 批量插入居民户籍信息
     *
     * @param List<ResidentInfo>
     * @return
     * @throws Exception
     */
	@Override
	public boolean batchSave(List<ResidentInfo> list) throws Exception {
		 try {
		 return infoMapper.batchSave(list)==1;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new MyException("批量新增居民信息出现异常");
		 }
	}
}
