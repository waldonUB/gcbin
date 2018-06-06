package cn.wdq.dao;

import cn.wdq.entities.UserInfo;
import cn.wdq.mapping.UserInfoDAO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UIDImpl implements UserInfoDAO{
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<UserInfo> login(UserInfo userInfo) {
        return sqlSession.selectList("cn.wdq.mapping.UserInfoDAO.login",userInfo);
    }

    @Override
    public List<UserInfo> hasSame(JSONObject json) {
        String user_name=json.getString("user_name");
        return sqlSession.selectList("cn.wdq.mapping.UserInfoDAO.hasSame",user_name);
    }

    @Override
    public int register(JSONObject json) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String last_time=simpleDateFormat.format(new Date());
        UserInfo userInfo=new UserInfo();
        userInfo.setCuserid(UUID.randomUUID().toString());
        userInfo.setUser_name(json.getString("user_name"));
        userInfo.setPassword(json.getString("password"));
        userInfo.setLast_time(last_time);
        userInfo.setDistrict(json.getString("district"));
        return sqlSession.insert("cn.wdq.mapping.UserInfoDAO.register",userInfo);
    }

    @Override
    public int kicking(String user_name) {
        return sqlSession.delete("cn.wdq.mapping.UserInfoDAO.kicking",user_name);
    }

    @Override
    public int updateLasttime(String user_name) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String last_time=simpleDateFormat.format(new Date());
        UserInfo userInfo=new UserInfo();
        userInfo.setUser_name(user_name);
        userInfo.setLast_time(last_time);
        return sqlSession.update("cn.wdq.mapping.UserInfoDAO.updateLasttime",userInfo);
    }

    @Override
    public int deleteOnline(String cuserid) {
        return sqlSession.delete("cn.wdq.mapping.UserInfoDAO.deleteOnline",cuserid);
    }

    @Override
    public int clearAll() {
        return sqlSession.delete("cn.wdq.mapping.UserInfoDAO.clearAll");
    }
}
