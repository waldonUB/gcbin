package cn.wdq.test;

import org.junit.Test;

import java.io.*;

public class IOTest {
    @Test
    public void ioTest(){
        byte[] buffer=new byte[1024];
        int numberRead=0;
        FileInputStream inputStream=null;
        FileOutputStream outputStream=null;
        try {
            inputStream=new FileInputStream(new File("E:\\images/left.jpg"));
            outputStream=new FileOutputStream("E:\\images/left2.jpg");
            while ((numberRead=inputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,numberRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    //本地
    /*String local_img_base64=img_url.replaceFirst("data:image/gif;base64,","");
    BASE64Decoder decoder=new BASE64Decoder();
    byte[] buffer=new byte[1024];
		try {
        buffer=decoder.decodeBuffer(local_img_base64);
    } catch (IOException e) {
        e.printStackTrace();
    }
    int numberRead=0;
    FileInputStream inputStream=null;
    FileOutputStream outputStream=null;
		try {
        outputStream=new FileOutputStream("E:\\images/left2.gif");
        outputStream.write(buffer);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }finally {
        try {
            //inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
