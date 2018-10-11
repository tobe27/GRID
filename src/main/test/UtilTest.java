import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.CustomerInfo;
import org.junit.Test;
import util.QueryDishonestUtil;

public class UtilTest {


    @Test
    public void dishonestTest() {
        System.out.println(QueryDishonestUtil.getStringDishonest("牛高峰","",""));
        System.out.println(JSON.toJSONString(QueryDishonestUtil.listDishonest("牛高峰",""),true));

    }
    @Test
    public void jsonParse() {
        CustomerInfo info = new CustomerInfo();
        info.setCustomerName("json");
        info.setIdNumber("1111111");
        String x = info.toString();
    }
}
