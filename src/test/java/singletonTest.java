import java.util.ArrayList;
import java.util.List;

public class singletonTest {
    private static List<String> list = null;
    private singletonTest(){

    }
    public static List<String> getSingleton(){
        if(list.isEmpty()){
            list = new ArrayList<>();
        }
        return list;
    }
}
