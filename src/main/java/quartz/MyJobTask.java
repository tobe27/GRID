package quartz;

import java.util.Date;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class MyJobTask {
	public void doSomething(){
        //System.out.println(new Date() + ": job 2 doing something...");
        //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        //QuartzManager quartzManager = (QuartzManager)wac.getBean("quartzManager");
        //quartzManager.shutdownJobs();

    }
}
