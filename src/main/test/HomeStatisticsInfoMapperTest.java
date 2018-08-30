import model.HomeStatisticsInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.HomeStatisticsInfoService;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class HomeStatisticsInfoMapperTest {
    @Autowired
    HomeStatisticsInfoService infoService;

    @Test
    public void insert() {
        HomeStatisticsInfo info = new HomeStatisticsInfo();
        info.setOrgCode(17001L);
        info.setOrgLevel(1);
        info.setPeopleNum(100000L);
        info.setAmount(new BigDecimal("100000.11"));
        info.setAmountType(1);
        info.setTime("201802");
        info.setTimeType(2);
        try {
            System.out.println(infoService.insertSelective(info));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void  list() {
        HomeStatisticsInfo info = new HomeStatisticsInfo();
        info.setOrgCode(17000L);
        info.setOrgLevel(1);
        //info.setPeopleNum(100000L);
        //info.setAmount(new BigDecimal("100000.11"));
        //info.setAmountType(1);
        info.setTime("2018");
        info.setTimeType(2);
        try {
            System.out.println(infoService.listInfo(info));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
