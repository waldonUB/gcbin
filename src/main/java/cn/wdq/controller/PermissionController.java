package cn.wdq.controller;

import cn.wdq.entities.GroupPermission;
import cn.wdq.entities.GroupUser;
import cn.wdq.entities.ReturnModel;
import cn.wdq.entities.UserInfo;
import cn.wdq.service.UserPermission;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    private Logger logger = Logger.getLogger(PermissionController.class);
    @Autowired
    UserPermission userPermission;

    /**
     * 查询用户可拥有的节点权限
     * @param json 1.group_code 用户所属的用户组编码
     * */
    @RequestMapping("/queryPermission")
    @ResponseBody
    public ReturnModel queryPermission (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        List<GroupPermission> list = userPermission.getPermissionList(json);
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    /**
     * 根据用户拥有的节点权限生成导航栏节点
     * @param json 1.group_code 用户所属的用户组编码
     * */
    @RequestMapping("/queryPermissionTree")
    @ResponseBody
    public ReturnModel queryPermissionTree (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        List<Map<String, Object>> list = userPermission.queryPermissionTree(json);
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    /**
     * 保存该用户组所拥有的权限
     * @param groupPermissions 1.group_code 用户所属的用户组编码
     * */
    @RequestMapping("/savePermission")
    @ResponseBody
    public ReturnModel savePermission (@RequestBody GroupPermission[] groupPermissions) {
        ReturnModel model = new ReturnModel();
        try {
            userPermission.deletePermission(groupPermissions[0]);
            for (GroupPermission groupPermission : groupPermissions) {
                userPermission.savePermission(groupPermission);
            }
            model.setSuccess(true);
            return model;
        } catch (Exception e) {
            logger.error("保存用户组权限异常", e);
            model.setSuccess(false);
            model.setMessage("保存用户组权限异常");
            return model;
        }
    }

    /**
     * 查询用户组
     * */
    @RequestMapping("/queryGroup")
    @ResponseBody
    public ReturnModel queryGroup (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        List<GroupUser> list = userPermission.getGroupList();
        model.setSuccess(true);
        model.setData(list);
        return model;
    }

    /**
     * 保存用户组
     * */
    @RequestMapping("/saveGroup")
    @ResponseBody
    public ReturnModel saveGroup (@RequestBody GroupUser groupUser) {
        ReturnModel model = new ReturnModel();
        userPermission.saveGroup(groupUser);
        return model;
    }

    /**
     * 删除用户组
     * */
    @RequestMapping("/deleteGroup")
    @ResponseBody
    public ReturnModel deleteGroup (@RequestBody GroupUser groupUser) {
        ReturnModel model = new ReturnModel();
        return model;
    }

    /**
     * 查询系统所有用户
     * */
    @RequestMapping("/queryUsers")
    @ResponseBody
    public ReturnModel queryUsers (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        if (StringUtils.isEmpty(json.getString("user_name").trim())) {
            json.put("user_name", null);
        }
        List<UserInfo> list = userPermission.queryUsers(json);
        model.setData(list);
        model.setSuccess(true);
        return model;
    }

    /**
     * 添加用户到用户组
     * */
    @RequestMapping("/addGroupUser")
    @ResponseBody
    public ReturnModel addGroupUser (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        userPermission.addGroupUser(json);
        model.setSuccess(true);
        return model;
    }

    /**
     * 查询当前用户组用户
     * */
    @RequestMapping("/queryGroupUsers")
    @ResponseBody
    public ReturnModel queryGroupUsers (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        List<Map<String, Object>> list = userPermission.queryGroupUsers(json);
        model.setData(list);
        model.setSuccess(true);
        return model;
    }

    /**
     * 删除当前用户组用户
     * */
    @RequestMapping("/deleteGroupUser")
    @ResponseBody
    public ReturnModel deleteGroupUser (@RequestBody JSONObject json) {
        ReturnModel model = new ReturnModel();
        userPermission.deleteGroupUser(json);
        model.setSuccess(true);
        return model;
    }
}
