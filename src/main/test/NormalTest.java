import model.GridUser;
import org.junit.Test;
import util.ValidUtil;

public class NormalTest {
    @Test
    public void nullTest() {
        GridUser user = new GridUser();
        user.setPassword("1xxx");
        user.setPhoneNumber(15555413245L);
        String email = "_xxx@x.con.cn";
        System.out.println(ValidUtil.notEmpty(user));
        System.out.println(ValidUtil.length(user.getPassword(),5));
        System.out.println(ValidUtil.loginName(user.getPassword(),5));
        System.out.println(ValidUtil.phone(String.valueOf(user.getPhoneNumber())));
        System.out.println(ValidUtil.number(String.valueOf(user.getPassword())));
        System.out.println(ValidUtil.email(email));
    }

    @Test
    public void timeTest() {
        String id_type = "xx";
        System.out.println(id_type);
        System.out.println(System.currentTimeMillis());
    }
}
