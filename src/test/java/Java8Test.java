import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

public class Java8Test {
    @Test
    public void streamTest(){
        String day= String.valueOf(EnumModel.Monday);
        System.out.println(day);
    }
    @Test
    public void httpClientTest() throws IOException {
        String url= "https://www.baidu.com";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response1 = client.execute(httpGet);
        HttpEntity entity = response1.getEntity();
        System.out.println();
    }
    @Test
    public void getJSON(){
        String jsonString = "{username:1}";
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println(jsonObject);
    }
}
