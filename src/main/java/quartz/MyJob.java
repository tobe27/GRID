package quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import dao.GridInfoMapper;

public class MyJob implements Job{
  @Autowired
  private GridInfoMapper  gridInfoMapper;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		 //System.out.println(new Date() + ": job 1 doing something...");
        //Map<String,Object> map=new HashMap<>();
        //此写法可以解决Job类中无法注入的问题
       // SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        
        //List< Map<String,Object>> list=gridInfoMapper.getGridInfoList(map);
        //System.out.println("======="+list.toString());
	}
}
