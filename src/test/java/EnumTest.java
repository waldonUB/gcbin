import java.util.ArrayList;
import java.util.List;

public enum EnumTest {
    INSTANCE;
    private List<String> list = null;
    EnumTest(){
        list = new ArrayList<>();
    }
    public List<String> getInstance(){
        return list;
    }
}
