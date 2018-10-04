package cn.wdq.service.impl;

import cn.wdq.dao.PermissionDAOImpl;
import cn.wdq.entities.GroupPermission;
import cn.wdq.entities.GroupUser;
import cn.wdq.service.UserPermission;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPermissionImpl implements UserPermission {
    Logger logger = Logger.getLogger(UserPermissionImpl.class);
    @Autowired
    PermissionDAOImpl permissionDAO;
    @Override
    public List<GroupPermission> getPermissionList(JSONObject json) {
        return permissionDAO.getPermissionList(json);
    }

    @Override
    public int savePermission(GroupPermission groupPermission) {
        return permissionDAO.savePermission(groupPermission);
    }

    @Override
    public int deletePermission(GroupPermission groupPermission) {
        return permissionDAO.deletePermission(groupPermission);
    }

    @Override
    public List<GroupUser> getGroupList() {
        return permissionDAO.getGroupList();
    }

    @Override
    public int saveGroup(GroupUser groupUser) {
        return permissionDAO.saveGroup(groupUser);
    }

    @Override
    public int deleteGroup(GroupUser groupUser) {
        return permissionDAO.deleteGroup(groupUser);
    }
}
