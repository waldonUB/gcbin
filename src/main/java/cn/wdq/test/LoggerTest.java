package cn.wdq.test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class LoggerTest {
    Logger logger= Logger.getLogger(LoggerTest.class);
    @Test
    public void printLogger(){
        logger.error("直接输出吧");
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
