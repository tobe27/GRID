import com.alibaba.fastjson.JSON;
import dao.FamilyMemberMapper;
import model.FamilyMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.FamilyMemberService;

/**
 * @author Created by L.C.Y on 2018-11-5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class FamilyMemberMapperTest {
    @Autowired
    FamilyMemberService familyMemberService;
    @Autowired
    FamilyMemberMapper familyMemberMapper;

    @Test
    public void getM() {
        System.out.println(JSON.toJSONString(familyMemberMapper
                .getByCustomerIdAndIdNumber(new FamilyMember()
                        .setIdNumber("37012119870529139X")
                        .setCustomerId(925L)), true));
    }

    @Test
    public void insert() throws Exception {
        FamilyMember member = new FamilyMember();
        member.setMemberName("赵敏")
                .setRelationship("儿媳")
                .setBirthday("19801010")
                .setIdType("中国居民身份证")
                .setIdNumber("123456789987654321")
                .setNation("汉族")
                .setIsHouseholdHead("否")
                .setContact("16666666669")
                .setAddress("武当山武当观")
                .setRemark("太极创始人")
                .setCustomerId(75L);
        System.out.println("*******************************************");
        System.out.println(familyMemberService.insertSelective(member));
        System.out.println("返回自增ID："+member.getMemberId());

    }

    @Test
    public void update() throws Exception {
        FamilyMember member = new FamilyMember();
        member.setMemberName("殷素")
                .setRelationship("妻子")
                .setBirthday("19601010")
                .setIdType("中国居民身份证")
                .setIdNumber("123456789987654321")
                .setNation("汉族")
                .setIsHouseholdHead("否")
                .setContact("16666666669")
                .setAddress("武当山武当观")
                .setRemark("张翠山妻子")
                .setCustomerId(75L)
                .setMemberId(4L);
        System.out.println("*******************************************");
        System.out.println(familyMemberService.updateByPrimaryKeySelective(member));
    }

    @Test
    public void get() throws Exception {
        System.out.println("*******************************************");
        System.out.println(JSON.toJSONString(familyMemberService.getByPrimaryKey(2L),true));
        System.out.println("*******************************************");
        System.out.println(JSON.toJSONString(familyMemberService.listByCustomerId(75L),true));
    }

    @Test
    public void delete() throws Exception {
        System.out.println("*******************************************");
        System.out.println(familyMemberService.deleteByPrimaryKey(3L));
        System.out.println(JSON.toJSONString(familyMemberService.getByPrimaryKey(3L),true));
    }
}
