package cn.wdq.dao;

import cn.wdq.entities.GroupPermission;
import cn.wdq.mapping.PermissionDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PermissionDAOImpl implements PermissionDAO {
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<GroupPermission> getPermissionList() {
        return null;
    }

    @Override
    public int savePermission(GroupPermission groupPermission) {
        return sqlSession.insert("cn.wdq.mapping.PermissionDAO.savePermission", groupPermission);
    }

    @Override
    public int deletePermission(GroupPermission groupPermission) {
        return 0;
    }
}
