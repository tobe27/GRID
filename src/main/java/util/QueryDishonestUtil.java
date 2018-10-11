package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import exception.MyException;
import model.DishonestCustomerInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬取客户的失信记录
 * @author Created by L.C.Y on 2018-9-28
 */
public class QueryDishonestUtil {
    private static final String UTF_8 = "UTF-8";
    private static final String SPIDER_URL = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php";
    private static Logger logger = LoggerFactory.getLogger(QueryDishonestUtil.class);

    /**
     * 查询失信人
     * @param performedName 名称
     * @param cardNumber 证件号码
     * @return
     */
    public static List<DishonestCustomerInfo> listDishonest(String performedName, String cardNumber) {
        return listDishonest(performedName, cardNumber, "");
    }

    /**
     * 查询失信人
     * @param performedName 名称
     * @param cardNumber 证件号码
     * @param areaName 省份
     * @return
     */
    public static List<DishonestCustomerInfo> listDishonest(String performedName, String cardNumber, String areaName) {

        // 返回的结果初始化列表
        List<DishonestCustomerInfo> infoList = new ArrayList<>();

        // 查询结果
        String strResult = getStringDishonest(performedName, cardNumber, areaName);

        // json封装
        JSONObject firstMap = JSONObject.parseObject(strResult);
        JSONArray secondMap = (JSONArray) firstMap.get("data");

        // 取出Result中的信息
        if (secondMap != null && secondMap.size() > 0) {
            JSONObject thirdMap = (JSONObject) secondMap.get(0);
            JSONArray forthMap = (JSONArray) thirdMap.get("result");
            for (int i = 0; i < forthMap.size(); i++) {
                JSONObject maps = forthMap.getJSONObject(i);
                logger.info("JSON Object["+ i +"]:"+maps);

                // 自定义类封装
                DishonestCustomerInfo info = new DishonestCustomerInfo();
                info.setPerformedName(performedName);
                info.setCardNumber(cardNumber);
                info.setSex(String.valueOf(maps.get("sexy")).trim());
                info.setAge(Integer.valueOf(String.valueOf(maps.get("age")).trim()));
                info.setAreaName(String.valueOf(maps.get("areaName")).trim());
                info.setCourtName(String.valueOf(maps.get("courtName")).trim());
                info.setGistId((String.valueOf(maps.get("gistId")).trim()));
                info.setRegisterDate(String.valueOf(maps.get("regDate")).trim());
                info.setCaseCode(String.valueOf(maps.get("caseCode")).trim());
                info.setGistInstitution(String.valueOf(maps.get("gistUnit")).trim());
                info.setDuty(String.valueOf(maps.get("duty")).trim());
                info.setPerformance(String.valueOf(maps.get("performance")).trim());
                info.setConcreteReason(String.valueOf(maps.get("disruptTypeName")).trim());
                info.setType(String.valueOf(maps.get("type")).trim());
                info.setPublishedAt(Long.valueOf(String.valueOf(maps.get("publishDateStamp")).trim()+"000") );
                infoList.add(info);
            }
        }
        return infoList;
    }



    /**
     *  查询出原始数据，返回字符串
     * @param
     * @return
     */
    public static String getStringDishonest(String performedName, String cardNumber, String areaName) {
        // 查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("resource_id", "6899");
        map.put("query", "失信被执行人名单");
        map.put("cardNum", cardNumber);
        map.put("iname", performedName);
        map.put("areaName", areaName);
        map.put("ie", "utf-8");
        map.put("oe", "utf-8");
        map.put("format", "json");
        map.put("t", "1524537973200");
        map.put("cb", "jQuery110207319777455577083_1524537959352");
        map.put("_", "1524537959354");

        StringBuffer resultBuffer;
        String fullUrl = null;


        BufferedReader bufferedReader = null;
        // 构建请求参数
        // 拼接请求参数，并进行URLEncoder编码
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            buffer.append(entry.getKey());
            buffer.append("=");
            try {
                buffer.append(URLEncoder.encode((String) entry.getValue(), QueryDishonestUtil.UTF_8));
            } catch (UnsupportedEncodingException e) {
                throw new MyException("失信人查询编码异常:"+e);
            }
            buffer.append("&");
        }

        // 拼接查询URL
        if (buffer.length() > 0) {
            fullUrl = QueryDishonestUtil.SPIDER_URL + "?" + buffer.substring(0, buffer.length() - 1);
        }

        // 创建HTTP请求
        HttpClient client = HttpClients.createDefault();
        // GET请求拼接后的地址获取详细信息
        HttpGet httpGet = new HttpGet(fullUrl);
        try {
            // 获得响应数据
            HttpResponse response = client.execute(httpGet);
            // 读入响应数据体文本信息
            // http传输乱码-解决new InputStreamReader(response.getEntity().getContent(),UTF_8)
            bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
            String temp;
            resultBuffer = new StringBuffer();
            while ((temp = bufferedReader.readLine()) != null) {
                resultBuffer.append(temp);
            }

            return resultBuffer.toString().substring(
                    resultBuffer.toString().indexOf("(")+1,resultBuffer.toString().lastIndexOf(");"));
        } catch (IOException e) {
            logger.error("失信工具IO异常："+e.getMessage());
            throw new MyException("失信工具IO异常:"+e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error("失信工具IO关闭异常："+e.getMessage());
                }
            }
        }
    }
}
