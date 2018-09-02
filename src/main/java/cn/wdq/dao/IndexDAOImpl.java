package cn.wdq.dao;

import cn.wdq.mapping.IndexDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class IndexDAOImpl implements IndexDAO {
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List query_last() {
        return sqlSession.selectList("cn.wdq.mapping.IndexDAO.query_last");
    }

    @Override
    public List query_time() {
        return sqlSession.selectList("cn.wdq.mapping.IndexDAO.query_time");
    }

    @Override
    public List query_map() {
        return sqlSession.selectList("cn.wdq.mapping.IndexDAO.query_map");
    }
}
