package cn.wdq.mapping;

import cn.wdq.entities.GroupPermission;

import java.util.List;

public interface PermissionDAO {
    List<GroupPermission> getPermissionList();
    int savePermission(GroupPermission groupPermission);
    int deletePermission(GroupPermission groupPermission);
}
