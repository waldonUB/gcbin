package cn.wdq.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RunnableTest {

    @Test
    public void runnableTest(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程执行任务，当前时间："+new SimpleDateFormat("yyyy-MM--dd HH:mm:ss").format(new Date()));
            }
        };
        try {
            System.out.println("主线程启动子线程时间："+new SimpleDateFormat("yyyy-MM--dd HH:mm:ss").format(new Date()));
            scheduleThread(5L,3,runnable);
        }catch (Exception e){

        }
    }
    static void scheduleThread(Long duration,Integer timeInterval,Runnable runnable) throws InterruptedException{
        TimeUnit.SECONDS.sleep(duration);

        final Runnable interiorRun=runnable;
        final Integer interiorTimeInterval=timeInterval;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    interiorRun.run();
                    try {
                        TimeUnit.SECONDS.sleep(interiorTimeInterval);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
    public static void main(String[] args) {
        int b=1;
        int i=b;
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程执行任务，当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                System.out.println(i);
            }
        };
        try {
            System.out.println("主线程启动子线程时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            scheduleThread(5L, 3, runable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
