package cn.wdq.controller;

import cn.wdq.common.singleton.SingletonH;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RequestMapping
@Controller
public class TestController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    static{
        System.out.println("TestController");
    }
    @RequestMapping("/singleTest")
    @ResponseBody
    public void singleTest(String str){
        List<String> list = SingletonH.getInstance();
        list.add(str);
        System.out.println(list.hashCode());
    }

    @RequestMapping("/testParameter")
    @ResponseBody
    public String testParameter(String str, Integer date) {
        int a = 1/0;
        return "";
    }

    @RequestMapping("/testHttpRequest")
    @ResponseBody
    public String testParameter(HttpServletRequest httpRequest) {
        System.out.println(httpRequest);
        return "";
    }

    @RequestMapping("/testRequestInputStream")
    @ResponseBody
    public String testRequestInputStream(HttpServletRequest httpRequest) throws IOException {
        InputStream is = null;
        StringBuffer sb = new StringBuffer();
        try {
            int n = httpRequest.getContentLength();
            is = httpRequest.getInputStream();
            byte[] b = new byte[4096]; // 1024?有啥区别
            for (int i = n; (n = is.read(b)) != -1;) {
                sb.append(new String(b , 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        System.out.println(sb);
        JSONObject json = JSON.parseObject(sb.toString());
        return "";
    }
}
