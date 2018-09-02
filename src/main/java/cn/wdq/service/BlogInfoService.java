package cn.wdq.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 话题交流
 * @author waldon
 * */
public interface BlogInfoService {
    /**
     * 保存话题
     * @param json include:1.pk_blog 话题主键 2.blog_title 话题标题
     * @return 保存成功的状态 1 成功 0失败
     * */
    int saveQuestion(JSONObject json);
    /**
     * 保存话题
     * @param user_name 用户名
     * @return 与该用户关联的话题，点赞
     * */
    List queryBlog(String user_name);
    /**
     * 保存话题
     * @param pk_blog 话题主键
     * @return 该话题下的评论
     * */
    List queryComment(int pk_blog);
    /**
     * 保存话题
     * @param json include:1.pk_comment 评论主键
     * @return 保存成功的状态 1 成功 0失败
     * */
    int saveComment(JSONObject json);
    /**
     * 保存话题
     * @param json include:1.pk_praise 点赞主键
     * @return 保存成功的状态 1 成功 0失败
     * */
    int savePraise(JSONObject json);
}
