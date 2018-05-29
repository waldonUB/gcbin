package cn.wdq.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface BlogInfoService {
    public int saveQuestion(JSONObject json);
    public List queryBlog(String user_name);
    public List queryComment(int pk_blog);
    public int saveComment(JSONObject json);
    public int savePraise(JSONObject json);
}
