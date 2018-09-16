package cn.wdq.dao;

import cn.wdq.mapping.CommonDAO;
import org.apache.ibatis.session.SqlSession;


public class CommonDAOImpl implements CommonDAO {
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public int loginInterceptor(String cuserid) {
        return sqlSession.selectOne("cn.wdq.mapping.FilterDAO.loginInterceptor",cuserid);
    }
}
