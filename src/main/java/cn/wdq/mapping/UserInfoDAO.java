package cn.wdq.mapping;

import cn.wdq.entities.UserInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserInfoDAO {
    public List<UserInfo> getAllUserInfo();
    public List<UserInfo> hasSame(JSONObject json);
    public int register(JSONObject json);
    public int kicking(String user_name);
    public int updateLasttime(String user_name);
    public int deleteOnline(String cuserid);
    public int clearAll();
}
