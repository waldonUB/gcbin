package collection;

import cn.wdq.entities.UserInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapAndSet {
    @Test
    public void mapTest() {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, 1);
        map.put(1, 5);
    }

    @Test
    public void listTest() {
        List<String> list = new ArrayList<>();
        list.add("a");
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name("a");
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUser_name("a");
    }
}
