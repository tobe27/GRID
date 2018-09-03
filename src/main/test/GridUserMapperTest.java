import com.alibaba.fastjson.JSON;
import dao.GridUserMapper;
import model.GridRole;
import model.GridUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.GridUserService;
import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class GridUserMapperTest {
    @Autowired
    GridUserService gridUserService;
    @Resource
    GridUserMapper gridUserMapper;

    @Test
    public void returnId(){
        GridUser user = new GridUser();
        user.setPassword("11111");
        System.out.println(gridUserMapper.insertSelective(user));
        System.out.println(user.getAccountId());
    }

    @Test
    public void setSRTest(){
        Set<String> roles = gridUserMapper.getStringRolesByUserPrimaryKey(1L);
        for (String role:roles)
            System.out.println(role);

        Set<String> perms = gridUserMapper.getStringPermissionsByUserPrimaryKey(1L);
        for (String perm : perms)
            System.out.println(perm);
    }

    @Test
    public void insertTest() throws Exception {
        GridUser gridUser = new GridUser();
        gridUser.setAccountName("aha");
        gridUser.setPassword("xxx");
        gridUser.setRealName("YYY");
        gridUser.setPhoneNumber(122233L);
        System.out.println(gridUserService.insertSelective(gridUser));
    }


    @Test
    public void selectTest() throws Exception {
        GridUser gridUser = new GridUser();
        gridUser.setAccountName("a");
        gridUser.setRealName("1");
        System.out.println(gridUser);
//        List<GridUser> users = gridUserMapper.getUsersByAccountNameOrRealNameOrOrgName(gridUser);
//        List<GridUser> userList = gridUserMapper.getUsersByRoleId(7L);
        GridRole role = new GridRole();
        role.setRoleId(7L);
        List<GridRole> roles = new ArrayList<>();
        roles.add(role);
        gridUser.setRoles(roles);
        List<GridUser> userList = gridUserMapper.getUsersByAccountNameOrRealNameOrOrgName(gridUser);
        System.out.println(JSON.toJSONString(userList,true));
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
