import model.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.RegionService;

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
        System.out.println(regionService.deleteByPrimaryKey(10000L));
        System.out.println(regionService.getRegionByPrimaryKey(10000L));
        System.out.println(regionService.getRegionsByPrimaryKey(10001L));
    }

}
