package dao;

import model.PlatformInfo;

import java.util.List;

public interface PlatformInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(PlatformInfo record);

    PlatformInfo getByPrimaryKey(Long id);

    List<PlatformInfo> listByHandlerId(PlatformInfo record);

    int updateByPrimaryKeySelective(PlatformInfo record);

}