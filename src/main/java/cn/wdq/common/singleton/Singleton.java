package cn.wdq.common.singleton;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    static{
        System.out.println("测试未被扫描的静态块");
    }
    private static List<String> list = null;

    private Singleton(){

    }

    public static List<String> getSingleList(){
        if(list==null||list.isEmpty()){
            list = new ArrayList<>();
        }
        return list;
    }
}
