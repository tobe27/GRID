import model.HomeWarnInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.HomeWarnInfoService;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class HomeWarnInfoMapperTest {
    @Autowired
    HomeWarnInfoService infoService;

    @Test
    public void insertTest() throws Exception {
        HomeWarnInfo info = new HomeWarnInfo();
        info.setOrgCode(17002L);
        info.setOrgLevel(1);
        info.setAmount(new BigDecimal("1000.01"));
        info.setAmountType(1);
        info.setMessage("张三存款"+new BigDecimal("1000.01"));
        info.setTime(System.currentTimeMillis());
        System.out.println(infoService.insertSelective(info));
    }

    @Test
    public void  listTest() {
        HomeWarnInfo info = new HomeWarnInfo();
        //info.setOrgCode(17002L);
        //info.setOrgLevel(1);
        //info.setAmount(new BigDecimal("1000.01"));
        info.setAmountType(1);
        try {
            System.out.println(infoService.listInfo(info));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
