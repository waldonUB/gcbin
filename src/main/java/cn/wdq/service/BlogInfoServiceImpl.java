package cn.wdq.service;

import cn.wdq.dao.BlogInfoDAOImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogInfoService")
public class BlogInfoServiceImpl implements BlogInfoService {
    static BlogInfoDAOImpl blogInfoDAOImpl;
    static{
        System.out.println("进入了blog_static模块");
        ApplicationContext cxt=new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        //getBean后面的blogInfoDAOImpl为xml里面配置的bean的id
        blogInfoDAOImpl=cxt.getBean("blogInfoDAOImpl",BlogInfoDAOImpl.class);
    }
    @Override
    public int saveQuestion(JSONObject json) {
        int i=blogInfoDAOImpl.save_question(json);
        return i;
    }

    @Override
    public List queryBlog(String user_name) {
        List list=blogInfoDAOImpl.query_blog(user_name);
        return list;
    }

    @Override
    public List queryComment(int pk_blog) {
        List list=blogInfoDAOImpl.query_comment(pk_blog);
        return list;
    }

    @Override
    public int saveComment(JSONObject json) {
        int i=blogInfoDAOImpl.save_comment(json);
        return i;
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
