package concurrent;

import exception.MyException;
import model.DishonestCustomerInfo;
import model.ResidentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import service.DishonestCustomerInfoService;
import service.ResidentInfoService;
import util.QueryDishonestUtil;

import java.util.List;

/**
 * 开启线程从户籍信息查询失信人并导入
 * 注意：调用DAO层不能使用Spring注解，且实现类必须在xml中定义
 * @author Created by L.C.Y on 2018-11-16
 */
@Service
public class DishonestQueryThread extends Thread {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ResidentInfoService residentInfoService;
    private DishonestCustomerInfoService dishonestCustomerInfoService;

    public DishonestQueryThread(ResidentInfoService residentInfoService, DishonestCustomerInfoService dishonestCustomerInfoService) {
        this.residentInfoService = residentInfoService;
        this.dishonestCustomerInfoService = dishonestCustomerInfoService;
    }

    @Override
    public void run() {
        // 查询所有户籍
        List<ResidentInfo> residentInfoList = null;

        try {
            residentInfoList = residentInfoService.listResidents(new ResidentInfo());
            logger.info("户籍列表结束：" + residentInfoList);
        } catch (Exception e) {
            logger.error("查询户籍异常:" + e);
        }
        logger.info("户籍列表：" + residentInfoList);
        if (residentInfoList == null) {
            throw new MyException("户籍列表为空");
        }
        int count = 0; // 导入的数量
        // 遍历查询并导入数据库
        for (ResidentInfo residentInfo : residentInfoList) {
            // 查询失信人
            List<DishonestCustomerInfo> list = QueryDishonestUtil.listDishonest(residentInfo.getResidentName(), residentInfo.getIdNumber());
            // 为空则继续查询
            if (list == null || list.isEmpty()) {
                continue;
            }
            // 不为空导入到数据库
            for (DishonestCustomerInfo info : list) {
                try {
                    dishonestCustomerInfoService.insertSelective(info);
                    count ++;
                } catch (Exception e) {
                    logger.error("从户籍导入失信人异常" + e.getMessage());
                }
            }
        }
        logger.info("户籍总数:"+ residentInfoList.size() + ",导入失信人的总数:" + count + "." );
    }
}
