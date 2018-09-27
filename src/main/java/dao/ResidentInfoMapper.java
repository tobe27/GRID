package dao;

import model.ResidentInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface ResidentInfoMapper {
    int deleteByPrimaryKey(Long residentId);

    int insertSelective(ResidentInfo record);

    ResidentInfo getResidentByPrimaryKey(Long residentId);

    List<ResidentInfo> listResidents(ResidentInfo record);

    int updateByPrimaryKeySelective(ResidentInfo record);
    int  batchSave(@Param("list")List<ResidentInfo> list);
}