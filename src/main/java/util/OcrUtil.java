package util;


import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSONObject;

import controller.FileUploadController;
import exception.MyException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 百度云OCR工具类，识别身份证
 */
public class OcrUtil {
    // Access_Token获取
    private static final String ACCESS_TOKEN_HOST = "https://aip.baidubce.com/oauth/2.0/token?";
    // 身份证识别请求URL
    private static final String OCR_HOST = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?";
    // apiKey,secretKey
    private static final String API_KEY ="Xb12m5t4jS2n7jREvOgnBHIY";
    private static final String SECRET_KEY = "9XVx9GPcSbSUTZCbSuvdUDDYXiB6MMLa";
    private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    // 获取百度云OCR的授权access_token
    private static String getAccessToken() {
        return getAccessToken(API_KEY, SECRET_KEY);
    }
   
    /**
     * 获取百度云OCR的授权access_token
     * @param apiKey
     * @param secretKey
     * @return
     */
    public static String getAccessToken(String apiKey, String secretKey) {
        String accessTokenURL = ACCESS_TOKEN_HOST
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + apiKey
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + secretKey;

        try {
            URL url = new URL(accessTokenURL);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                result.append(inputLine);
            }
            JSONObject jsonObject = JSONObject.parseObject(result.toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取身份证识别后的数据
     * @param image
     * @param idCardSide 1-正面（头像面），2-反面
     * @return
     */
    public static String getStringIdentityCard(File image, int idCardSide) throws Exception {

        String side;
        if (idCardSide == 1) {
            side = "front"; //正面（头像面）
        } else if (idCardSide == 2) {
            side = "back"; //反面
        } else {
            throw new MyException("身份证正反面参数请求错误："+idCardSide);
        }
        // 身份证OCR的http URL+鉴权token
        String OCRUrl = OCR_HOST+"access_token="+getAccessToken();
        // 对图片进行base64处理
        String imageUrl = encodeImageToBase64(image);
        // 请求参数
        String requestParam = "detect_direction=true&id_card_side="+side+"&image="+imageUrl;

        try {
            // 请求OCR地址
            URL url = new URL(OCRUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法为POST
            connection.setRequestMethod("POST");

            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("apiKey", API_KEY);
            connection.setDoOutput(true);
            connection.getOutputStream().write(requestParam.getBytes(StandardCharsets.UTF_8));
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                result.append(inputLine);
            }
            bufferedReader.close();
            return result.toString();
        } catch (Exception e) {
            throw new MyException("身份证OCR识别异常");
        }
    }

    /**
     * 提取OCR识别身份证有效信息
     * @param value
     * @return
     */
    public static Map<String, String> getJsonIdCardInfo(String value) {
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(value);
        JSONObject words_result = jsonObject.getJSONObject("words_result");
        if (words_result == null || words_result.isEmpty()) {
            throw new MyException("请正确提供身份证正反面图片");
        }
        for (String key : words_result.keySet()) {
            JSONObject result = words_result.getJSONObject(key);
            String info = result.getString("words");
            switch (key) {
                case "姓名":
                    map.put("name", info);
                    break;
                case "性别":
                    map.put("sex", info);
                    break;
                case "民族":
                    map.put("nation", info);
                    break;
                case "出生":
                    map.put("birthday", info);
                    break;
                case "住址":
                    map.put("address", info);
                    break;
                case "公民身份号码":
                    map.put("idNumber", info);
                    break;
                case "签发机关":
                    map.put("issuedOrganization", info);
                    break;
                case "签发日期":
                    map.put("issuedAt", info);
                    break;
                case "失效日期":
                    map.put("expiredAt", info);
                    break;
            }
        }
        return map;

    }

    /**
     * 对图片url进行Base64编码处理
     * @param image
     * @return
     */
    public static String encodeImageToBase64(File image) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data;
        InputStream inputStream = null;
        try {
             inputStream = new FileInputStream(image);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            // 对字节数组Base64编码
            return URLEncoder.encode(Base64.byteArrayToBase64(data), "UTF-8");
        } catch (Exception e) {
            return null;
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("关闭输入流异常",e);
            }

        }

    }

    
    /**
     * 对文件进行Base64编码处理
     * @param image
     * @return
     */
    public static String fileToBase64Code(File image) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data;
        InputStream inputStream = null;
        try {
             inputStream = new FileInputStream(image);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();

            // 对字节数组Base64编码
            return Base64.byteArrayToBase64(data);
        } catch (Exception e) {
        	
            return null;
        }
        finally {
            try {
                if (inputStream != null) {
                	inputStream.close();
                }
            } catch (IOException e) {
                logger.error("关闭输出流异常",e);
            }

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("关闭输入流异常",e);
            }

        }

    }

    
    
}
