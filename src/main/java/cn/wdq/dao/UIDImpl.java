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
    public int haveSame(JSONObject json) {
        String user_name=json.getString("user_name");
        return sqlSession.selectOne("cn.wdq.mapping.UserInfoDAO.haveSame",user_name);
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
    public int forceLogout(String user_name){
        return sqlSession.delete("cn.wdq.mapping.UserInfoDAO.forceLogout",user_name);
    }
    @Override
    public String checkOnline(String user_name){
        return sqlSession.selectOne("cn.wdq.mapping.UserInfoDAO.checkOnline",user_name);
    }
    @Override
    public int autoLogout(String user_name){
        return sqlSession.delete("cn.wdq.mapping.UserInfoDAO.autoLogout",user_name);
    }
    @Override
    public int insertLoginInfo(UserInfo userInfo){
        return sqlSession.insert("cn.wdq.mapping.UserInfoDAO.insertLoginInfo",userInfo);
    }
    @Override
    public int editPassword(UserInfo userInfo){
        return sqlSession.update("cn.wdq.mapping.UserInfoDAO.editPassword",userInfo);
    }
    @Override
    public int setHeadImg(UserInfo userInfo){
        return sqlSession.update("cn.wdq.mapping.UserInfoDAO.setHeadImg",userInfo);
    }
    @Override
    public int kicking(String user_name) {
        return sqlSession.delete("cn.wdq.mapping.UserInfoDAO.kicking",user_name);
    }

    @Override
    public int updateLastTime(String user_name) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String last_time=simpleDateFormat.format(new Date());
        UserInfo userInfo=new UserInfo();
        userInfo.setUser_name(user_name);
        userInfo.setLast_time(last_time);
        return sqlSession.update("cn.wdq.mapping.UserInfoDAO.updateLastTime",userInfo);
    }

    @Override
    public int deleteOnline(String cuserid) {
        return sqlSession.delete("cn.wdq.mapping.UserInfoDAO.deleteOnline",cuserid);
    }

    @Override
    public int clearAll() {
        return sqlSession.delete("cn.wdq.mapping.UserInfoDAO.clearAll");
    }

    @Override
    public int addLoginHistory(UserInfo userInfo) {
        return sqlSession.insert("cn.wdq.mapping.UserInfoDAO.addLoginHistory",userInfo);
    }

    @Override
    public List<UserInfo> queryLogHis() {
        return sqlSession.selectList("cn.wdq.mapping.UserInfoDAO.queryLogHis");
    }
}
