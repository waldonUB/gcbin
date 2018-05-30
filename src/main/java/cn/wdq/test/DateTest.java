package cn.wdq.test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    /**
     * 试试github的更新
     * */
    
    @Test
    public void dateTest(){
        String testDate="2015-12-09 00:00:05";
        String formatDate=null;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2=simpleDateFormat1.parse("2012-12-06 00:15:15");
            formatDate= DateFormat.getDateInstance(DateFormat.MEDIUM).format(date2);
            System.out.println("date2:"+formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String datestr=simpleDateFormat.format(new Date());
        Date date=new Date(System.currentTimeMillis());
        System.out.println("date:"+date);
        System.out.println(formatDate);
        System.out.println("datestr:"+datestr);


    }
}
