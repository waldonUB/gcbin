import org.junit.Test;

public class ReplaceTest {
    @Test
    public void replaceTest(){
        String str="kkggkkhh";
        str=str.replaceFirst("kk","ww");
        System.out.println(str);
    }
}
