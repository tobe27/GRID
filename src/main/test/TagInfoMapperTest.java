import dao.TagCustomerMapper;
import model.TagCustomer;
import model.TagInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.TagCustomerService;
import service.TagInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class TagInfoMapperTest {
    @Autowired
    TagInfoService tagInfoService;
    @Autowired
    TagCustomerService tagCustomerService;
    @Autowired
    TagCustomerMapper tagCustomerMapper;

    @Test
    public void insertAndUpdateTagInfo() throws Exception {
        TagInfo tagInfo = new TagInfo();
        tagInfo.setTagId(1L);
        tagInfo.setTagName("三星级");
        tagInfo.setDescription("星级标签，额度达到600万以上");
        //System.out.println(tagInfoService.insertSelective(tagInfo));
        System.out.println(tagInfoService.updateByPrimaryKeySelective(tagInfo));
    }

    @Test
    public void getAndList() throws Exception {
        //System.out.println(tagInfoService.selectByPrimaryKey(2L));
        //System.out.println(tagInfoService.listTags(new TagInfo()));
        //System.out.println(tagCustomerService.listTagsByIdNumber("342224199910120671"));
        System.out.println(tagCustomerMapper.listCustomersByTagId(4L));
    }

    @Test
    public void delete() throws Exception {
        System.out.println(tagInfoService.deleteByPrimaryKey(1L));
        System.out.println(tagCustomerService.deleteTagByIdNumber("342224199910120672"));
    }

    @Test
    public void insertAndUpdateTagCustomer() throws Exception {
        TagCustomer tagCustomer = new TagCustomer();
        tagCustomer.setIdNumber("342224199910120673");
        tagCustomer.setTagId(6L);
        tagCustomer.setTagName("六星级");
        System.out.println(tagCustomerService.insertSelective(tagCustomer));
    }
}
