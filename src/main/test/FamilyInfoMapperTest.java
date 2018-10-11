import model.FamilyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.FamilyInfoService;

/**
 * @author Created by L.C.Y on 2018-10-10
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class FamilyInfoMapperTest {
    @Autowired
    FamilyInfoService familyInfoService;

    @Test
    public void insert() throws Exception {
        FamilyInfo info = new FamilyInfo();
        info.setHouseholdId(12121213L);
        info.setPopulation(5);
        info.setAddress("北京");
        info.setPostcode(100000);
        info.setLocalCredit("较好");
        info.setIsHarmony("是");
        info.setSocialEvaluation("家庭关系和睦，资金往来频繁，信誉较好");
        info.setIsOweTax("否");
        info.setIsOweTax("是");
        info.setProduceCapacity("很好");
        info.setInsuranceType("社会保险");
        //System.out.println(familyInfoService.insertSelective(info));
        info.setFamilyId(1L);
        System.out.println(familyInfoService.updateByPrimaryKeySelective(info));
    }

    @Test
    public void get() throws Exception {
        System.out.println(familyInfoService.getByHouseholdId(12121213L));
    }
}
