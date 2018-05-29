package cn.wdq.mapping;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface BlogInfoDAO {
    public int save_question(JSONObject json);
    public List query_blog(String user_name);
    public List query_comment(int pk_blog);
    /**
     * 保存评论
     * */
    public int save_comment(JSONObject json);
    /**
     * 保存赞
     * */
    public int save_praise(JSONObject json);
    /**
     * 判断是否该用户在该话题下赞过
     * */
    public List is_praised(JSONObject json);
    /**
     * 取消赞
     * */
    public int cancel_praised(JSONObject json);
}
