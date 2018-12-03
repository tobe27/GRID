import model.PlatformInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.PlatformInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-11-9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class PlatformInfoMapperTest {
    @Autowired
    PlatformInfoService platformInfoService;

    @Test
    public void insert() throws Exception {
        PlatformInfo info = new PlatformInfo();
        List<Long> longList = new ArrayList<>();
        longList.add(5L);
        longList.add(19L);
        System.out.println(platformInfoService.sendPlatformInfo(1, "面谈面签审批", 5L, 2, 0, longList));
    }

    @Test
    public void list() throws Exception {
        PlatformInfo info = new PlatformInfo();
        info.setHandlerId(19L);
        System.out.println(platformInfoService.listByHandlerId(info));
    }


}
