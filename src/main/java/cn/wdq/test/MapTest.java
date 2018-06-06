package cn.wdq.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    @Test
    public void MapToJSON(){

    }
    @Test
    public void mapToJSON2(){
        Map map=new HashMap();
        map.put("user","ss");
        map.put("password","ww");
        String jsonStr= JSON.toJSONString(map);
        JSONObject json= (JSONObject)JSONObject.parse(jsonStr);
        JSONObject json2=JSONObject.parseObject(jsonStr);
        JSONObject json3= (JSONObject) JSON.toJSON(map);
        System.out.println(json);
        System.out.println(json2);
        System.out.println(json3);
    }
}
