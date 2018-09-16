import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncFixedThreadPoolTest {
    private static long begin = System.currentTimeMillis();
    private static Logger logger = Logger.getLogger(AsyncFixedThreadPoolTest.class);
    private static int count = 10000;
    private static int index = 0;
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        HttpPost request = new HttpPost("http://47.106.190.36:8080/gcbin/edit_password");
        request.setHeader("Content-Type", "application/json");
        for (int i = 0; i < count; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    JSONObject json = new JSONObject();
                    json.put("user_name", "waldon1");
                    json.put("password", "1");
                    try {
                        request.setEntity(new StringEntity(json.toJSONString()));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    httpclient.execute(request, new FutureCallback<HttpResponse>() {
                        @Override
                        public void completed(HttpResponse response) {
                            index++;
                            logger.info("当前数值："+index);
                            try {
                                HttpEntity entity = response.getEntity();
                                String result = EntityUtils.toString(entity, "utf-8");
                                System.out.println(result);
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
            });
        }
//        long end = System.currentTimeMillis();
//        System.out.println("耗时："+ (end - begin));
    }
    @Test
    public void sum() {
        int num = Runtime.getRuntime().availableProcessors();
        System.out.println(num);
    }
}
