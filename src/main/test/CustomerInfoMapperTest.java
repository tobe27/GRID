import model.CustomerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.CustomerInfoService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class CustomerInfoMapperTest {
    @Autowired
    CustomerInfoService infoService;

    @Test
    public void batchUpdate() throws Exception {
        List<CustomerInfo> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CustomerInfo info = new CustomerInfo();
            info.setCustomerId(i+2L);
            info.setStatus(0);
            list.add(info);
        }
        System.out.println(infoService.batchUpdateStatus(list));
    }


    @Test
    public void getAndList() throws Exception {
        CustomerInfo info = new CustomerInfo();
        System.out.println(infoService.listCustomersByAccountId(info));
        System.out.println(infoService.listCustomersByOrgCode(info));

    }

    @Test
    public void insert() throws Exception {
        CustomerInfo info = new CustomerInfo();
        info.setCustomerId(2L);
        info.setCustomerName("赵菲菲");
        info.setIdNumber("123333333333X");
        info.setGridCode("X007");

        System.out.println(infoService.updateByPrimaryKeySelective(info));
        //System.out.println(infoService.insertSelective(info));
    }

    @Test
    public void delete() throws Exception {
        System.out.println(infoService.deleteByPrimaryKey(2L));
    }
}
