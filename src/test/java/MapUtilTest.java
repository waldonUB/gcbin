import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapUtilTest {
    @Test
    public void mapTest(){
//        MapUtils mapUtils =
        Map<String,Object> map = new HashMap<>();
        map.put("test",new Date());
        String test = MapUtils.getString(map,"test");
    }
    @Test
    public void splitTest(){
        String str = "1|1";
        String str1 = str.split("\\|")[0];
        System.out.println(str1);
    }
    @Test
    public void jsonTest(){
        JSONObject json =new JSONObject();
        json.put("date",new Date());

    }
}
