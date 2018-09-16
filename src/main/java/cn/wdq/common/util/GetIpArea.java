package cn.wdq.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取登录用户的ip归属地
 * ip接口为阿里巴巴
 * @author waldon
 * */
public class GetIpArea {
    private Logger logger=Logger.getLogger(GetIpArea.class);
    /**
     * 获取登录用户的ip归属地
     * @param ip 用户登录ip
     * @return ip归属地的数据
     * */
    public JSONObject getIpArea(String ip) throws IOException {
        BufferedReader reader=null;
        String ipArea;
        JSONObject areaInfo=new JSONObject();
        try {
            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
            URLConnection connection=url.openConnection();
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            StringBuilder result=new StringBuilder();
            while ((line=reader.readLine())!=null){
                result.append(line);
            }
            if (StringUtils.isEmpty(result.toString())) {
                ipArea="局域网";
                areaInfo.put("ipArea",ipArea);
                areaInfo.put("city","获取失败");
                return areaInfo;
            }
            JSONObject json= JSON.parseObject(result.toString());
            JSONObject ipInfo= (JSONObject) json.get("data");
            String country=ipInfo.getString("country");
            if(country.equals("XX")){
                ipArea="局域网";
                areaInfo.put("ipArea",ipArea);
                areaInfo.put("city","获取失败");
            }else{
                String region=ipInfo.getString("region");
                String city=ipInfo.getString("city");
                String isp=ipInfo.getString("isp");
                ipArea=country+region+city+isp;
                areaInfo.put("ipArea",ipArea);
                areaInfo.put("city",city);
            }
        } catch (IOException e) {
            logger.error("method(getIpArea) 读取用户ip信息失败："+e);
        }catch (JSONException e){
            ipArea="网络故障,无法获取";
            areaInfo.put("ipArea",ipArea);
            areaInfo.put("city","获取失败");
        }finally {
            if (reader != null) {
                reader.close();
            }
        }
        return areaInfo;
    }
}
