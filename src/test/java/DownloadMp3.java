import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class DownloadMp3 {
    private static String map3Url="http://m10.music.126.net/20180620160137/ea6a877aa8c2fee0bd1512f4b9d4a8d3/ymusic/03c9/3f47/e891/403f01ebc70bb2077e24b9b37e43dac5.mp3";

    public static void main(String[] args) throws IOException {
        downloadMp3();
    }
    private static void downloadMp3() throws IOException {
        Date begin = new Date();
        System.out.println(begin);
        URL url=new URL(map3Url);
        InputStream in=url.openStream();
        FileOutputStream fo = new FileOutputStream(new File("C:\\Users\\Stanley\\Desktop\\img\\"+"test.mp3"));
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = in.read(bytes,0,bytes.length))!=-1){
            fo.write(bytes,0,length);
        }
        in.close();
        fo.close();
        Date end=new Date();
        System.out.println(end);
        System.out.println((end.getTime()-begin.getTime())/1000);
    }
}
