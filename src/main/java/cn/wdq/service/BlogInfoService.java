package cn.wdq.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface BlogInfoService {
    int saveQuestion(JSONObject json);
    List queryBlog(String user_name);
    List queryComment(int pk_blog);
    int saveComment(JSONObject json);
    int savePraise(JSONObject json);
}
