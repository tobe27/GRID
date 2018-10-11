import com.alibaba.fastjson.JSON;
import model.DishonestCustomerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.DishonestCustomerInfoService;
import util.QueryDishonestUtil;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class DishonestMapperTest {
    @Autowired
    DishonestCustomerInfoService dishonestCustomerInfoService;

    @Test
    public void insertTest() throws Exception {
        List<DishonestCustomerInfo> list = QueryDishonestUtil.listDishonest("牛高","41041119610****2058");
        if (list == null) {
            System.out.println("null");
        } else {
            long id = 11;
            for (DishonestCustomerInfo info : list) {
                //info.setId(id);
                dishonestCustomerInfoService.insertSelective(info);
                //dishonestCustomerInfoService.updateByPrimaryKeySelective(info);
                id++;
            }
        }
    }


    @Test
    public void listTest() throws Exception {
        DishonestCustomerInfo info = new DishonestCustomerInfo();
        info.setCardNumber("41041119610****2058");
        List<DishonestCustomerInfo> set = dishonestCustomerInfoService.listByPerformedNameOrCardNumber(info);
        System.out.println(JSON.toJSONString(dishonestCustomerInfoService.getByPrimaryKey(1L),true));
        System.out.println(set+":"+set.size());
    }

    @Test
    public void deleteTest() throws Exception {
        for (long i = 1; i<20; i++) {
            dishonestCustomerInfoService.deleteByPrimaryKey(i);
        }
        System.out.println(dishonestCustomerInfoService.deleteByPrimaryKey(14L));
    }
}
