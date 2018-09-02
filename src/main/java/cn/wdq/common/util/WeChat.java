package cn.wdq.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WeChat {
    //date测试wxc6bc23b1e8a51cc8，APPSECRET:27956fe56f942c9bbded9dcc4873b23e
    public static final String APPID = "wxc6bc23b1e8a51cc8";//wxe7459d028726a345
    public static final String APPSECRET = "27956fe56f942c9bbded9dcc4873b23e";//e1b23f2cea7627cc1c317436c0e7ad5e
    public static JSONObject deGetJson(String url) throws IOException {
        JSONObject json = new JSONObject();
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            String result = EntityUtils.toString(entity,"UTF-8");
            json = JSONObject.parseObject(result);
        }
        httpGet.releaseConnection();
        return json;
    }
}
