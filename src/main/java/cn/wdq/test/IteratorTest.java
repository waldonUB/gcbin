package cn.wdq.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    @Test
    public void iteratorTest(){
        List list=new ArrayList();
        list.add("ss");
        list.add("dd");
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){//原先错误的写法，死循环了：list.iterator().hasNext()
            System.out.println(iterator.next());
        }
        System.out.println(list);
    }
}
