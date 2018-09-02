package cn.wdq.service;

import cn.wdq.dao.BlogInfoDAOImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogInfoService")
public class BlogInfoServiceImpl implements BlogInfoService {
    @Autowired
    BlogInfoDAOImpl blogInfoDAOImpl;
    @Override
    public int saveQuestion(JSONObject json) {
        return blogInfoDAOImpl.save_question(json);
    }

    @Override
    public List queryBlog(String user_name) {
        return blogInfoDAOImpl.query_blog(user_name);
    }

    @Override
    public List queryComment(int pk_blog) {
        return blogInfoDAOImpl.query_comment(pk_blog);
    }

    @Override
    public int saveComment(JSONObject json) {
        return blogInfoDAOImpl.save_comment(json);
    }

    @Override
    public int savePraise(JSONObject json) {
        int i=0;
        List list=blogInfoDAOImpl.is_praised(json);
        if(list.isEmpty()){
            i=blogInfoDAOImpl.save_praise(json);
        }else{
            i=i-blogInfoDAOImpl.cancel_praised(json);
        }
        return i;
    }
}
