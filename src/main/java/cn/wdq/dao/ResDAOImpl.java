package cn.wdq.dao;

import cn.wdq.entities.ResourceModel;
import cn.wdq.mapping.ResourceDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ResDAOImpl implements ResourceDAO {
    private SqlSession sqlSession;
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    @Override
    public int save_tree(ResourceModel resourceModel) {
        return sqlSession.insert("cn.wdq.mapping.ResourceDAO.save_tree",resourceModel);
    }
    @Override
    public int edit_tree(ResourceModel resourceModel){
        return sqlSession.update("cn.wdq.mapping.ResourceDAO.edit_tree",resourceModel);
    }
    @Override
    public List queryByName(ResourceModel resourceModel) {
        return sqlSession.selectList("cn.wdq.mapping.ResourceDAO.queryByName",resourceModel);
    }

    @Override
    public List query_tree(){
        return sqlSession.selectList("cn.wdq.mapping.ResourceDAO.query_tree");
    }
    @Override
    public int delete_tree(ResourceModel resourceModel){
        return sqlSession.delete("cn.wdq.mapping.ResourceDAO.delete_tree",resourceModel);
    }
}
