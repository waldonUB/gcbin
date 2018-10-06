package cn.wdq.dao;

import cn.wdq.entities.GroupPermission;
import cn.wdq.entities.GroupUser;
import cn.wdq.entities.UserInfo;
import cn.wdq.mapping.PermissionDAO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class PermissionDAOImpl implements PermissionDAO {
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<GroupPermission> getPermissionList(JSONObject json) {
        return sqlSession.selectList("cn.wdq.mapping.PermissionDAO.getPermissionList", json);
    }

    @Override
    public List<Map<String, Object>> queryPermissionTree(JSONObject json) {
        return sqlSession.selectList("cn.wdq.mapping.PermissionDAO.queryPermissionTree", json);
    }

    @Override
    public int savePermission(GroupPermission groupPermission) {
        return sqlSession.insert("cn.wdq.mapping.PermissionDAO.savePermission", groupPermission);
    }

    @Override
    public int deletePermission(GroupPermission groupPermission) {
        return sqlSession.delete("cn.wdq.mapping.PermissionDAO.deletePermission", groupPermission);
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
        return sqlSession.delete("cn.wdq.mapping.PermissionDAO.deleteGroup" ,groupUser);
    }

    @Override
    public List<UserInfo> queryUsers() {
        return sqlSession.selectList("cn.wdq.mapping.PermissionDAO.queryUsers");
    }

    @Override
    public int addGroupUser(JSONObject json) {
        return sqlSession.update("cn.wdq.mapping.PermissionDAO.addGroupUser", json);
    }

    @Override
    public List<Map<String, Object>> queryGroupUsers(JSONObject json) {
        return sqlSession.selectList("cn.wdq.mapping.PermissionDAO.queryGroupUsers", json);
    }

    @Override
    public int deleteGroupUser(JSONObject json) {
        return sqlSession.update("cn.wdq.mapping.PermissionDAO.deleteGroupUser", json);
    }
}
