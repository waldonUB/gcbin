package cn.wdq.mapping;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 话题信息
 * @author waldon
 * */
public interface BlogInfoDAO {
    /**
     * 保存话题
     * @param json include:1.blog_title 话题标题 2.blog_content 话题内容
     * @return 保存成功的状态 0:失败 1::成功
     * */
    int save_question(JSONObject json);
    /**
     * 查询该用户关联的话题所有信息
     * @param user_name 用户名
     * @return 该用户关联的话题所有信息
     * */
    List query_blog(String user_name);
    /**
     * 查询评论
     * @param pk_blog 话题主键
     * @return 该话题下所有的评论信息
     * */
    List query_comment(int pk_blog);
    /**
     * 保存评论
     * @param json include:1.pk_blog 话题主键 2.cm_content 评论内容
     * @return 保存成功的状态 0:失败 1::成功
     * */
    int save_comment(JSONObject json);
    /**
     * 保存赞
     * @param json include:1.pk_blog 话题主键 2.user_name 点赞人
     * @return 保存成功的状态 0:失败 1::成功
     * */
    int save_praise(JSONObject json);
    /**
     * 查询当前登录用户是否在该话题下点过赞
     * @param json include:1.pk_blog 话题主键 2.user_name 点赞人
     * @return 点过赞的话题集合
     * */
    List is_praised(JSONObject json);
    /**
     * 取消该话题的赞
     * @param json include:1.pk_blog 话题主键 2.user_name 点赞人
     * @return 取消赞的状态 0:失败 1::成功
     * */
    int cancel_praised(JSONObject json);
}
