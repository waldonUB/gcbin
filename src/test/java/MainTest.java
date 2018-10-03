public class MainTest {
    public static void main(String[] args) {
        J8Test j8Test = new J8Test();
        int sum = j8Test.plus(6,  8);
        int count = 1000000;
        long begin = System.currentTimeMillis();
        for (int i = 0; i <count; i++) {
            System.out.println(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时："+ (end - begin));
    }
}
