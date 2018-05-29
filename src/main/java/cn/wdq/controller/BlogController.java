package cn.wdq.controller;

import cn.wdq.entities.ReturnModel;
import cn.wdq.service.BlogInfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BlogController {
    //@Autowired
    @Autowired()
    @Qualifier("blogInfoService")//括号要不要都得
            //@Resource(name="blogInfoService")//试试@Resource的用法而已
            BlogInfoService blogInfoService;

    @RequestMapping("/save_question")
    @ResponseBody
    public ReturnModel saveQuestion(@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        int i = blogInfoService.saveQuestion(json);
        if (i == 0) {
            model.setSuccess(false);
        } else {
            model.setSuccess(true);
        }
        return model;
    }

    @RequestMapping("/query_blog")
    @ResponseBody
    public ReturnModel queryBlog(HttpServletRequest request) {
        String user_name=null;
        String session_string=null;
        JSONObject session_json=null;
        if(request.getSession().getAttribute("sessionKey")!=null){
            session_string=(String)request.getSession().getAttribute("sessionKey");
            session_json=JSONObject.parseObject(session_string);
            user_name=session_json.getString("user_name");
        }
        ReturnModel model = new ReturnModel();
        List list = blogInfoService.queryBlog(user_name);
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    @RequestMapping("/query_comment")
    @ResponseBody
    public ReturnModel queryComment(@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        int pk_blog = json.getInteger("pk_blog");
        List list = blogInfoService.queryComment(pk_blog);
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    @RequestMapping("/save_comment")
    @ResponseBody
    public ReturnModel saveComment(@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        int i = blogInfoService.saveComment(json);
        if (i != 0) {
            model.setSuccess(true);
            model.setMessage("发布评论成功");
        }
        return model;
    }
    @RequestMapping("/save_praise")
    @ResponseBody
    public ReturnModel savePraise(@RequestBody JSONObject json){
        ReturnModel model=new ReturnModel();
        int i=blogInfoService.savePraise(json);
        if (i>0){
            model.setSuccess(true);
            model.setMessage(json.getString("user_name")+"已赞");
        }else{
            model.setSuccess(false);
            model.setMessage(json.getString("user_name")+"已取消赞");
        }
        return model;
    }
}
