package cn.wdq.test;

import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    @Test
    public void MapToJSON(){
        Map map=new HashMap();
        JSONObject json=new JSONObject();
        map.put("user",1);
        map.put("password",8);
        System.out.println(map);
        json=JSONObject.fromObject(map.toString());
        System.out.println(json);
    }
}
