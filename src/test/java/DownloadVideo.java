import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadVideo {
    final static private String url = " http://www.yylep.com/site/haokan?id=https://haokan.baidu.com/videoui/page/videoland?pd=haokan_share&context=%7B%22internal_url%22%3A%22http%3A%5C%2F%5C%2Fwww.internal.video.baidu.com%5C%2Fc40b13c5c8cee0819eb2ae00a6a20ed9.html%22%7D";
    final static private String videoUrl = "<video.*src=(.*?)[^>]*?>";
    final static private String videoSrc = "[a-zA-z]+://[^\\s]+[a-zA-z]";

    public static void main(String[] args) {
        DownloadVideo video = new DownloadVideo();
        try {
            String html = video.getHtml(url);
            List<String> urlList = video.getVideoUrl(html);
            List<String> srcList = video.getVideoSrc(urlList);
            video.downloadVideo(srcList);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getHtml(String url) throws MalformedURLException {
        URL conUrl = new URL(url);
        URLConnection connection;
        StringBuffer sb = new StringBuffer();
        try {
            connection = conUrl.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    private List<String> getVideoUrl(String html){
        List<String> list=new ArrayList<>();
        Matcher matcher= Pattern.compile(videoUrl).matcher(html);
        while (matcher.find()){
            list.add(matcher.group());
        }
        return list;
    }
    private List<String> getVideoSrc(List<String> urlList){
        List<String> list = new ArrayList<>();
        for(String lcVideoUrl:urlList){
            Matcher matcher=Pattern.compile(videoSrc).matcher(lcVideoUrl);
            while (matcher.find()){
                list.add(matcher.group());
            }
        }
        return list;
    }
    private void downloadVideo(List<String> srcList) throws IOException {
        Date begin = new Date();
        System.out.println(begin);
        for (String lcVideoSrc:srcList){
            URL srcUrl = new URL("https://vd3.bdstatic.com/mda-ibta8ep7amn1tyhv/mda-ibta8ep7amn1tyhv.mp4?auth_key=1528903013-0-0-8e5c1eeb57327014b24fa8373f7e417e&amp;bcevod_channel=searchbox_feed&amp;pd=haokan_share");
            InputStream in = srcUrl.openStream();
            FileOutputStream fo = new FileOutputStream(new File("C:\\Users\\Stanley\\Desktop\\img\\"+"test.avi"));
            byte[] bytes = new byte[1024];
            int length=0;
            while ((length = in.read(bytes,0,bytes.length))!=-1){
                fo.write(bytes,0,length);
            }
            in.close();
            fo.close();
        }
        Date end = new Date();
        System.out.println(end);
        System.out.println((end.getTime()-begin.getTime())/1000);
    }
}
