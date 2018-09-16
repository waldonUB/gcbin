import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class ForPostTest {
    private static long begin = System.currentTimeMillis();
    public static void main(String[] args) {
        int count = 10000;
        for (int i = 0; i < count; i++) {
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                System.out.println();
                HttpPost request = new HttpPost("http://47.106.190.36:8080/gcbin/edit_password");
                request.setHeader("Content-Type", "application/json");
                JSONObject json = new JSONObject();
                json.put("user_name", "waldon1");
                json.put("password", "1");
                request.setEntity(new StringEntity(json.toJSONString()));
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                System.out.println(result);
                long end = System.currentTimeMillis();
                System.out.println("当前"+i+"耗时："+ (end - begin));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    @Test
    public void getTomcatPath(){
        String path=System.getProperty("catalina.home");
        System.out.println(path);
    }
}
