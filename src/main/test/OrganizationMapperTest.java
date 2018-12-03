import model.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.OrganizationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class OrganizationMapperTest {
    @Autowired
    OrganizationService organizationService;
    @Test
    public void insertTest() throws Exception {
        Organization organization = new Organization();
        organization.setOrgCode(17002L);
        organization.setOrgName("烟台办事处");
        organization.setDescription("编辑");
        //System.out.println(organizationService.insertSelective(organization));
        System.out.println(organizationService.updateByOrgCodeSelective(organization));
    }

    @Test
    public void getTest() throws Exception {
        System.out.println(organizationService.listOrganization(new Organization()));
    }


}
