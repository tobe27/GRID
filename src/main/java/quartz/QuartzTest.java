package quartz;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class QuartzTest {

	public void  Test() {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        QuartzManager quartzManager = (QuartzManager)wac.getBean("quartzManager");
        System.out.println("【系统启动】开始(每1秒输出一次 job2)...");


        System.out.println("【增加job1启动】开始(每1秒输出一次)...");
        quartzManager.addJob("test", "test", "test", "test", MyJob.class, "0/1 * * * * ?");


        System.out.println("【修改job1时间】开始(每2秒输出一次)...");
        quartzManager.modifyJobTime("test", "test", "test", "test", "0/2 * * * * ?");


        System.out.println("【移除job1定时】开始...");
        quartzManager.removeJob("test", "test", "test", "test");
        quartzManager.shutdownJobs();
        // 关掉任务调度容器
        // quartzManager.shutdownJobs();

        quartzManager.shutdownJobs();
	}


}
