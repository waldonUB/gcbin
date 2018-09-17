package cn.wdq.controller;

import cn.wdq.common.user.GetUserInfo;
import cn.wdq.common.util.GetIpArea;
import cn.wdq.common.util.WeChat;
import cn.wdq.entities.ReturnModel;
import cn.wdq.entities.UserInfo;
import cn.wdq.mapping.CommonDAO;
import cn.wdq.service.LoginService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 登录注册及部分个性化功能的实现
 * @author waldon
 * */
@Controller
@RequestMapping
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    CommonDAO commonDAO;
//    @Autowired
//    private CloseableHttpClient httpClient;
static{
    System.out.println("LoginController");
}

    /**
     * 登陆验证
     * @param userInfo include:1.user_name 用户名 2.password 密码
     * @return 登录用户个人信息
     */
    @RequestMapping("/login_validate")
    @ResponseBody
    public ReturnModel loginValidate(@RequestBody UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        ReturnModel model = new ReturnModel();
        GetIpArea getIpArea=new GetIpArea();
        String user_name = userInfo.getUser_name();
        String password = userInfo.getPassword();
        if (user_name == null || password == null || user_name.trim().length() < 1 || password.trim().length() < 1) {
            model.setSuccess(false);
            model.setMessage("用户名或者密码为空");
            logger.info("method(login_validate):用户名或者密码为空");
            return model;
        }
        List list = loginService.login(userInfo);
        if(GetUserInfo.get()==null){
            GetUserInfo.set(userInfo);
        }
        if(!list.isEmpty()){
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            JSONObject json_info = (JSONObject) JSON.toJSON(list.get(0));//历史脏数据存在多个用户同名的情况
            String ipArea=getIpArea.getIpArea(ip).getString("ipArea");
            json_info.put("ip", ip);
            json_info.put("is_online", 1);
            json_info.put("ip_area",ipArea);
            userInfo.setHead_img(json_info.getString("head_img"));
            userInfo.setIs_online(1);
            userInfo.setUser_type(json_info.getInteger("user_type"));
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String last_time=simpleDateFormat.format(new Date());
            userInfo.setLast_time(last_time);
            userInfo.setDistrict(json_info.getString("district"));
            userInfo.setIp(ip);
            userInfo.setIp_area(ipArea);
            request.getSession().setAttribute("sessionKey", json_info.toString());//把值放到session里面
            loginService.forceLogout(request, user_name);//强制挤下用户
            loginService.insertLoginInfo(userInfo,request);//把在线用户数据插入login_info表
            loginService.addLoginHistory(userInfo);//把登录用户插入用户登录历史表
            loginService.updateLastTime(user_name);//更新最近登录时间
            model.setData(json_info);
            model.setSuccess(true);
        }else{
            model.setSuccess(false);
            model.setMessage("用户名或密码错误");
            logger.info("method(login_validate):用户名或密码错误");
        }
        return model;
    }

    /**
     * 注册新用户
     * @param json include:user_name
     * @return 是否注册成功的状态
     */
    @RequestMapping("/register")
    @ResponseBody
    public ReturnModel registerUser(@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        int has_same = loginService.haveSame(json);
        if (has_same==0) {//这里true代表没有重名的用户
            model.setSuccess(true);
            loginService.register(json);
        }else{
            model.setSuccess(false);
            model.setMessage("该用户已存在");
            logger.info("method(register):该用户已存在");
        }
        return model;
    }

    /**
     * 注销登陆
     * @param json include:user_name 用户名
     * @return 是否注销成功的状态
     */
    @RequestMapping("/login_out")
    @ResponseBody
    public ReturnModel loginOut(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws RuntimeException, SQLException {
        UserInfo userInfo = GetUserInfo.get();
        ReturnModel model = new ReturnModel();
        String user_name = json.getString("user_name");
        HttpSession session = request.getSession();
        String sessionKey = request.getSession().getAttribute("sessionKey").toString();
        if (sessionKey != null) {
//            loginService.autoLogout(user_name);//主动注销
            session.invalidate();//可以触发Session的监听事件
            model.setSuccess(true);
            model.setMessage("注销成功");
            return model;
        } else {
            model.setSuccess(false);
            model.setMessage("注销失败");
            return model;
        }
    }

    /**
     * 查询当前用户信息
     * @return 当前登录用户个人信息
     */
    @RequestMapping("/query_userinfo")
    @ResponseBody
    public ReturnModel getUserInfo(HttpServletRequest request) {
        ReturnModel model = new ReturnModel();
        String string_info = null;
        if (request.getSession().getAttribute("sessionKey") != null) {
            string_info = request.getSession().getAttribute("sessionKey").toString();
        }
        JSONObject json_info = JSON.parseObject(string_info);
        model.setSuccess(true);
        model.setData(json_info);
        return model;
    }

    /**
     * 修改密码
     * @param json include:1.user_name 用户名 2.password 密码
     * @return 修改密码是否成功的状态
     */
    @RequestMapping("/edit_password")
    @ResponseBody
    public ReturnModel updatePassword(@RequestBody JSONObject json) throws SQLException {
        ReturnModel model = new ReturnModel();
        String user_name = json.getString("user_name");
        String password = json.getString("password");
        loginService.editPassword(user_name, password);
        model.setSuccess(true);
        model.setMessage("修改密码成功");
        return model;
    }

    /**
     * 上传头像
     * @param json include:1.img_url 前端传回来的图片base64码 2.user_name 当前登录的用户
     * @return 修改是否成功的状态
     */
    @RequestMapping("/head_portrait")
    @ResponseBody
    public ReturnModel modifyHeadPortrait(@RequestBody JSONObject json) throws SQLException {
        ReturnModel model = new ReturnModel();
        String img_url = json.getString("base64");//带格式的图片
        String user_name = json.getString("user_name");
//		String suffix=json.getString("suffix");//图片后缀名
//		img_url=img_url.replaceFirst("png",suffix);//在后台直接替换后缀名，前台canvas的方式暂时先不动
        logger.info("base_url的长度为：" + img_url.length());
        if (img_url.length() >= 130000) {//限制一下图片的大小
            model.setSuccess(false);
            model.setMessage("太大了...");
            return model;
        } else {
            loginService.setHeadImg(img_url, user_name);
            model.setSuccess(true);
            model.setData(img_url);
            return model;
        }
    }

    /**
     * 前端定时任务，查session信息
     * @param json include:1.cuserid 用户主键
     * @return 用户是否在线的状态
     */
    @RequestMapping("/query_session")
    @ResponseBody
    public ReturnModel querySession(@RequestBody JSONObject json, HttpServletRequest request) {
        ReturnModel model = new ReturnModel();
        String cuserid = request.getSession().getId();
        if (cuserid == null) {
            model.setSuccess(false);
            model.setMessage("会话失效，请重新登录");
        } else {
            int onlineNum=commonDAO.loginInterceptor(cuserid);
            if(onlineNum>0){
                model.setSuccess(true);
            }else{
                model.setSuccess(false);
                model.setMessage("账号在其他处登录");
            }
        }
        return model;
    }
    @RequestMapping("/query_history")
    @ResponseBody
    public ReturnModel queryHistory(@RequestBody JSONObject json){
        ReturnModel model=new ReturnModel();
        int item=json.getInteger("item");
        int pageCount=loginService.queryCountHis();
        if(pageCount%item==0){
            pageCount=pageCount/item;
        }else{
            pageCount=(pageCount/item)+1;
        }
        List<UserInfo> list=loginService.queryLogHis(json);
        model.setSuccess(true);
        model.setData(list);
        model.setStatus(pageCount);
        return model;
    }
    @RequestMapping("/query_locate")
    @ResponseBody
    public ReturnModel queryLocate(HttpServletRequest request) throws IOException {
        ReturnModel model=new ReturnModel();
        GetIpArea ipArea=new GetIpArea();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String city=ipArea.getIpArea(ip).getString("city");
        model.setData(city);
        return model;
    }
    @RequestMapping("/wxLogin")
    public void wxLogin(HttpServletResponse response) throws IOException {
        String backUrl = "http://21t284120p.imwork.net:15390/gcbin/callBack";
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeChat.APPID+
                "&redirect_uri="+URLEncoder.encode(backUrl,"UTF-8")+
                "&response_type=code"+
                "&scope=snsapi_userinfo"+// snsapi_userinfo
                "&state=STATE#wechat_redirect";
        response.sendRedirect(url);
    }
    @RequestMapping("/callBack")
    public void callBack(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +WeChat.APPID+
                "&secret=" +WeChat.APPSECRET+
                "&code=" +code+
                "&grant_type=authorization_code";
        JSONObject json = WeChat.deGetJson(url);
        String openid = json.getString("openid");
        String token = json.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" +token+
                "&openid=" +openid+
                "&lang=zh_CN";
        JSONObject userInfo = WeChat.deGetJson(infoUrl);
        //没有账号就注册

        System.out.println(userInfo);
    }
    @RequestMapping("/getSessionKey")
    @ResponseBody
    public ReturnModel getSessionKey(@RequestBody JSONObject json) throws IOException {
        String appId = "wx6552e76407aa28f8";
        String appSecret = "fb9246a289ab43d06b41f71b69427be0";
        String code = json.getString("code");//wx.login获取
        String getKeyUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"" +
                "&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
        JSONObject jsonKey = WeChat.deGetJson(getKeyUrl);
        ReturnModel model = new ReturnModel();
        return model;
    }
    @RequestMapping("/upload_test")
    @ResponseBody
    public ReturnModel uploadTest(@RequestParam("files") MultipartFile[] files, @RequestParam("testData") String testData) throws IOException {
        ReturnModel model=new ReturnModel();
        MultipartFile file = files[0];
        JSONObject jsonObject = JSONObject.parseObject(testData);
        POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
        HSSFWorkbook hw = new HSSFWorkbook(fs);
        HSSFSheet sheet = hw.getSheetAt(0);//只针对单页的情况
        List<Map<String,Object>> list = new ArrayList<>();
        HSSFRow rowTitle = sheet.getRow(0);//标题行
        int colNum = rowTitle.getPhysicalNumberOfCells();//总列数
        //排除标题从第一行开始
        for (int i =1; i<=sheet.getLastRowNum(); i++){
            HSSFRow row = sheet.getRow(i);
            if(row!=null){
                Map<String,Object> map = new HashMap<>();//不能用clear，add时会指向同一个内存地址
                for (int j = 0;j < colNum; j++){
                    if(row.getCell(j)!=null){
                        map.put(rowTitle.getCell(j).getStringCellValue(),row.getCell(j).getStringCellValue());
                    }else{
                        map.put(rowTitle.getCell(j).getStringCellValue(),null);
                    }
                }
                list.add(map);
            }
        }
        if(!list.isEmpty()){
            model.setSuccess(true);
            model.setData(list);
        }else {
            model.setSuccess(false);
            model.setMessage("导入失败");
        }
        return model;
    }
}
