package dao;

import model.ResidentInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
public interface ResidentInfoMapper {
    int deleteByPrimaryKey(Long residentId);

    int insertSelective(ResidentInfo record);

    ResidentInfo getResidentByPrimaryKey(Long residentId);

    List<ResidentInfo> listResidents(ResidentInfo record);

    int updateByPrimaryKeySelective(ResidentInfo record);
    int  batchSave(@Param("list")List<ResidentInfo> list);
}