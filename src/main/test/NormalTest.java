import model.GridUser;
import org.junit.Test;
import util.ValidUtil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
        long now = System.currentTimeMillis();
        LocalDate localDate = LocalDate.now();
        System.out.println(now);
        System.out.println(localDate);
        Instant instant = Instant.ofEpochMilli(now);
        System.out.println(instant);
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(LocalDateTime.ofInstant(instant, zoneId));


    }
}
