package base;

public class Father {
    {
        age = 14;
        System.out.println("父类非静态块");
        fatherMethod();
    }
    int age = 10;
    public Father() {
        System.out.println("父类构造方法");
        System.out.println("age:" + age);
    }

    void fatherMethod() {
        System.out.println("fatherMethod");
    }
}
