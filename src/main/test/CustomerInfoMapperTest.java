import model.CustomerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.CustomerInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class CustomerInfoMapperTest {
    @Autowired
    CustomerInfoService infoService;

    @Test
    public void getAndList() throws Exception {
        CustomerInfo info = new CustomerInfo();
        System.out.println(infoService.getCustomerByPrimaryKey(1L));
        System.out.println(infoService.listCustomers(info));

    }

    @Test
    public void insert() throws Exception {
        CustomerInfo info = new CustomerInfo();
        info.setCustomerId(2L);
        info.setCustomerName("赵菲菲");
        info.setIdNumber("123333333333X");
        info.setGridCode("X007");
        info.setPhoneNumber(111111111111L);
        info.setHouseholdId(888888888L);
        System.out.println(infoService.updateByPrimaryKeySelective(info));
        //System.out.println(infoService.insertSelective(info));
    }

    @Test
    public void delete() throws Exception {
        System.out.println(infoService.deleteByPrimaryKey(2L));
    }
}
