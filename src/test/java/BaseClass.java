import base.Son;
import org.junit.Test;

class Price {
    final static Price INSTANCE = new Price(10); // 先执行静态变量的赋值,在这里实例化Price,跳到构造方法,此时static initNum还没有值
    final static int ADD_NUM = add(20);
    final static int initNum = 2;
    int secondNum;
    public Price(int thirdNum) {
        secondNum = initNum - thirdNum;
    }
    public static int add(int fourNum){
        return initNum + fourNum;
    }
}
public class BaseClass {
    // 静态变量和静态代码块的执行顺序取决于出现顺序,非静态的也是一样,然后到构造函数
    static { // 静态的东西只执行一次
        name = "wudeqin";
    }
    {
        age = 14;
    }
    int age = 10;
    static String name = "waldon"; // 初始化执行顺序是一样的,先在堆中赋值为wudeqin,后赋值为waldon
    @Test
    public void printTest() {
        System.out.println(name);
    }

    @Test
    public void sortTest() {
        System.out.println(Price.ADD_NUM);
//        Price price = new Price(5);
//        System.out.println(price.secondNum);
    }

    @Test
    public void extendsTest() {
        Son son = new Son();
    }
}
