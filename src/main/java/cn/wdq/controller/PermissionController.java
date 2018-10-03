package cn.wdq.controller;

import cn.wdq.entities.GroupPermission;
import cn.wdq.entities.GroupUser;
import cn.wdq.entities.ReturnModel;
import cn.wdq.service.UserPermission;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return model;
    }

    /**
     * 保存该用户组所拥有的权限
     * @param groupPermission 1.group_code 用户所属的用户组编码
     * */
    @RequestMapping("/savePermission")
    @ResponseBody
    public ReturnModel savePermission (@RequestBody GroupPermission groupPermission) {
        ReturnModel model = new ReturnModel();
        try {
            userPermission.savePermission(groupPermission);
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
     * 删除该用户组所拥有的权限
     * @param groupPermission 1.group_code 用户所属的用户组编码
     * */
    @RequestMapping("/deletePermission")
    @ResponseBody
    public ReturnModel deletePermission (@RequestBody GroupPermission groupPermission) {
        ReturnModel model = new ReturnModel();
        try {
            userPermission.deletePermission(groupPermission);
            model.setSuccess(true);
            return model;
        } catch (Exception e) {
            logger.error("删除用户组权限异常", e);
            model.setSuccess(false);
            model.setMessage("删除用户组权限异常");
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
}
