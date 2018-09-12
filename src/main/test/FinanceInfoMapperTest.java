import model.FinanceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.FinanceInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class FinanceInfoMapperTest {
    @Autowired
    FinanceInfoService infoService;

    @Test
    public void get() throws Exception {
        FinanceInfo info = new FinanceInfo();
        String idNumber = "1111XY";
        info.setIdNumber(idNumber);
        info.setBreedNumber(66);
        System.out.println(infoService.insertSelective(info));
        System.out.println(infoService.getFinanceInfoByIdNumber(idNumber));
        info.setCareer("工程师");
        System.out.println(infoService.updateByIdNumberSelective(info));
        System.out.println(infoService.deleteByIdNumber(idNumber));
        System.out.println(infoService.getFinanceInfoByIdNumber(idNumber));
    }
}
