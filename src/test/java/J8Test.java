import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

public class J8Test implements DefaultImpl {
    @Test
    public void randomTest() {
        Random random = new Random();
        System.out.println("random:" + random);
        int value = random.nextInt(10);
        System.out.println("value：" + value);
    }
    @Test
    public void instantTest() {
        Instant instant = Instant.now();
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(instant);
    }

    @Test
    public void jsonTest() {
        String brand = "[{brand: \"666\"}]";
        JSONArray jsonArray = JSONArray.parseArray(brand);
    }

    @Test
    public void stringTest() {
        String str = "1";
        String str2 = "6";
        stringMuti(str, str, str2);
    }

    public void stringMuti(String... strings) { // 其实是个String[]
        String[] strings1 = strings;
        System.out.println(strings);
    }
    @Override
    public int foo() {
        return 0;
    }
}
