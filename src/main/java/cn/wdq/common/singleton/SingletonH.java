package cn.wdq.common.singleton;

import java.util.ArrayList;
import java.util.List;

public class SingletonH {
    private static final List<String> list = new ArrayList<>();
    private SingletonH(){

    }
    public static List<String> getInstance(){
        return list;
    }
}
