import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class HttpAsyncClient {
    private final static int socketTimeout = 50000; // 等待数据超时时间
    private final static int connectTimeout = 50000; // 连接超时时间
    private final static int connectionRequestTimeout = 1000000; // 连接池连接超时,超时将取消传输
    private final static int poolSize = 1000; // 连接池最大连接数
    private final static int maxPerRoute = 10000; // 每个主机最大并发为1500

    private static long begin = System.currentTimeMillis();
    private static Logger logger = Logger.getLogger(HttpAsyncClient.class);
    private static int count = 10000;
    private static int index = 0;
    public static void main(String[] args) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connectionManager.setMaxTotal(poolSize);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
//        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        HttpPost request = new HttpPost("http://47.106.190.36:8080/gcbin/edit_password");
        request.setHeader("Content-Type", "application/json");
        final CountDownLatch downLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            JSONObject json = new JSONObject();
            json.put("user_name", "waldon1");
            json.put("password", "1");
            request.setEntity(new StringEntity(json.toJSONString()));
            httpclient.execute(request, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse response) {
                    downLatch.countDown();
                    System.out.println();
                    index++;
                    try {
                        HttpEntity entity = response.getEntity();
                        String result = EntityUtils.toString(entity, "utf-8");
                        System.out.println("当前"+downLatch.getCount()+"耗时:"+(System.currentTimeMillis()-begin) + "-" + result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Exception ex) {
                    downLatch.countDown();
                }

                @Override
                public void cancelled() {
                    downLatch.countDown();
                }
            });
        }
        try {
            downLatch.await(100, TimeUnit.SECONDS);
            System.out.println("耗时:"+(System.currentTimeMillis()-begin) + "执行关闭httpclient操作");
            httpclient.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void translateTest() throws UnsupportedEncodingException {
        String map = "{\"id\":\"0098a2bc1759491893dd7ee98c34cfab\",\"house_Id\":\"a9395c5b4128e447a64bc734308acda1\",\"customer_Name\":\"测试two\",\"date_Completion\":\"2018-08-09\",\"follow_Up_Node\":\"首付款\",\"follow_Type\":\"款项\",\"plan_Collection_Sum\":0,\"charge_Sum\":0,\"consultant\":\"钟联惠\",\"del_Flag\":0}";
        JSONObject json = JSON.parseObject(map);
        String urlJson = URLEncoder.encode(json.toJSONString(), "utf-8");
        System.out.println(urlJson);
    }
    @Test
    public void mapString() {
        Map<String, Object> map = new HashMap<>();
        map.put("test", "user");
        JSONObject json = new JSONObject();
        json.put("username", "wudq");
        System.out.println(map.toString());
        System.out.println(json.toJSONString());
    }
    @Test
    public void moreRequest(){
        final RequestConfig requestConfitg = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();

        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfitg)
                .build();

        httpClient.start();

        final HttpGet[] requests = new HttpGet[]{
                new HttpGet("http://www.apache.org/"),
                new HttpGet("http://www.baidu.com/"),
                new HttpGet("http://www.oschina.net/")
        };

        final CountDownLatch latch = new CountDownLatch(requests.length);
        for(final HttpGet request: requests){

            httpClient.execute(request, new FutureCallback(){
                @Override
                public void completed(Object obj) {
                    final HttpResponse response = (HttpResponse)obj;
                    latch.countDown();
                    System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                }

                @Override
                public void failed(Exception excptn) {
                    latch.countDown();
                    System.out.println(request.getRequestLine() + "->" + excptn);
                }

                @Override
                public void cancelled() {
                    latch.countDown();
                    System.out.println(request.getRequestLine() + "cancelled");
                }
            });
        }
        try {
            latch.await();
            System.out.println("Shutting Down");
        } catch (InterruptedException ex) {
        }finally{
            try {
                httpClient.close();
            } catch (IOException ex) {
            }
        }
        System.out.println("Finish!");
    }
}
