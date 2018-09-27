import com.alibaba.fastjson.JSON;
import org.junit.Test;
import util.DishonestUtil;
import util.OcrUtil;

import java.io.File;

public class UtilTest {
    public static void main(String[] args) throws Exception {
        String value = OcrUtil.getStringIdentityCard(new File("D:\\project\\front.png"), 1);
        System.out.println(value+"\n"+OcrUtil.getJsonIdCardInfo(value));
    }

    @Test
    public void dishonestTest() {
        System.out.println(JSON.toJSONString(DishonestUtil.listDishonest("牛高峰",""),true));

    }
}
