package cn.wdq.mapping;

import cn.wdq.entities.GroupPermission;
import cn.wdq.entities.GroupUser;

import java.util.List;

public interface PermissionDAO {
    List<GroupPermission> getPermissionList();
    int savePermission(GroupPermission groupPermission);
    int deletePermission(GroupPermission groupPermission);
    List<GroupUser> getGroupList();
    int saveGroup(GroupUser groupUser);
    int deleteGroup(GroupUser groupUser);
}
