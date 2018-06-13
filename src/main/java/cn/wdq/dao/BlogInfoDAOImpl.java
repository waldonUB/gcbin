package cn.wdq.dao;

import cn.wdq.entities.BlogInfo;
import cn.wdq.entities.CommentInfo;
import cn.wdq.entities.PraiseInfo;
import cn.wdq.mapping.BlogInfoDAO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class BlogInfoDAOImpl implements BlogInfoDAO {
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public int save_question(JSONObject json) {
        BlogInfo blogInfo=new BlogInfo();
        //blogInfo.setPk_blog(1);主键已自增
        blogInfo.setCuserid(json.getString("cuserid"));
        blogInfo.setUser_name(json.getString("user_name"));
        blogInfo.setHead_img(json.getString("head_img"));
        blogInfo.setBlog_title(json.getString("blog_title"));
        blogInfo.setBlog_content(json.getString("blog_content"));
        blogInfo.setBlog_classify(json.getString("blog_classify"));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate=simpleDateFormat.format(new Date());
        blogInfo.setLast_time(formatDate);//编辑时间
        return sqlSession.insert("cn.wdq.mapping.BlogInfoDAO.save_question",blogInfo);
    }

    @Override
    public List query_blog(String user_name) {
        return sqlSession.selectList("cn.wdq.mapping.BlogInfoDAO.query_blog",user_name);
    }

    @Override
    public List query_comment(int pk_blog) {
        return sqlSession.selectList("cn.wdq.mapping.BlogInfoDAO.query_comment",pk_blog);
    }

    @Override
    public int save_comment(JSONObject json) {
        CommentInfo commentInfo=new CommentInfo();
        commentInfo.setPk_blog(json.getInteger("pk_blog"));
        commentInfo.setCm_content(json.getString("cm_content"));
        commentInfo.setCm_praise("");
        commentInfo.setUser_name(json.getString("user_name"));
        commentInfo.setHead_img(json.getString("head_img"));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String formatDate=simpleDateFormat.format(new Date());
        commentInfo.setCm_time(formatDate);
        return sqlSession.insert("cn.wdq.mapping.BlogInfoDAO.save_comment",commentInfo);
    }

    @Override
    public int save_praise(JSONObject json) {
        PraiseInfo praiseInfo=new PraiseInfo();
        praiseInfo.setPk_blog(json.getInteger("pk_blog"));
        praiseInfo.setUser_name(json.getString("user_name"));
        return sqlSession.insert("cn.wdq.mapping.BlogInfoDAO.save_praise",praiseInfo);
    }

    @Override
    public List is_praised(JSONObject json) {
        PraiseInfo praiseInfo=new PraiseInfo();
        praiseInfo.setPk_blog(json.getInteger("pk_blog"));
        praiseInfo.setUser_name(json.getString("user_name"));
        return sqlSession.selectList("cn.wdq.mapping.BlogInfoDAO.is_praised",praiseInfo);
    }

    @Override
    public int cancel_praised(JSONObject json) {
        PraiseInfo praiseInfo=new PraiseInfo();
        praiseInfo.setPk_blog(json.getInteger("pk_blog"));
        praiseInfo.setUser_name(json.getString("user_name"));
        return sqlSession.delete("cn.wdq.mapping.BlogInfoDAO.cancel_praised",praiseInfo);
    }
}
