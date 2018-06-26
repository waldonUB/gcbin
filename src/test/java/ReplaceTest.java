import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReplaceTest {
    @Test
    public void replaceTest(){
        String str="kkggkkhh";
        str=str.replaceFirst("kk","ww");
        System.out.println(str);
    }
    @Test
    public void listTest(){
        List<String> list=new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        List<String> list2=new ArrayList<>();
        list2.add("C");
        list2.add("B");
        list2.add("E");
        list2.add("F");
        List<String> list3=list;
        System.out.println(list3);
        list.removeAll(list2);
        System.out.println(list3);
    }
}
