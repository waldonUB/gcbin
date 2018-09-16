package cn.wdq.dao;

import cn.wdq.mapping.AdminDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDAOImpl implements AdminDAO {
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List queryUserType(String userType, int page) {
        Map<String,Object> map=new HashMap<>();
        map.put("userType",userType);
        map.put("page",page);
        return sqlSession.selectList("cn.wdq.mapping.AdminDAO.queryUserType",map);
    }

    @Override
    public int queryAllPage(){
        return sqlSession.selectOne("cn.wdq.mapping.AdminDAO.queryAllPage");
    }

    @Override
    public List querySearch(String user_name) {
        return sqlSession.selectList("cn.wdq.mapping.AdminDAO.querySearch",user_name);
    }

    @Override
    public int deleteCustomer(String user_name) {
        return sqlSession.delete("cn.wdq.mapping.AdminDAO.deleteCustomer",user_name);
    }

    @Override
    public int kicking(String user_name) {
        return sqlSession.delete("cn.wdq.mapping.AdminDAO.kicking",user_name);
    }
}
