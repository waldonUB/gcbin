import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class TranslateBig {
    @Test
    public void translateBig(){
        double d = 123456789.8;
        Map<String,Object> map = new HashMap<>();
        BigDecimal bigDecimal = new BigDecimal("1.01761E7");
        bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        map.put("big",bigDecimal);
        String jsonStr = JSONObject.toJSONString(map);
        System.out.println(bigDecimal);
    }
    @Test
    public void listTest() {
        Map<String,Object> map = new HashMap<>(16);
        Date date = DateUtil.parseYYYYMMDDDate("2018/09/02");
        System.out.println(date);
    }
}
