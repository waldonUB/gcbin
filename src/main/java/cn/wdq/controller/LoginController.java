package cn.wdq.controller;

import cn.wdq.entities.ReturnModel;
import cn.wdq.entities.UserInfo;
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

    /**
     * 登陆验证
     * @param userInfo include:1.user_name 用户名 2.password 密码
     * @return 登录用户个人信息
     */
    @RequestMapping("/login_validate")
    @ResponseBody
    public ReturnModel loginValidate(@RequestBody UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ReturnModel model = new ReturnModel();
        String user_name = userInfo.getUser_name();
        String password = userInfo.getPassword();
        if (user_name == null || password == null || user_name.trim().length() < 1 || password.trim().length() < 1) {
            model.setSuccess(false);
            model.setMessage("用户名或者密码为空");
            logger.info("method(login_validate):用户名或者密码为空");
            return model;
        }
        List list = loginService.login(userInfo);
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
            json_info.put("ip", ip);
            json_info.put("is_online", 1);
            request.getSession().setAttribute("sessionKey", json_info.toString());//把值放到session里面
            loginService.forceLogout(request, response, user_name);//强制挤下用户
            loginService.insertLoginInfo(request, response, user_name);//把在线用户数据插入login_info表
            loginService.updateLasttime(user_name);//更新最近登录时间
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
        boolean has_same = loginService.has_same(json);
        if (has_same) {//这里true代表没有重名的用户
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
        ReturnModel model = new ReturnModel();
        String user_name = json.getString("user_name");
        HttpSession session = request.getSession();
        String sessionKey = request.getSession().getAttribute("sessionKey").toString();
        if (sessionKey != null) {
            loginService.autoLogout(user_name);//主动注销
            loginService.deleteLoginInfo(request, response, user_name, 0);
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
     * @param json include:user_name 用户名
     * @return 当前登录用户个人信息
     */
    @RequestMapping("/query_userinfo")
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
        String cuserid = json.getString("cuserid");//用cuserid来判断是不是被挤下来的
        String session = (String) request.getSession().getAttribute("sessionKey");
        if (session == null) {
            model.setSuccess(false);
            model.setMessage("会话失效，请重新登录");
        } else {
            model.setSuccess(true);
        }
        return model;
    }
}
