//package wechat;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClientBuilder;
//
//
//import java.io.IOException;
//
//public class AuthUtil {
//    public static final String APPID = "wxe7459d028726a345";
//    public static final String APPSECRET = "e1b23f2cea7627cc1c317436c0e7ad5e";
//    public static JSONObject deGetJson(String url) throws IOException {
//        JSONObject json = new JSONObject();
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet httpGet = new HttpGet(url);
////        HttpResponse response = client.execute(httpGet);
////        HttpEntity entity = response.getEntity();
////        if(entity!=null){
////            String result = EntityUtils.toString(entity,"UTF-8");
////            json = JSONObject.parseObject(result);
////        }
////        httpGet.releaseConnection();
//        return json;
//    }
//}
