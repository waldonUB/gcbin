import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class PostTest {

    @Test
    public void postTest() throws IOException {
        HttpClient client = HttpClients.createDefault();
        System.out.println();
        HttpPost request = new HttpPost("http://47.106.190.36:8080/gcbin/login_validate");
        request.setHeader("Content-Type", "application/json");
        JSONObject json = new JSONObject();
        json.put("user_name", "waldon1");
        json.put("password", "1");
        request.setEntity(new StringEntity(json.toJSONString()));
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        System.out.println(result);
    }
    @Test
    public void getTomcatPath(){
        String path=System.getProperty("catalina.home");
        System.out.println(path);
    }
}
