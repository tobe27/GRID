import model.ResidentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ResidentInfoService;

import static java.awt.SystemColor.info;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class ResidentInfoMapperTest {
    @Autowired
    ResidentInfoService infoService;

    @Test
    public void insert() {
        ResidentInfo info = new ResidentInfo();
        info.setResidentName("刘长峰");
        info.setIdNumber("340121196705175535");
        info.setHouseholdType("居民家庭户");
        info.setSex("男");
        info.setNation("汉");
        info.setHouseholdId(1338514L);

        try {
            System.out.println(infoService.insertSelective(info));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void update() {
        ResidentInfo info = new ResidentInfo();
        info.setResidentId(1L);
        info.setResidentName("季长峰");
        info.setIdNumber("34012119670517553X");
        info.setHouseholdType("居民家庭户");
        info.setSex("男");
        info.setNation("汉");
        info.setHouseholdId(1338514L);

        try {
            System.out.println(infoService.updateByPrimaryKeySelective(info));
            System.out.println(infoService.getResidentByPrimaryKey(1L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void list() throws Exception {
        ResidentInfo info = new ResidentInfo();
        info.setResidentId(1L);
        info.setIdNumber("7051755");
        info.setResidentName("季");
        System.out.println(infoService.listResidents(info));
    }

    @Test
    public void delete() throws Exception {
        System.out.println(infoService.deleteByPrimaryKey(2L));
    }
}
