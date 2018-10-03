package cn.wdq.service.impl;

import cn.wdq.dao.PermissionDAOImpl;
import cn.wdq.entities.GroupPermission;
import cn.wdq.entities.GroupUser;
import cn.wdq.service.UserPermission;
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
    public List<GroupPermission> getPermissionList() {
        return null;
    }

    @Override
    public int savePermission(GroupPermission groupPermission) {
        return permissionDAO.savePermission(groupPermission);
    }

    @Override
    public int deletePermission(GroupPermission groupPermission) {
        return 0;
    }

    @Override
    public List<GroupUser> getGroupList() {
        return null;
    }

    @Override
    public int saveGroup(GroupUser groupUser) {
        return permissionDAO.saveGroup(groupUser);
    }

    @Override
    public int deleteGroup(GroupUser groupUser) {
        return 0;
    }
}
