import com.alibaba.fastjson.JSON;
import model.ResponseData;
import org.junit.Test;
import util.JwtUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWTTest {

    @Test
    public void jwt(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "name");
        String token = JwtUtil.createToken(map,1000);
        System.out.println(token+":length:"+token.length());
        System.out.println("************************");
        System.out.println("************************");
        System.out.println(JSON.toJSONString(JwtUtil.parseToken(token)));
    }

    @Test
    public void jwtTest(){
        String token = "";
        System.out.println(JSON.toJSONString(JwtUtil.parseToken(token)));
        System.out.println(JwtUtil.verifyToken(token));
    }
    @Test
    public void match(){
        String url = "/user";
        String role = "userx";
        System.out.println(url);
        System.out.println(url.indexOf(role));
    }

}
