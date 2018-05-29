package cn.wdq.controller;

import cn.wdq.entities.ReturnModel;
import cn.wdq.service.LoginService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping
public class LoginController {
    Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    LoginService login;

    /**
     * 登陆验证
     */
    @RequestMapping("/loginvalidate")
    @ResponseBody
    public ReturnModel gotos(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        ReturnModel model = new ReturnModel();
        String user_name = json.getString("user_name");
        String password = json.getString("password");
        //int page=json.getIntValue("page");
        List list = login.getAllUser();
        login.forceLogout(request, response, user_name);//强制挤下用户
        if (user_name == null || password == null || user_name.trim().length() < 1 || password.trim().length() < 1) {
            model.setSuccess(false);
            model.setMessage("用户名或者密码为空");
            return model;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(user_name) && list.get(i).toString().contains(password)) {
                Map map = new HashMap();
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
                JSONObject json_info = new JSONObject();
                json_info = JSONObject.parseObject(list.get(i).toString());
//				json_info=JSON.parseObject(list.get(i).toString());
                json_info.put("ip", ip);
                json_info.put("is_online", 1);
                map = (Map) json_info;
                if (map.get("user_name") != null && map.get("password") != null) {
                    String mapname = map.get("user_name").toString();
                    String mappassword = map.get("password").toString();
                    if (user_name.trim().equals(mapname) && password.trim().equals(mappassword)) {
                        request.getSession().setAttribute("sessionKey", map.toString());//把值放到session里面
                        //Cookie cookie=new Cookie(request.getSession().getId(),user_name);//把登录的user_name放到cookie里面
                        //String cookieValue=cookie.getValue();//在拦截器里，setMaxAge(0);,立即删除cookie（获取不到sessionID...）看看只保存一个cookie得不得
                        login.insertLoginInfo(request, response, user_name);//把在线用户数据插入login_info表
                        login.updateLasttime(user_name);//更新最近登录时间
                        //String str=(String) request.getSession().getAttribute("sessionKey");
                        model.setData(map);
                        model.setSuccess(true);
                        return model;
                    }
                }

            }
        }
        model.setSuccess(false);
        model.setMessage("用户名或密码错误");
        return model;
    }

    /**
     * 注册新用户
     */
    @RequestMapping("/register")
    @ResponseBody
    public ReturnModel registerUser(@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        boolean has_same = login.has_same(json);
        if (has_same) {//这里true代表返回结果正确
            model.setSuccess(true);
            login.register(json);
        }
        return model;
    }

    /**
     * 注销登陆
     */
    @RequestMapping("/loginout")
    @ResponseBody
    public ReturnModel queryUser(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws RuntimeException, SQLException {
        ReturnModel model = new ReturnModel();
        String user_name = json.getString("user_name");
        HttpSession session = request.getSession();
        String sessionKey = request.getSession().getAttribute("sessionKey").toString();
        if (sessionKey != null) {
            //request.getSession().setAttribute("sessionKey", null);
            login.autoLogout(user_name);//主动注销
            login.deleteLoginInfo(request, response, user_name, 0);
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
     */
    @RequestMapping("/queryuserinfo")
    @ResponseBody
    public ReturnModel getUserInfo(@RequestBody JSONObject json, HttpServletRequest request) {
        ReturnModel model = new ReturnModel();
        String string_info = null;
        if (request.getSession().getAttribute("sessionKey") != null) {
            string_info = request.getSession().getAttribute("sessionKey").toString();
        }
        JSONObject json_info = JSON.parseObject(string_info);
        if (json.getString("head_img") != null) {
            String head_img = json.getString("head_img");
            json_info.put("head_img", head_img);
            request.getSession().setAttribute("sessionKey", json_info.toString());
        }
        model.setSuccess(true);
        model.setData(json_info);
        return model;
    }

    /**
     * 修改密码
     */
    @RequestMapping("/editpassword")
    @ResponseBody
    public ReturnModel updatePassword(@RequestBody JSONObject json) throws SQLException {
        ReturnModel model = new ReturnModel();
        String user_name = json.getString("user_name");
        String password = json.getString("password");
        login.editPassword(user_name, password);
        model.setSuccess(true);
        model.setMessage("修改密码成功");
        return model;
    }

    /**
     * 上传头像
     *
     * @param json 前端传回来的图片base64码
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
        if (img_url.length() >= 130000) {
            model.setSuccess(false);
            model.setMessage("太大了...");
            return model;
        } else {
            login.setHeadImg(img_url, user_name);
            model.setSuccess(true);
            model.setData(img_url);
            return model;
        }
    }

    /**
     * 定时任务，查session信息
     */
    @RequestMapping("/query_session")
    @ResponseBody
    public ReturnModel querySession(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) {
        ReturnModel model = new ReturnModel();
        String cuserid = json.getString("cuserid");//用这个来判断是不是被挤下来的
        String session = (String) request.getSession().getAttribute("sessionKey");
        if (session == null) {
            model.setSuccess(false);
            model.setMessage("会话失效，请重新登录");
        } else {
            model.setSuccess(true);
        }
        return model;
    }

    /**
     * 多线程，每10分钟更新一次用户最近登录时间
     */
    @RequestMapping("/update_lasttime")
    @ResponseBody
    public ReturnModel updateTime(@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
				/*while (true){
					try {
						SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String last_time=simpleDateFormat.format(new Date());
						Thread.sleep(30000);
						System.out.println("当前时间："+last_time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}*/
            }
        });
        thread.start();
        return model;
    }
}
