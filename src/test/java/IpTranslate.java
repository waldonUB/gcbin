import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpTranslate {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=null;
        String ipArea;
        try{
            URL url=new URL("http://ip.taobao.com/service/getIpInfo.php?ip=219.128.252.122");
            URLConnection connection=url.openConnection();
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            StringBuilder result=new StringBuilder();
            while ((line=reader.readLine())!=null){
                result.append(line);
            }
            System.out.println();
            JSONObject json= JSON.parseObject(result.toString());
            JSONObject ipInfo= (JSONObject) json.get("data");
            String country=ipInfo.getString("country");
            if(country.equals("XX")){
                country="局域网";
                ipArea=country;
                System.out.println(ipArea);
            }else{
                String region=ipInfo.getString("region");
                String city=ipInfo.getString("city");
                String isp=ipInfo.getString("isp");
                ipArea=country+region+city+isp;
                System.out.println(ipArea);
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            ipArea="网络故障,无法获取";
            System.out.println(ipArea);
        }finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
