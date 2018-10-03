import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest extends ThreadPoolExecutor {
    private static int number = 0;
    private static int count = 20;
    public ThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 7, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        long begin = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            synchronized (executor) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (executor.getMaximumPoolSize()>=7) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(" name_"+number);
                        System.out.println(" ActiveCount: " + executor.getActiveCount());
                        System.out.println(" poolSize: " + executor.getPoolSize());
                        System.out.println(" queueSize: " + executor.getQueue().size());
                        System.out.println(" taskCount: " + executor.getTaskCount());
                        number++;
                    }
                });
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时："+ (end - begin));
    }
}
