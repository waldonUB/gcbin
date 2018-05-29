package cn.wdq.test;

import cn.wdq.entities.ReturnModel;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.*;

public class ToLocal {
    Logger logger=Logger.getLogger(ToLocal.class);
    @Test
    public void toLocalTest(JSONObject json){
        ReturnModel model=new ReturnModel();
        byte[] buffer=new byte[1024];
        BufferedWriter bw = null;
        String img_url=json.getString("base64");
        String[] base_urls=img_url.split(",");
        String base_url=base_urls[1];
        logger.info("base_url的长度为："+base_url.length());
        BASE64Decoder decoder=new BASE64Decoder();
        try {
            buffer=decoder.decodeBuffer(base_url);
            String path="E:\\images/left88.png";
            OutputStream outputStream=new FileOutputStream(path);
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        model.setSuccess(true);
    }

}
