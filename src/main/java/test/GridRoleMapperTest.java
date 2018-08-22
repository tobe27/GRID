package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import model.GridRole;
import service.GridRoleService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class GridRoleMapperTest {
	@Autowired
  private GridRoleService gridRoleService;
	
	
	
	//@Test
	public void insertTest() {
		GridRole gr=new GridRole();
		 long now = System.currentTimeMillis();
		// gr.setRoleId(1);
		gr.setRoleName("test1");
		gr.setDescription("这是一个新增测试");
		gr.setRoleScope("all");
		gr.setCreatedAt(now );
		System.out.println(gridRoleService.insertSelective(gr));
		System.out.println("11111111111111111111111===================");
		
	}
	//@Test
	public void deleteTest() {
		System.out.println(gridRoleService.deleteByPrimaryKey(1));
		System.out.println("删除成功===================");
	}
	//@Test
	public void updateTest() {
		GridRole gr=new GridRole();
		gr.setRoleId(2);
		gr.setRoleName("222222");
		System.out.println(gridRoleService.updateByPrimaryKeySelective(gr));
	}
	@Test
	public void selectTest() {
		GridRole gr=gridRoleService.selectByPrimaryKey(2);
	System.out.println(gr.toString());	
	}

}
