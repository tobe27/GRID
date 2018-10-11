import com.alibaba.fastjson.JSON;
import model.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.RegionService;
import util.TreeUtil;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class RegionMapperTest {
    @Autowired
    RegionService regionService;

    @Test
    public void insertRegionTest() throws Exception {
        Region region = new Region();
        region.setRegionCode(10003L);
        region.setRegionName("浦东新区");
        region.setRegionLevel(2);
        region.setPreRegionCode(10001L);
        //System.out.println(regionService.insertSelective(region));
        System.out.println(regionService.updateByPrimaryKeySelective(region));
    }

    @Test
    public void get() throws Exception {
        Region region = new Region();
        List<Region> regionList = regionService.listRegions(region);
        TreeUtil treeUtil = new TreeUtil();
        List list = treeUtil.getRegionList(regionList,10000L);
        System.out.println(JSON.toJSONString(list,true));

    }

}
