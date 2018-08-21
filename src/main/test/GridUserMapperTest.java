import dao.GridUserMapper;
import model.GridUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.GridUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class GridUserMapperTest {
    @Autowired
    GridUserService gridUserService;

    @Test
    public void insertTest() throws Exception {
        GridUser gridUser = new GridUser();
        gridUser.setAccountName("aha");
        gridUser.setPassword("xxx");
        gridUser.setRealName("YYY");
        gridUser.setPhoneNumber(122233L);
        long now = System.currentTimeMillis();
        gridUser.setCreatedAt(now);
        gridUser.setUpdatedAt(now);
        System.out.println(gridUserService.insertSelective(gridUser));
    }

    @Test
    public void selectTest() throws Exception {
        System.out.println(gridUserService.selectByPrimaryKey(1L));
    }

    @Test
    public void updateStatusTest() throws Exception {
        GridUser gridUser = new GridUser();
        gridUser.setAccountId(1L);
        gridUser.setStatus(2);
        System.out.println(gridUserService.updateByPrimaryKeySelective(gridUser));
        System.out.println(gridUserService.deleteByPrimaryKey(2L));
    }

}
