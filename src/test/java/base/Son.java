package base;

public class Son extends Father{
    int age = 10;
    {
        age = 14;
        System.out.println("子类非静态块");
        sonMethod();
    }
    public Son() {
        System.out.println("子类构造方法");
        System.out.println("age:" + age);
    }

    void sonMethod() {
        System.out.println("sonMethod");
    }
}
