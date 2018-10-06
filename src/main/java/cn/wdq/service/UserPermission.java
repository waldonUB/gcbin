package cn.wdq.service;

import cn.wdq.entities.GroupPermission;
import cn.wdq.entities.GroupUser;
import cn.wdq.entities.UserInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface UserPermission {
    List<GroupPermission> getPermissionList(JSONObject json);
    List<Map<String,Object>> queryPermissionTree(JSONObject json);
    int savePermission(GroupPermission groupPermission);
    int deletePermission(GroupPermission groupPermission);
    List<GroupUser> getGroupList();
    int saveGroup(GroupUser groupUser);
    int deleteGroup(GroupUser groupUser);
    List<UserInfo> queryUsers();
    int addGroupUser(JSONObject json);
    List<Map<String, Object>> queryGroupUsers(JSONObject json);
    int deleteGroupUser(JSONObject json);
}
