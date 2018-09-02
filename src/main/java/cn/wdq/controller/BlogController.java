package cn.wdq.controller;

import cn.wdq.entities.ReturnModel;
import cn.wdq.service.BlogInfoService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 话题界面数据的增删改查
 * @author waldon
 * */

@Controller
public class BlogController {
    private Logger logger = Logger.getLogger(BlogController.class);
    @Autowired()//括号要不要都得
    @Qualifier("blogInfoService")
    //@Resource(name="blogInfoService")
    private BlogInfoService blogInfoService;

    /**
     * 保存用户提出的话题信息
     * @param json
     * @return 返回保存是否成功的状态
     * */
    @RequestMapping("/save_question")
    @ResponseBody
    public ReturnModel saveQuestion(@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        int i = blogInfoService.saveQuestion(json);
        if (i < 1) {
            model.setSuccess(false);
            logger.info("method(save_question):保存失败");
        } else {
            model.setSuccess(true);
        }
        return model;
    }

    /**
     * 查询当前登录用户涉及到的话题信息，包括自己点赞过的话题
     * @param request 用于查询当前登录用户的user_name
     * @return 所有话题信息集合
     * */
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

    /**
     * 查询当前登录用户涉及到的话题信息，包括自己点赞过的话题
     * @param json include:1.pk_blog 话题主键
     * @return 该话题下所有评论信息集合
     * */
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

    /**
     * 查询当前登录用户涉及到的话题信息，包括自己点赞过的话题
     * @param json include:1.blog_title 话题的标题 2.blog_content:话题内容
     * @return 保存话题是否成功的状态
     * */
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

    /**
     * 查询当前登录用户涉及到的话题信息，包括自己点赞过的话题
     * @param json include:1.user_name 用户名
     * @return 保存给话题点赞是否成功的状态
     * */
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
