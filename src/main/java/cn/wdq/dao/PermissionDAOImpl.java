package cn.wdq.dao;

import cn.wdq.entities.GroupPermission;
import cn.wdq.entities.GroupUser;
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

    @Override
    public List<GroupUser> getGroupList() {
        return sqlSession.selectList("cn.wdq.mapping.PermissionDAO.getGroupList");
    }

    @Override
    public int saveGroup(GroupUser groupUser) {
        return sqlSession.insert("cn.wdq.mapping.PermissionDAO.saveGroup" ,groupUser);
    }

    @Override
    public int deleteGroup(GroupUser groupUser) {
        return 0;
    }
}
