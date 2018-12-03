import com.alibaba.fastjson.JSON;
import model.GridUser;
import org.junit.Test;
import util.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NormalTest {

    @Test
    public void idGen() {
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("1", null);
        formMap.put("2", null);
        formMap.put("3", null);
        System.out.println(JSON.toJSONString(formMap, true));
    }


    @Test
    public void el() {

        String uuid = UUID.randomUUID().toString();
        String[] id = uuid.split("-");
        String xx = id[0].toUpperCase();
        int count = 800000;

        String ids = "1,2,3";
        String[] idArr = ids.split(",");
        for (String idd : idArr) {
            System.out.println(idd);
        }
    }


    @Test
    public void password() {
        System.out.println(Md5Util.createSaltMD5("123456"));
    }
    @Test
    public void nullTest() {
        GridUser user = new GridUser();
        user.setPassword("1xxx");
        user.setPhoneNumber(15555413245L);
        String email = "_xxx@x.con.cn";
        System.out.println(ValidUtil.isNotEmpty(user));
        System.out.println(ValidUtil.isLength(user.getPassword(),5));
        System.out.println(ValidUtil.isLoginName(user.getPassword(),5));
        System.out.println(ValidUtil.isPhone(String.valueOf(user.getPhoneNumber())));
        System.out.println(ValidUtil.isNumber(String.valueOf(user.getPassword())));
        System.out.println(ValidUtil.isEmail(email));
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

    @Test
    public void  subString() {
        String idCard = "342224199208160981";
        String subFront = idCard.substring(0, 11);
        String subBack = idCard.substring(14,18);
        System.out.println(subFront);
        System.out.println(subBack);
    }

    @Test
    public void  date() {
        long millisecond = Instant.now().toEpochMilli();
        long second = Instant.now().getEpochSecond();
        long now = System.currentTimeMillis();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(DateConvertUtil.getMillisecond());
        System.out.println(DateConvertUtil.convert2String(now, DatePatternEnum.DATE_TIME_MILLI));
        System.out.println(DateConvertUtil.convertDateTime2Long("2018-11-06 00:00:00.123", DatePatternEnum.DATE_TIME_MILLI));
        System.out.println(DateConvertUtil.convertDate2Long("2018-11-06"));

    }
}
