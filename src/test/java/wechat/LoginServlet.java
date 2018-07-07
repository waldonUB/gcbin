//package wechat;
//
//import cn.wdq.common.util.WeChat;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLEncoder;
//
//@WebServlet("/wxLogin")
//public class LoginServlet extends HttpServlet{
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String backUrl = "http://21t284120p.imwork.net:15390/PetsCT/callBack";
//        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeChat.APPID+
//                "&redirect_uri="+URLEncoder.encode(backUrl)+
//                "&response_type=code" +
//                "&scope=snsapi_userinfo" +// snsapi_userinfo
//                "&state=STATE#wechat_redirect";
//        resp.sendRedirect(url);
//    }
//}
