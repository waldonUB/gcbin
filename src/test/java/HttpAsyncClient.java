import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

public class HttpAsyncClient {
    private static long begin = System.currentTimeMillis();
    private static Logger logger = Logger.getLogger(HttpAsyncClient.class);
    private static int count = 10000;
    private static int index = 0;
    public static void main(String[] args) throws IOException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        HttpPost request = new HttpPost("http://47.106.190.36:8080/gcbin/edit_password");
        request.setHeader("Content-Type", "application/json");
        for (int i = 0; i < count; i++) {
            JSONObject json = new JSONObject();
            json.put("user_name", "waldon1");
            json.put("password", "1");
            request.setEntity(new StringEntity(json.toJSONString()));
            httpclient.execute(request, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse response) {
                    System.out.println();
                    index++;
                    logger.info("当前数值："+index);
                    try {
                        HttpEntity entity = response.getEntity();
                        String result = EntityUtils.toString(entity, "utf-8");
                        System.out.println(result);
                        System.out.println();
                        if (index == (count-1)) {
                            httpclient.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前"+index+"耗时:"+(System.currentTimeMillis()-begin));
                }

                @Override
                public void failed(Exception ex) {
                    try {
                        httpclient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void cancelled() {
                    try {
                        httpclient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
